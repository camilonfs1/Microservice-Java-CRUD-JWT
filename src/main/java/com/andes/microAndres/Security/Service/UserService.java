package com.andes.microAndres.Security.Service;


import com.andes.microAndres.Security.Entity.User_model;
import com.andes.microAndres.Security.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional //Mantener la coherencia de la bd si hay varios accesos
//Si hay una incoherencia se hace un rollback
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Optional<User_model> getByNameUser(String userName){
           return userRepository.findBynombreUsuerio(userName);
    }

    public boolean existsByUserName(String userName){
            return userRepository.existsByNombreUsuario(userName);
    }
    public boolean existsByUserEmail(String userEmail){
        return userRepository.existsByEmail(userEmail);
    }

    public void save(User user){
        userRepository.save(user);
    }

}
