package org.learning.videogameshop.controller;

import org.learning.videogameshop.model.Videogame;
import org.learning.videogameshop.repository.PurchaseRepository;
import org.learning.videogameshop.repository.TypeRepository;
import org.learning.videogameshop.repository.VideogameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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

}
