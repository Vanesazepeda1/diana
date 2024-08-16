package org.esfe.controllers;
import org.esfe.models.role;
import org.esfe.models.students;
import org.esfe.models.subjects;
import org.esfe.services.interfaces.istudentsservice;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("subjects")
public class subjectscontroller {

    @Autowired
    private isubjectsservice subjectsservice;


    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1) - 1;
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<subjects> subjects = subjectsservice.findAll(pageable);
        model.addAttribute("subjects", subjects);

        int totalPages = subjects.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "subjects/index";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model){
        subjects subject = subjectsservice.findOneById(id).orElse(null);
        model.addAttribute("subjects", subject);
        return "subjects/details";
    }

    @GetMapping("/create")
    public String create(subjects subjects){
        return "subjects/create";
    }


    @PostMapping("/save")
    public String save(subjects subjects, BindingResult result, Model model, RedirectAttributes attributes){
        if(result.hasErrors()){
            model.addAttribute("subjects", subjects);
            attributes.addFlashAttribute("error", "No se pudo guardar debido a un error.");
            return "subjects/create";
        }

        subjectsservice.createOrEditOne((org.esfe.models.subjects) subjects);
        attributes.addFlashAttribute("msg", "subjects creado correctamente");
        return "redirect:/subjects";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        subjects subject = subjectsservice.findOneById(id).orElse(null);
        model.addAttribute("subjects", subject);
        return "subjects/edit";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model){
        subjects subject= subjectsservice.findOneById(id).orElse(null);
        model.addAttribute("subjects", subject);
        return "subjects/delete";
    }

    @PostMapping("/delete")
    public String delete(subjects subjects, RedirectAttributes attributes){
        subjectsservice.deleteOneById(subjects.getId());
        attributes.addFlashAttribute("msg", "Estudiante eliminado correctamente");
        return "redirect:/subjects";
    }
}
