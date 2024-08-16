package org.esfe.controllers;

import org.esfe.models.role;
import org.esfe.models.students;
import org.esfe.services.interfaces.istudentsservice;
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
@RequestMapping("/students")
public class studentscontroller {

    @Autowired
    private istudentsservice studentsservice;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1) - 1;
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<students> students = studentsservice.findAll(pageable);
        model.addAttribute("students", students);

        int totalPages = students.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "students/index";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model){
        students student = studentsservice.findOneById(id).orElse(null);
        model.addAttribute("student", student);
        return "students/details";
    }

    @GetMapping("/create")
    public String create(Model model){
        students student = new students();
        model.addAttribute("student", student);
        return "students/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute students student, BindingResult result, Model model, RedirectAttributes attributes){
        if(result.hasErrors()){
            model.addAttribute("student", student);
            attributes.addFlashAttribute("error", "No se pudo guardar debido a un error.");
            return "students/create";
        }

        studentsservice.createOrEditOne(student);
        attributes.addFlashAttribute("msg", "Estudiante creado correctamente");
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        students student = studentsservice.findOneById(id).get();
        model.addAttribute("student", student);
        return "students/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute students student, BindingResult result, Model model, RedirectAttributes attributes){
        if(result.hasErrors()){
            model.addAttribute("student", student);
            attributes.addFlashAttribute("error", "No se pudo actualizar debido a un error.");
            return "students/edit";
        }

        studentsservice.createOrEditOne(student);
        attributes.addFlashAttribute("msg", "Estudiante actualizado correctamente");
        return "redirect:/students";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model){
        students student = studentsservice.findOneById(id).get();
        model.addAttribute("student", student);
        return "students/delete";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute students student, RedirectAttributes attributes){
        studentsservice.deleteOneById(student.getId());
        attributes.addFlashAttribute("msg", "Estudiante eliminado correctamente");
        return "redirect:/students";
    }
}
