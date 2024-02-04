package org.learning.videogameshop.api;

import org.learning.videogameshop.model.Stock;
import org.learning.videogameshop.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stocks")
public class RestStockController {

    @Autowired
    StockRepository stockRepository;

    @GetMapping
    public List<Stock> list() {
        List<Stock> stockList = stockRepository.findAll();
        return stockList;
    }
}
