package org.learning.videogameshop.controller;

import org.learning.videogameshop.model.Purchase;
import org.learning.videogameshop.model.Type;
import org.learning.videogameshop.model.Videogame;
import org.learning.videogameshop.repository.PurchaseRepository;
import org.learning.videogameshop.repository.TypeRepository;
import org.learning.videogameshop.repository.VideogameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping
    public String viewGames(Model model) {
        List<Videogame> videogames = videogameRepository.findAll();
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

    @GetMapping("/purchase-success")
    public String purchaseSuccess() {
        return "store/purchase-success";
    }

    @PostMapping("/purchase")
    public String makePurchase(@RequestParam Integer videogameId, @RequestParam int quantity) {
        Videogame videogame = videogameRepository.findById(videogameId)
                .orElseThrow(() -> new RuntimeException("Videogame not found with ID: " + videogameId));

        Purchase purchase = new Purchase();
        purchase.setVideogame(videogame);
        purchase.setPurchaseDate(LocalDate.now());
        purchase.setQuantity(quantity);

        purchaseRepository.save(purchase);

        return "redirect:/store/purchase-success";
    }

    private Map<String, Integer> getPurchaseMap(List<Purchase> lastMonthPurchases) {
        // Set contenente i nomi dei videogame venduti nell'ultimo mese
        Set<String> videogameNames = new HashSet<>();
        for (Purchase purchase : lastMonthPurchases) {
            String iteratedName = purchase.getVideogame().getName();
            videogameNames.add(iteratedName);
        }

        // Inizializzazione della mappa con le quantità iniziali a 0
        Map<String, Integer> purchaseMap = new HashMap<>();
        for (String videogameName : videogameNames) {
            purchaseMap.put(videogameName, 0);
        }

        // Si aggiorna la mappa con le quantità degli acquisti dell'ultimo mese
        for (Purchase purchase : lastMonthPurchases) {
            String iteratedVideogameName = purchase.getVideogame().getName();
            // Ottieni l'attuale quantità del gioco acquistato
            Integer oldValue = purchaseMap.getOrDefault(iteratedVideogameName, 0);
            // Aggiungi la quantità acquistata a quella esistente
            purchaseMap.put(iteratedVideogameName, oldValue + purchase.getQuantity());
        }
        return purchaseMap;
    }
}
