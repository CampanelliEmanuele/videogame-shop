package org.learning.videogameshop.controller;

import jakarta.validation.Valid;
import org.learning.videogameshop.model.Purchase;
import org.learning.videogameshop.repository.PurchaseRepository;
import org.learning.videogameshop.repository.VideogameRepository;
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
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private VideogameRepository videogameRepository;

    @GetMapping
    public String purchaseSuccess(Model model) {
        Purchase lastPurchase = purchaseRepository.findFirstByOrderByPurchaseDateDesc();

        model.addAttribute("lastPurchase", lastPurchase);

        return "store/purchase-success";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Purchase> purchaseList = purchaseRepository.findAll(Sort.by("purchaseDate").descending());
        model.addAttribute("purchaseList", purchaseList);
        return "purchases/list";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Optional<Purchase> result = purchaseRepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("purchase", result.get());
            return "purchases/show";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Purchase with id " + id + " not found");
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<Purchase> result = purchaseRepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("purchase", result.get());
            return "purchases/edit";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Purchase with id " + id + " not found");
        }
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("purchase") Purchase stockFrom,
                         BindingResult bindingResult) {
        Optional<Purchase> result = purchaseRepository.findById(id);
        if (result.isPresent()) {
            if (bindingResult.hasErrors()) {
                System.out.println(bindingResult.getAllErrors());
                return "purchases/edit";
            }
            Purchase savedRecipe = purchaseRepository.save(stockFrom);
            return "redirect:/purchases/list";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Purchase with id " + id + " not found");
        }
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Optional<Purchase> result = purchaseRepository.findById(id);
        if (result.isPresent()) {
            purchaseRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("redirectMessage",
                    "Purchase with id " + result.get().getId() + " deleted!");
            return "redirect:/purchases/list";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Purchase with id " + id + " not found");
        }
    }

}
