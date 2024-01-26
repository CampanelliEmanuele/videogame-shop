package org.learning.videogameshop.controller;

import jakarta.validation.Valid;
import org.learning.videogameshop.model.Videogame;
import org.learning.videogameshop.repository.PurchaseRepository;
import org.learning.videogameshop.repository.StockRepository;
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
    public String list(Model model) {
        List<Videogame> videogameList = videogameRepository.findAll();
        model.addAttribute("videogameList", videogameList);
        return "/videogames/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Videogame videogame = new Videogame();
        model.addAttribute("videogame", videogame);
        model.addAttribute("typeList", typeRepository.findAll());
        return "videogames/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("videogame") Videogame videogameForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("typeList", typeRepository.findAll());
            System.out.println(bindingResult.getAllErrors());
            return "videogames/create";
        }
        Videogame savedVideogame = videogameRepository.save(videogameForm);
        return "redirect:/videogames/show/" + savedVideogame.getId();
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Optional<Videogame> result = videogameRepository.findById(id);
        if (result.isPresent()) {
            Videogame videogame = result.get();
            model.addAttribute("videogame", videogame);
            model.addAttribute("typeList", typeRepository.findAll());
            return "videogames/show";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Videogame with id " + id + " not found");
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<Videogame> result = videogameRepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("videogame", result.get());
            model.addAttribute("typeList", typeRepository.findAll());
            return "videogames/edit";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Videogame with id " + id + " not found");
        }
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("videogame") Videogame videogameForm,
                         BindingResult bindingResult) {
        Optional<Videogame> result = videogameRepository.findById(id);
        if (result.isPresent()) {
            if (bindingResult.hasErrors()) {
                System.out.println(bindingResult.getAllErrors());
                return "videogames/edit";
            }
            Videogame savedRecipe = videogameRepository.save(videogameForm);
            return "redirect:/videogames/show/" + id;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Videogame with id " + id + " not found");
        }
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Optional<Videogame> result = videogameRepository.findById(id);
        if (result.isPresent()) {
            videogameRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("redirectMessage",
                    "Videogame " + result.get().getName() + " deleted!");
            return "redirect:/videogames";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Videogame with di " + id + " not found");
        }
    }

}
