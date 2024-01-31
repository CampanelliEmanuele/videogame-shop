package org.learning.videogameshop.controller;

import jakarta.validation.Valid;
import org.learning.videogameshop.model.User;
import org.learning.videogameshop.repository.RoleRepository;
import org.learning.videogameshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/register/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public String list(Model model) {
        List<User> userList = userRepository.findAll();
        model.addAttribute("userList", userList);
        return "register/users/list";
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Optional<User> result = userRepository.findById(id);
        if (result.isPresent()) {
            User user = result.get();
            model.addAttribute("user", user);
            return "register/users/show";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " not found");
        }
    }
    
    @GetMapping("/create")
    public String createUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("roleSet", roleRepository.findAll());
        return "register/users/create";
    }

    @PostMapping("/create")
    public String createUser(@Valid @ModelAttribute("user") User userForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "register/users/create";
        }
        BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
        userForm.setPassword("{bcrypt}" + bcpe.encode(userForm.getPassword()));

//        userForm.setPassword("{noop}" + userForm.getPassword());
        User savedUser = userRepository.save(userForm);
        return "redirect:/register/users/show/" + savedUser.getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<User> result = userRepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("user", result.get());
            model.addAttribute("roleSet", roleRepository.findAll());
            return "register/users/edit";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " not found");
        }
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("user") User videogameForm,
                         BindingResult bindingResult) {
        Optional<User> result = userRepository.findById(id);
        if (result.isPresent()) {
            if (bindingResult.hasErrors()) {
                System.out.println(bindingResult.getAllErrors());
                return "register/users/edit";
            }
            User savedRecipe = userRepository.save(videogameForm);
            return "redirect:/register/users/show/" + id;
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
            return "redirect:/register/users";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with di " + id + " not found");
        }
    }

}
