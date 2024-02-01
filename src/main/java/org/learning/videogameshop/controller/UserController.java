package org.learning.videogameshop.controller;

import jakarta.validation.Valid;
import org.learning.videogameshop.model.Purchase;
import org.learning.videogameshop.model.Role;
import org.learning.videogameshop.model.User;
import org.learning.videogameshop.repository.RoleRepository;
import org.learning.videogameshop.repository.UserRepository;
import org.learning.videogameshop.security.DatabaseUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

// TODO: La creazione di un nuovo dato User deve avere obbligatoriamente almeno un ruolo selezionato!
// Non deve essere possibile creare uno User senza aver inserito un ruolo.

// Soluzione: assegnare di default il ruolo 'USER'
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

    @GetMapping("/profile")
    public String profile(Authentication authentication, Model model) {
        if (authentication == null)
            return "redirect:/login";

        // Dall'oggetto authentication si estrae il campo principal
        Object principal = authentication.getPrincipal();
        // Si fa il cast da Object a DatabaseUserDetails (il quale contiene, tra le altre cose, l'id dello user)
        DatabaseUserDetails dud = (DatabaseUserDetails) principal;
        // Dal DatabaseUserDetails si estrae l'id dello user e lo si usa per ottenere l'oggetto User con quell'id
        Integer userId = dud.getId();
        Optional<User> result = userRepository.findById(userId);

        if (result.isPresent()) {
            User user = result.get();
            model.addAttribute("user", user);
            return "register/users/profile";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + userId + " not found");
        }
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

        // Creare un oggetto Role con il nome "USER"
        Role userRole = new Role();
        userRole.setName("USER");

        // Creare un Set<Role> contenente il ruolo "USER"
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);

        // Assegnare il Set di ruoli all'utente
        userForm.setRoleSet(roles);

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
            User userToDelete = result.get();
            // Affinché il dato purchase persista anche dopo l'eliminazione di un dato user, si setta a null qualsiasi purchase collegato all'id dell'user eliminato
            List<Purchase> purchaseList = userToDelete.getPurchaseList();
            for (Purchase purchase : purchaseList) {
                purchase.setUser(null);
            }
            userRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("redirectMessage",
                        "User " + result.get().getEmail() + " deleted!");
            return "redirect:/register/users";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with di " + id + " not found");
        }
    }

}
