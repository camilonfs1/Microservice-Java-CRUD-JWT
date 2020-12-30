package com.andes.microAndres.Security.Service;


import com.andes.microAndres.Security.Entity.Roll;
import com.andes.microAndres.Security.Enums.RollName;
import com.andes.microAndres.Security.Repository.RollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RollService {
    @Autowired
    RollRepository rollRepository;

    public Optional<Roll> getByRollName(RollName rollName){
        return rollRepository.findByRolNombre(rollName);
    }
}
