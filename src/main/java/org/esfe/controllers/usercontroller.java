package org.esfe.controllers;
import org.esfe.models.role;
import org.esfe.models.user;
import org.esfe.services.interfaces.iuserservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("user")
public class usercontroller {

    @Autowired
    private iuserservice userservice;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1) - 1; // si no está seteado se asigna 0
        int pageSize = size.orElse(5); // tamaño de la página, se asigna 5
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<user> user = userservice.findAll(pageable);
        model.addAttribute("user", user);

        int totalPages = user.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "user/index";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model) {
        user user = userservice.findOneById(id).orElse(null);
        model.addAttribute("user", user);
        return "user/details";
    }

    @GetMapping("/create")
    public String create(user user) {
        return "user/create";
    }

    @PostMapping("/save")
    public String save(user user, BindingResult result, Model model, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            attributes.addFlashAttribute("error", "No se pudo guardar debido a un error.");
            return "user/create";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userservice.createOrEditOne((org.esfe.models.user) user);
        attributes.addFlashAttribute("msg", "Usuario creado correctamente");
        return "redirect:/user";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        user user = userservice.findOneById(id).orElse(null);
        model.addAttribute("user", user);
        return "user/edit";
    }


    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model){
        user user = userservice.findOneById(id).orElse(null);
        model.addAttribute("user", user);
        return "user/delete";
    }
    @PostMapping("/delete")
    public String delete(user user, RedirectAttributes attributes){
        userservice.deleteOneById(user.getId());
        attributes.addFlashAttribute("msg", "Usuario eliminado correctamente");
        return "redirect:/user";
    }
}