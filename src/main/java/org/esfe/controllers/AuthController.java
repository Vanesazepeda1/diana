package org.esfe.controllers;

import org.esfe.models.user;
import org.esfe.repository.irolerepository;
import org.esfe.services.implement.userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private userservice userservice;

    @Autowired
    private irolerepository repository;

    @GetMapping("/login")
    public String login(){ return "login";}

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new user());
        model.addAttribute("roles", repository.findAll());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute user user, BindingResult result, RedirectAttributes attributes, Model model){
        // Verificar si ya existe un usuario con el mismo correo
        user exists = userservice.findByEmail(user.getEmail());

        if (exists != null) {
            result.rejectValue("email", null, "Ya existe un usuario con el correo");
            model.addAttribute("roles", repository.findAll());
            return "register";
        }
        if (result.hasErrors()) {
            model.addAttribute("roles", repository.findAll());
            return "register";
        }

        try {
            this.userservice.createOrEditOne(user);
        } catch (DataIntegrityViolationException ex) {
            result.rejectValue("email", null, "Error al guardar el usuario");
            model.addAttribute("roles", repository.findAll());
            return "register";
        }
        catch (Exception e){
            result.rejectValue("email", null, "Error inesperado");
            model.addAttribute("roles", repository.findAll());
            return "register";
        }

        attributes.addFlashAttribute("msg", "Usuario creado correctamente");

        return "redirect:/auth/login";
    }

    @GetMapping("/logout")
    public String logoutSuccess(RedirectAttributes attributes) {
        attributes.addFlashAttribute("logoutMessage", "Sesion cerrada con exito");
        return "redirect:/auth/login";
    }

}
