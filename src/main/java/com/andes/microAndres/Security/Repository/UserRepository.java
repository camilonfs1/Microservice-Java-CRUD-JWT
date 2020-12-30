package com.andes.microAndres.Security.Repository;

import com.andes.microAndres.Security.Entity.User_model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User_model> findBynombreUsuerio(String nombre);
    boolean existsByNombreUsuario(String nombreUsuario);
    boolean existsByEmail(String email);
}
