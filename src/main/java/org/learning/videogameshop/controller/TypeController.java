package org.learning.videogameshop.controller;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import org.learning.videogameshop.model.Type;
import org.learning.videogameshop.repository.TypeRepository;
import org.learning.videogameshop.repository.VideogameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<Type> result = typeRepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("type", result.get());
            return "types/edit";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "type with id " + id + " not found");
        }
    }
    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("type") Type formType, BindingResult bindingResult) {
        Optional<Type> result = typeRepository.findById(id);
        if (result.isPresent()) {
            Type typeToEdit = result.get();
            if (bindingResult.hasErrors()) {
                return "types/edit";
            }
            Type savedType = typeRepository.save(formType);
            return "redirect:/types";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Type with id " + id + " not found");
        }
    }









}

