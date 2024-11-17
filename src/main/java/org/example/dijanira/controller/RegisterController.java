package org.example.dijanira.controller;

import org.example.dijanira.model.User;
import org.example.dijanira.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegisterPage() {
        return "page/Register";
    }

    @PostMapping("/register")
    public String registerUser(User user) {
        if (userService.isEmailExists(user.getEmail())) {
            return "redirect:/register?error=emailExists"; // Se o e-mail já existir, redireciona com mensagem de erro
        }


        if (userService.registerUser(user)) {
            return "redirect:login"; //
        } else {
            System.out.println("Usuário encontrado: " + user.getEmail());
            return "redirect:/register?error=error";
        }
    }


    @GetMapping("/new-user")
    public String shownewage() {
        return "page/newUser";
    }


    @PostMapping("/new-user")
    public String newuser(User user) {
        if (userService.isEmailExists(user.getEmail())) {
            return "redirect:/new-user?error=emailExists";
        }


        if (userService.registerUser(user)) {
            return "redirect:/home"; //
        } else {

            return "redirect:/new-user?error=error";
        }
    }
}
