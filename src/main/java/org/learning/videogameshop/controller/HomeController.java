package org.learning.videogameshop.controller;

import org.learning.videogameshop.model.Purchase;
import org.learning.videogameshop.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private PurchaseRepository purchaseRepository;
    @GetMapping
    public String home(Model model){
        // Lista delle vendite effettuate nell'ultimo mese
        LocalDate lastMonth = LocalDate.now().minusMonths(1);
        List<Purchase> lastMonthPurchases = purchaseRepository.findByPurchaseDateAfterOrderByPurchaseDateDesc(lastMonth);

        // Mappa contenente i nomi dei videogiochi come chiavi e le quantità vendute come valori
        Map<String, Integer> purchaseMap = getPurchaseMap(lastMonthPurchases);

        // Ottenere una lista degli entry dalla mappa
        List<Map.Entry<String, Integer>> purchasesRanking = new ArrayList<>(purchaseMap.entrySet());

        // Ordinamento della lista in base ai valori utilizzando una lambda expression
        purchasesRanking.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        model.addAttribute("purchasesRanking", purchasesRanking);

        return "home/index";
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