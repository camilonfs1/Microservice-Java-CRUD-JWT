package com.andes.microAndres.Security.Controller;

import com.andes.microAndres.Dto.Mensaje;
import com.andes.microAndres.Security.Dto.JwtDto;
import com.andes.microAndres.Security.Dto.LoginUser;
import com.andes.microAndres.Security.Dto.NewUser;
import com.andes.microAndres.Security.Entity.Roll;
import com.andes.microAndres.Security.Entity.User_model;
import com.andes.microAndres.Security.Enums.RollName;
import com.andes.microAndres.Security.Jwt.JwtProvider;
import com.andes.microAndres.Security.Service.RollService;
import com.andes.microAndres.Security.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RollService rollService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/new")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NewUser newUser, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity(new Mensaje("campos mal puestos o email invalido"), HttpStatus.BAD_REQUEST);
        }
        if(userService.existsByUserName(newUser.getUserName())){
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(userService.existsByUserEmail(newUser.getEmail())){
            return new ResponseEntity(new Mensaje("ese email ya existe"), HttpStatus.BAD_REQUEST);
        }
        User_model user = new User_model(newUser.getName(),newUser.getUserName(),newUser.getEmail(),passwordEncoder.encode(newUser.getPassword()));
        Set<Roll> rolls = new HashSet<>();
        rolls.add(rollService.getByRollName(RollName.ROLL_USER).
                get());
        if(newUser.getRole().contains("admin")){
            rolls.add(rollService.getByRollName(RollName.ROLL_ADMIN).get());
        }
        user.setRoles(rolls);
        userService.save(user);
        return new ResponseEntity(new Mensaje("Usuario guardado"),HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
        }
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUserName(), loginUser.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
}
