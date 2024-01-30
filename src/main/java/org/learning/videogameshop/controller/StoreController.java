package org.learning.videogameshop.controller;

import org.learning.videogameshop.model.Purchase;
import org.learning.videogameshop.model.Type;
import org.learning.videogameshop.model.Videogame;
import org.learning.videogameshop.repository.PurchaseRepository;
import org.learning.videogameshop.repository.TypeRepository;
import org.learning.videogameshop.repository.VideogameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("/store")
public class StoreController {
    @Autowired
    private VideogameRepository videogameRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private TypeRepository typeRepository;

    @GetMapping
    public String viewGames(Model model) {
        List<Videogame> videogames = videogameRepository.findAll();
        List<Type> types = typeRepository.findAll();
        model.addAttribute("videogames", videogames);
        model.addAttribute("types", types);
        return "store/list";
    }

    @PostMapping("/purchase")
    public String makePurchase(@RequestParam Integer videogameId, @RequestParam int quantity) {
        Videogame videogame = videogameRepository.findById(videogameId)
                .orElseThrow(() -> new RuntimeException("Videogame not found with ID: " + videogameId));

        Purchase purchase = new Purchase();
        purchase.setVideogame(videogame);
        purchase.setPurchaseDate(LocalDate.now());
        purchase.setQuantity(quantity);

        purchaseRepository.save(purchase);

        return "redirect:/store";
    }






}

