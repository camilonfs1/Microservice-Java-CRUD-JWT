package com.andes.microAndres.Security.Repository;

import com.andes.microAndres.Security.Entity.Roll;
import com.andes.microAndres.Security.Enums.RollName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RollRepository extends JpaRepository<Roll,Integer> {
    Optional<Roll> findByRolNombre(RollName rollName);
}
