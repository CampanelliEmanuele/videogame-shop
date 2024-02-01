package org.learning.videogameshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register/admins")
public class AdminController {

    @GetMapping("/administration")
    private String administration(Model model) {
        return "/register/admins/administration";
    }

}
