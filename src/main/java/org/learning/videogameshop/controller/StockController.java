package org.learning.videogameshop.controller;

import org.learning.videogameshop.model.Stock;
import org.learning.videogameshop.model.Type;
import org.learning.videogameshop.model.Videogame;
import org.learning.videogameshop.repository.PurchaseRepository;
import org.learning.videogameshop.repository.StockRepository;
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
import java.util.List;

@Controller
@RequestMapping("/stock")
public class StockController {
    @Autowired
    private VideogameRepository videogameRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private StockRepository stockRepository;

    @GetMapping
    public String viewStock(Model model) {
        List<Stock> stocks = stockRepository.findAll();
        List<Type> types = typeRepository.findAll();
        List<Videogame> videogames = videogameRepository.findAll();
        model.addAttribute("videogames", videogames);
        model.addAttribute("stocks", stocks);
        model.addAttribute("types", types);
        return "stock/show";
    }

    @PostMapping("/assortment")
    public String makeStock(@RequestParam Integer videogameId, @RequestParam int quantity) {
        Videogame videogame = videogameRepository.findById(videogameId)
                .orElseThrow(() -> new RuntimeException("Videogame not found with ID: " + videogameId));
        Stock newStock = new Stock();
        newStock.setPurchasedVideogame(videogame);
        newStock.setPurchaseDate(LocalDate.now());
        newStock.setPrice();
        newStock.setQuantity(quantity);
        newStock.setSupplierName();

    }
}
