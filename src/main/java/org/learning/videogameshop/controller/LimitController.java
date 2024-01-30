package org.learning.videogameshop.controller;

import jakarta.validation.Valid;
import org.learning.videogameshop.model.Limit;
import org.learning.videogameshop.model.Videogame;
import org.learning.videogameshop.repository.LimitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Controller
@RequestMapping("/limits")
public class LimitController {
    @Autowired
    LimitRepository limitRepository;
    @GetMapping
    public String show(Model model) {
        Optional<Limit> result = limitRepository.findById(1);
        if (result.isPresent()) {
            model.addAttribute("limit", result.get());
            return "limits/show";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Limit not found");
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<Limit> result = limitRepository.findById(1);
        if (result.isPresent()) {
            model.addAttribute("limit", result.get());
            return "limits/edit";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Limit not found");
        }
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("limit") Limit formLimit,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "limits/edit";
        }
        Limit saveLimit = limitRepository.save(formLimit);
        return "redirect:/limits";
    }
}
