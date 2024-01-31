package org.learning.videogameshop.repository;

import org.learning.videogameshop.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
