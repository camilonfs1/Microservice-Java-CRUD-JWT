package com.andes.microAndres.Service;


import com.andes.microAndres.Entity.Producto;
import com.andes.microAndres.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductoService {

    @Autowired //cuando sea necesario crea una instancia de la clase
    ProductoRepository productorespository;

    public List<Producto> list(){
        return productorespository.findAll();
    }

    public Optional<Producto> getOne(int id){
        return productorespository.findById(id);
    }

    public Optional<Producto> getByNombre(String nombre){
        return productorespository.findByNombre(nombre);
    }
    public void save (Producto producto){
        productorespository.save(producto);
    }
    public void delete (int id){
        productorespository.deleteById(id);
    }
    public boolean existeById(int id){
        return productorespository.existsById(id);
    }
    public boolean existeByNombre(String nombre){
        return productorespository.existsByNombre(nombre);
    }

}
