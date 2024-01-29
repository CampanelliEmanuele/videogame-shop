package org.learning.videogameshop.controller;

import org.learning.videogameshop.model.Limit;
import org.learning.videogameshop.model.Purchase;
import org.learning.videogameshop.model.Stock;
import org.learning.videogameshop.model.Videogame;
import org.learning.videogameshop.repository.LimitRepository;
import org.learning.videogameshop.repository.PurchaseRepository;
import org.learning.videogameshop.repository.StockRepository;
import org.learning.videogameshop.repository.VideogameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    private VideogameRepository videogameRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private LimitRepository limitRepository;

    @GetMapping
    public String show(Model model) {
        List<Videogame> videogameList = videogameRepository.findAll();
        List<Purchase> purchaseList = purchaseRepository.findAll();
        List<Stock> stockList = stockRepository.findAll();
        Optional<Limit> limit = limitRepository.findById(1);


        model.addAttribute("videogameList", videogameList);
        model.addAttribute("purchaseList", purchaseList);
        model.addAttribute("stockList", stockList);

        if (limit.isPresent()) {
            model.addAttribute("limitsTable", limit.get());
        }
        return "warehouse/show";
    }
}
