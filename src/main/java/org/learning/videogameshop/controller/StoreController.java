package org.learning.videogameshop.controller;

import org.learning.videogameshop.model.Purchase;
import org.learning.videogameshop.model.Type;
import org.learning.videogameshop.model.User;
import org.learning.videogameshop.model.Videogame;
import org.learning.videogameshop.repository.PurchaseRepository;
import org.learning.videogameshop.repository.TypeRepository;
import org.learning.videogameshop.repository.UserRepository;
import org.learning.videogameshop.repository.VideogameRepository;
import org.learning.videogameshop.security.DatabaseUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("/store")
public class StoreController {
    @Autowired
    private VideogameRepository videogameRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String viewGames(Model model) {
        int showLimit = 12;
        List<Videogame> videogames = videogameRepository.findAll();
        // Vengono mostrati solo i primi showLimit videogame nello store
        videogames = videogames.subList(0, Math.min(videogames.size(), showLimit));

        List<Type> types = typeRepository.findAll();

        model.addAttribute("videogames", videogames);
        model.addAttribute("types", types);

        LocalDate lastMonth = LocalDate.now().minusMonths(1);
        List<Purchase> lastMonthPurchases = purchaseRepository.findByPurchaseDateAfterOrderByPurchaseDateDesc(lastMonth);
        Map<String, Integer> purchaseMap = getPurchaseMap(lastMonthPurchases);
        List<Map.Entry<String, Integer>> purchasesRanking = new ArrayList<>(purchaseMap.entrySet());
        purchasesRanking.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        model.addAttribute("purchasesRanking", purchasesRanking);
        return "store/list";
    }

    @PostMapping("/purchase")
    public String makePurchase(@RequestParam(required = true) Integer videogameId, @RequestParam(required = true) int quantity, Authentication authentication) {
        // Dall'oggetto authentication si estrae il campo principal
        Object principal = authentication.getPrincipal();
        // Si fa il cast da Object a DatabaseUserDetails (il quale contiene, tra le altre cose, l'id dello user)
        DatabaseUserDetails dud = (DatabaseUserDetails) principal;
        // Dal DatabaseUserDetails si estrae l'id dello user e lo si usa per ottenere l'oggetto User con quell'id
        Integer userId = dud.getId();
        Optional<User> result = userRepository.findById(userId);

        if (result.isPresent()) {
            Videogame videogame = videogameRepository.findById(videogameId)
                    .orElseThrow(() -> new RuntimeException("Videogame not found with ID: " + videogameId));

            Purchase purchase = new Purchase();
            purchase.setUser(result.get());
            purchase.setVideogame(videogame);
            purchase.setPurchaseDate(LocalDate.now());
            purchase.setQuantity(quantity);

            purchaseRepository.save(purchase);

            return "redirect:/purchases";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + userId + " not found");
        }
    }

    private Map<String, Integer> getPurchaseMap(List<Purchase> purchaseList) {
        // Set contenente i nomi dei videogame venduti nell'ultimo mese
        Set<String> videogameNames = new HashSet<>();
        for (Purchase purchase : purchaseList) {
            String iteratedName = purchase.getVideogame().getName();
            videogameNames.add(iteratedName);
        }

        // Inizializzazione della mappa con le quantità iniziali a 0
        Map<String, Integer> purchaseMap = new HashMap<>();
        for (String videogameName : videogameNames) {
            purchaseMap.put(videogameName, 0);
        }

        // Si aggiorna la mappa con le quantità degli acquisti dell'ultimo mese
        for (Purchase purchase : purchaseList) {
            String iteratedVideogameName = purchase.getVideogame().getName();
            // Ottieni l'attuale quantità del gioco acquistato
            Integer oldValue = purchaseMap.getOrDefault(iteratedVideogameName, 0);
            // Aggiungi la quantità acquistata a quella esistente
            purchaseMap.put(iteratedVideogameName, oldValue + purchase.getQuantity());
        }
        return purchaseMap;
    }
}
