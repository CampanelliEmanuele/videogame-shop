package org.learning.videogameshop.controller;

import jakarta.validation.Valid;
import org.learning.videogameshop.model.User;
import org.learning.videogameshop.model.User;
import org.learning.videogameshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/register/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    public String list(Model model) {
        List<User> userList = userRepository.findAll();
        model.addAttribute("userList", userList);
        return "/register/users/list";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable Integer userId, Model model) {
        Optional<User> result = userRepository.findById(userId);
        if (result.isPresent()) {
            User user = result.get();
            model.addAttribute("user", user);
            return "/register/users/show/" + userId;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + userId + " not found");
        }
    }
    
    @GetMapping("/create")
    public String createUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "/register/users/create";
    }

    @PostMapping("/create")
    public String createUser(@Valid @ModelAttribute("user") User userForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "register/users/create";
        }
        User savedUser = userRepository.save(userForm);
        return "/register/users/show/" + savedUser.getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer userId, Model model) {
        Optional<User> result = userRepository.findById(userId);
        if (result.isPresent()) {
            model.addAttribute("user", result.get());
            return "/register/users/edit/" + userId;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + userId + " not found");
        }
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("user") User videogameForm,
                         BindingResult bindingResult) {
        Optional<User> result = userRepository.findById(id);
        if (result.isPresent()) {
            if (bindingResult.hasErrors()) {
                System.out.println(bindingResult.getAllErrors());
                return "/register/users/edit";
            }
            User savedRecipe = userRepository.save(videogameForm);
            return "register/users/show/" + id;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " not found");
        }
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Optional<User> result = userRepository.findById(id);
        if (result.isPresent()) {
            userRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("redirectMessage",
                        "User " + result.get().getEmail() + " deleted!");
            return "register/users";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with di " + id + " not found");
        }
    }

}
