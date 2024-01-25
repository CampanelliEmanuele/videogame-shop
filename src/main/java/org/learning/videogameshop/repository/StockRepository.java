package org.learning.videogameshop.repository;

import org.learning.videogameshop.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository <Stock, Integer> {
}
