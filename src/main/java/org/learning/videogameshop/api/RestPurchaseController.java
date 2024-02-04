package org.learning.videogameshop.api;

import jakarta.validation.Valid;
import org.learning.videogameshop.model.Purchase;
import org.learning.videogameshop.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping("/api/v1/purchases")
public class RestPurchaseController {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @GetMapping
    public List<Purchase> list() {
        List<Purchase> purchaseList = purchaseRepository.findAll();
        return purchaseList;
    }

    @GetMapping("soldVideogames")
    public Map<String, Integer> getSoldVideogames() {
        List<Purchase> purchaseList = purchaseRepository.findAll();
        Map<String, Integer> purchaseMap = getPurchaseMap(purchaseList);
        return purchaseMap;
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

    /* CRUD */

    @GetMapping("/{id}")
    public Purchase details(@PathVariable Integer id) {
        Optional<Purchase> result = purchaseRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public Purchase create(@Valid @RequestBody Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    @PutMapping("/{id}")
    public Purchase update(@PathVariable Integer id, @RequestBody Purchase purchase) {
        purchase.setId(id);
        return purchaseRepository.save(purchase);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        Optional<Purchase> result = purchaseRepository.findById(id);
        if (result.isPresent()) {
            purchaseRepository.delete(result.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}

