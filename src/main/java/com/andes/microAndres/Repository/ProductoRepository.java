package com.andes.microAndres.Repository;

import com.andes.microAndres.Entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductoRepository  extends JpaRepository<Producto, Integer> {//<Tipo de producot, tipo de dato llave primaria>
    Optional<Producto> findByNombre(String Nombre);
    boolean existsByNombre (String nombre);

}
