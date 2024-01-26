package org.learning.videogameshop.controller;

import org.learning.videogameshop.model.Stock;
import org.learning.videogameshop.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/stocks")
public class StockController {
    @Autowired
    private StockRepository stockRepository;

    @GetMapping
    public String list(Model model) {
        List<Stock> stockList = stockRepository.findAll(Sort.by("purchaseDate").descending());
        model.addAttribute("stockList", stockList);
        return "stocks/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        List<Stock> stockList = stockRepository.findAll(Sort.by("purchaseDate").descending());
        model.addAttribute("stockList", stockList);
        return "stocks/create";
    }

    @GetMapping("/edit")
    public String edit(Model model) {
        List<Stock> stockList = stockRepository.findAll(Sort.by("purchaseDate").descending());
        model.addAttribute("stockList", stockList);
        return "stocks/edit";
    }

//    @PostMapping("/assortment")
//    public String makeStock(@RequestParam Integer videogameId, @RequestParam int quantity) {
//        Videogame videogame = videogameRepository.findById(videogameId)
//                .orElseThrow(() -> new RuntimeException("Videogame not found with ID: " + videogameId));
//        Stock newStock = new Stock();
//        newStock.setPurchasedVideogame(videogame);
//        newStock.setPurchaseDate(LocalDate.now());
//        newStock.setPrice();
//        newStock.setQuantity(quantity);
//        newStock.setSupplierName();
//    }
}
