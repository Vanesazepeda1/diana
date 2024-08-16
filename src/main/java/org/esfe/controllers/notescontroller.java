package org.esfe.controllers;
import org.esfe.models.notes;
import org.esfe.models.subjects;
import org.springframework.web.bind.annotation.InitBinder;
import org.esfe.models.user;
import org.esfe.repository.inotesrepository;
import org.esfe.services.implement.notesservice;
import org.esfe.services.implement.roleservice;
import org.esfe.services.interfaces.inotesservice;
import org.esfe.services.interfaces.isubjectsservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/notes")
public class notescontroller {

    @Autowired
    private inotesservice notesService;
    @Autowired
    private isubjectsservice subjectService;


    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1) - 1;
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<notes> note = notesService.findAll(pageable);
        model.addAttribute("notes", note);

        int totalPages = note.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "notes/index";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model){
        notes note = notesService.findOneById(id).orElse(null);
        model.addAttribute("notes", note);
        return "notes/details";
    }

    @GetMapping("/create")
    public String create(notes notes,Model model){
        model.addAttribute("subjects", subjectService.getAll());
        return "notes/create";
    }


    @PostMapping("/save")
    public String save(notes note, BindingResult result, Model model, RedirectAttributes attributes){
        if(result.hasErrors()){
            model.addAttribute("notes", note);
            attributes.addFlashAttribute("error", "No se pudo guardar debido a un error.");
            return "notes/create";
        }

        notesService.createOrEditOne((org.esfe.models.notes) note);
        attributes.addFlashAttribute("msg", "subjects creado correctamente");
        return "redirect:/notes";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        notes note = notesService.findOneById(id).get();
        model.addAttribute("notes", note);
        return "notes/edit";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model){
        notes note= notesService.findOneById(id).orElse(null);
        model.addAttribute("notes", note);
        return "notes/delete";
    }

    @PostMapping("/delete")
    public String delete(subjects subjects, RedirectAttributes attributes){
        notesService.deleteOneById(subjects.getId());
        attributes.addFlashAttribute("msg", "Estudiante eliminado correctamente");
        return "redirect:/notes";
    }
}
