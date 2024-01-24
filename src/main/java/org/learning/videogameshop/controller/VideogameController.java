package org.learning.videogameshop.controller;

import jakarta.validation.Valid;
import org.learning.videogameshop.model.Videogame;
import org.learning.videogameshop.repository.PurchaseRepository;
import org.learning.videogameshop.repository.TypeRepository;
import org.learning.videogameshop.repository.VideogameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/videogames")
public class VideogameController {

    @Autowired
    private VideogameRepository videogameRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;

    @GetMapping
    public String index(Model model) {
        List<Videogame> videogameList = videogameRepository.findAll();
        model.addAttribute("videogameList", videogameList);
        return "/videogames/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Videogame recipe = new Videogame();
        model.addAttribute("videogame", recipe);
//        model.addAttribute("typeList", typeRepository.findAll());
        return "videogames/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("videogame") Videogame videogameForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
//            model.addAttribute("typeList", typeRepository.findAll());
            System.out.println(bindingResult.getAllErrors());
            return "videogames/create";
        }
        Videogame savedVideogame = videogameRepository.save(videogameForm);
        return "redirect:/videogames/show/" + savedVideogame.getId();
    }



}
