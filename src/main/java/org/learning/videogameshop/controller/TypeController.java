package org.learning.videogameshop.controller;

import jakarta.persistence.*;
import org.learning.videogameshop.model.Type;
import org.learning.videogameshop.repository.TypeRepository;
import org.learning.videogameshop.repository.VideogameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/types")
public class TypeController {
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private VideogameRepository videogameRepository;

    @GetMapping
    public String index(Model model) {
        List<Type> typeList = typeRepository.findAll();
        model.addAttribute("typeList", typeList);
        return "type/list";
    }
}

