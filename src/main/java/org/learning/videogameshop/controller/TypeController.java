package org.learning.videogameshop.controller;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import org.learning.videogameshop.model.Type;
import org.learning.videogameshop.repository.TypeRepository;
import org.learning.videogameshop.repository.VideogameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
        return "types/list";
    }
    @GetMapping("/create")
    public String create(Model model){
        Type type = new Type();
        model.addAttribute("type", type);
        return "types/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("type") Type formType, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            return "types/create";
        } else {
            Type savedType = typeRepository.save(formType);
            return "redirect:/types";
        }
    }









}

