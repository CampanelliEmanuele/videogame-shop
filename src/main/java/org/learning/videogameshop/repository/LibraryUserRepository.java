package org.learning.videogameshop.repository;

import java.util.Optional;
import org.learning.videogameshop.model.LibraryUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryUserRepository extends JpaRepository<LibraryUser, Integer> {

    Optional<LibraryUser> findByEmail(String email);
}
