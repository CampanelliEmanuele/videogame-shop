package org.learning.videogameshop.repository;

import org.learning.videogameshop.model.Purchase;
import org.learning.videogameshop.model.Videogame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface VideogameRepository extends JpaRepository<Videogame, Integer> {



  //  List<Videogame> findTopSoldVideogamesLastMonth(LocalDateTime oneMonthAgo);

}
