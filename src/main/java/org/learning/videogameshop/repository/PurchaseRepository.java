package org.learning.videogameshop.repository;

import org.learning.videogameshop.model.Purchase;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    List<Purchase> findByPurchaseDateAfterOrderByPurchaseDateDesc(LocalDate date);
    Purchase findFirstByOrderByPurchaseDateDesc();


   // List<Purchase> findTopSoldVideogamesLastMonth(LocalDateTime oneMonthAgo);
}

