package com.andes.microAndres.Security.Repository;

import com.andes.microAndres.Security.Entity.User_model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User_model, Integer> {
    Optional<User_model> findBynombreUsuerio(String nombre);
    boolean existsByNombreUsuario(String nombreUsuario);
    boolean existsByEmail(String email);
}
