package com.andes.microAndres.Controller;


import com.andes.microAndres.Dto.Mensaje;
import com.andes.microAndres.Dto.ProductoDto;
import com.andes.microAndres.Entity.Producto;
import com.andes.microAndres.Service.ProductoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  //indica que es una api rest
@RequestMapping("/producto") //URL para este producto
@CrossOrigin(origins = "*") //Posdemos acceder al servicio desde cualquier url
public class ProductoController {

    @Autowired
    ProductoService productoservice;

    @GetMapping("/lista")
    public ResponseEntity<List<Producto>> list(){
        List<Producto> list = productoservice.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Producto> getById(@PathVariable("id") int id){
        if(!productoservice.existeById(id)){
            return new ResponseEntity(new Mensaje("no exite"), HttpStatus.NOT_FOUND);
        }
        Producto producto = productoservice.getOne(id).get();
        return new ResponseEntity(producto, HttpStatus.OK);
    }

    @GetMapping("/detailname/{nombre}")
    public ResponseEntity<Producto> getByNombre(@PathVariable("nombre") String nombre){
        if(!productoservice.existeByNombre(nombre)){
            return new ResponseEntity(new Mensaje("no exite"), HttpStatus.NOT_FOUND);
        }
        Producto producto = productoservice.getByNombre(nombre).get();
        return new ResponseEntity(producto, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProductoDto productoDto){ //RequestBody indica que le enviaremos un JSON
        if(StringUtils.isBlank(productoDto.getNombre())){
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio "), HttpStatus.BAD_REQUEST);
        }
        if(productoDto.getPrecio()<0){
            return new ResponseEntity<>(new Mensaje("El el precio es obligatorio o mayor que cero "), HttpStatus.BAD_REQUEST);
        }
        if(productoservice.existeByNombre(productoDto.getNombre())){
            return new ResponseEntity<>(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        Producto producto = new Producto(productoDto.getNombre(),productoDto.getPrecio());
        productoservice.save(producto);
        return new ResponseEntity(new Mensaje("producto creado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id,@RequestBody ProductoDto productoDto){ //RequestBody indica que le enviaremos un JSON
        if(!productoservice.existeById(id)){
            return new ResponseEntity(new Mensaje("no exite"), HttpStatus.NOT_FOUND);
        }

        if(productoservice.existeByNombre(productoDto.getNombre()) && productoservice.getByNombre(productoDto.getNombre()).get().getId() != id){
            return new ResponseEntity<>(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }

        if(StringUtils.isBlank(productoDto.getNombre())){
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio "), HttpStatus.BAD_REQUEST);
        }
        if(productoDto.getPrecio()==null || productoDto.getPrecio()<0 ){
            return new ResponseEntity<>(new Mensaje("El el precio es obligatorio o mayor que cero "), HttpStatus.BAD_REQUEST);
        }

        Producto producto = productoservice.getOne(id).get();
        producto.setNombre(productoDto.getNombre());
        producto.setPrecio(productoDto.getPrecio());
        productoservice.save(producto);
        return new ResponseEntity(new Mensaje("producto actializado"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity <?> Delete(@PathVariable("id") int id) {
        if(!productoservice.existeById(id)){
            return new ResponseEntity(new Mensaje("no exite"), HttpStatus.NOT_FOUND);
        }
        productoservice.delete(id);
        return new ResponseEntity(new Mensaje("producto eliminado"), HttpStatus.OK);
    }


}
