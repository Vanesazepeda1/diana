package org.esfe.controllers;
import org.esfe.models.role;
import org.esfe.models.subjects;
import org.esfe.services.implement.roleservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("role")
public class rolecontroller {

    @Autowired
    private roleservice roleService;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1) - 1;
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<role> role = roleService.findAll(pageable);
        model.addAttribute("role", role);

        int totalPages = role.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "role/index";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model){
        role role = roleService.findOneById(id).orElse(null);
        model.addAttribute("role", role);
        return "role/details";
    }

    @GetMapping("/create")
    public String create(role role){
        return "role/create";
    }

    @PostMapping("/save")
    public String save(role role, BindingResult result, Model model, RedirectAttributes attributes){
        if(result.hasErrors()){
            model.addAttribute("role", role);
            attributes.addFlashAttribute("error", "No se pudo guardar debido a un error.");
            return "role/create";
        }

        roleService.createOrEditOne((org.esfe.models.role) role);
        attributes.addFlashAttribute("msg", "Rol creado correctamente");
        return "redirect:/role";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        role role = roleService.findOneById(id).orElse(null);
        model.addAttribute("role", role);
        return "role/edit";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model){
        role role = roleService.findOneById(id).orElse(null);
        model.addAttribute("role", role);
        return "role/delete";
    }


    @PostMapping("/delete")
    public String delete(role role, RedirectAttributes attributes){
        roleService.deleteOneById(role.getId());
        attributes.addFlashAttribute("msg", "Rol eliminado correctamente");
        return "redirect:/role";
    }
}