package org.learning.videogameshop.controller;

import org.learning.videogameshop.model.Purchase;
import org.learning.videogameshop.repository.PurchaseRepository;
import org.learning.videogameshop.repository.VideogameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private VideogameRepository videogameRepository;

    @GetMapping
    public String list(Model model) {
//        List<Purchase> purchaseList = purchaseRepository.findAll(Sort.by("purchaseDate").descending());
        List<Purchase> purchaseList = purchaseRepository.findAll(Sort.by("purchaseDate").descending());
        model.addAttribute("purchaseList", purchaseList);
        return "purchases/list";
    }
//    @GetMapping("/create")
//    //   public String create(Model model) {
//    public String create(@RequestParam(name = "videogameId", required = false) Integer videogameId, Model model) {
//        Purchase purchase = new Purchase();
//        purchase.setPurchaseDate(LocalDate.now());
//        model.addAttribute("purchase", purchase);
//
//        List<Videogame> videogameList = videogameRepository.findAll();
//        model.addAttribute("videogameList", videogameList);
//
//        if (videogameId != null) {
//            Optional<Videogame> videogame = videogameRepository.findById(videogameId);
//            if (videogame.isPresent()) {
//                //model.addAttribute("selectedVideogame", videogame.orElse(null));
//                purchase.setStockedVideogame(videogame.orElse(null));
//            }
//        }
//
//        return "purchases/create";
//    }
//
//    @PostMapping("/create")
//    public String store(@Valid @ModelAttribute("purchase") Purchase purchaseForm, BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) {
//            System.out.println(bindingResult.getAllErrors());
//            return "purchases/create";
//        }
//        Purchase savedStock = purchaseRepository.save(purchaseForm);
//        return "redirect:/purchases";
//    }
//
//    @GetMapping("/edit/{id}")
//    public String edit(@PathVariable Integer id, Model model) {
//        Optional<Purchase> result = purchaseRepository.findById(id);
//        if (result.isPresent()) {
//            model.addAttribute("purchase", result.get());
//            return "purchases/edit";
//        } else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Purchase with id " + id + " not found");
//        }
//    }
//
//    @PostMapping("/edit/{id}")
//    public String update(@PathVariable Integer id, @Valid @ModelAttribute("purchase") Purchase stockFrom,
//                         BindingResult bindingResult) {
//        Optional<Purchase> result = purchaseRepository.findById(id);
//        if (result.isPresent()) {
//            if (bindingResult.hasErrors()) {
//                System.out.println(bindingResult.getAllErrors());
//                return "purchases/edit";
//            }
//            Purchase savedRecipe = purchaseRepository.save(stockFrom);
//            return "redirect:/purchases/show/" + id;
//        } else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Purchase with id " + id + " not found");
//        }
//    }
//
//    @PostMapping("/delete/{id}")
//    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
//        Optional<Purchase> result = purchaseRepository.findById(id);
//        if (result.isPresent()) {
//            purchaseRepository.deleteById(id);
//            redirectAttributes.addFlashAttribute("redirectMessage",
//                    "Purchase with id " + result.get().getId() + " deleted!");
//            return "redirect:/purchases";
//        } else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Purchase with id " + id + " not found");
//        }
//    }
//
//    @GetMapping("/show/{id}")
//    public String show(@PathVariable Integer id, Model model) {
//        Optional<Purchase> result = purchaseRepository.findById(id);
//        if (result.isPresent()) {
//            model.addAttribute("purchase", result.get());
//
//            return "purchases/show";
//        } else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Purchase with id " + id + " not found");
//        }
//
//    }

}
