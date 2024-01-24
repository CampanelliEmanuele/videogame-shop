package org.learning.videogameshop.repository;

import org.learning.videogameshop.model.Videogame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideogameRepository extends JpaRepository<Videogame, Integer> {
}
