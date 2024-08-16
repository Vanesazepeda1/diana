package org.esfe.repository;

import org.esfe.models.user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface iuserrepository extends JpaRepository<user, Integer> {
    user findByEmail(String email);
}