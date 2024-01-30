package org.learning.videogameshop.repository;

import java.util.Optional;
import org.learning.videogameshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryUserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
}
