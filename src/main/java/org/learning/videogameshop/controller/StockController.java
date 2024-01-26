package org.learning.videogameshop.controller;

import jakarta.validation.Valid;
import org.learning.videogameshop.model.Stock;
import org.learning.videogameshop.model.Stock;
import org.learning.videogameshop.model.Stock;
import org.learning.videogameshop.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("stock") Stock stockForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "stocks/create";
        }
        Stock savedStock = stockRepository.save(stockForm);
        return "redirect:/stocks/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<Stock> result = stockRepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("stock", result.get());
            return "stocks/edit";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock with id " + id + " not found");
        }
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("stock") Stock stockFrom,
                         BindingResult bindingResult) {
        Optional<Stock> result = stockRepository.findById(id);
        if (result.isPresent()) {
            if (bindingResult.hasErrors()) {
                System.out.println(bindingResult.getAllErrors());
                return "stocks/edit";
            }
            Stock savedRecipe = stockRepository.save(stockFrom);
            return "redirect:/stocks/show/" + id;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock with id " + id + " not found");
        }
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Optional<Stock> result = stockRepository.findById(id);
        if (result.isPresent()) {
            stockRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("redirectMessage",
                    "Stock with id " + result.get().getId() + " deleted!");
            return "redirect:/stocks";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock with di " + id + " not found");
        }
    }



}
