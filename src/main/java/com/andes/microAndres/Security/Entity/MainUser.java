package com.andes.microAndres.Security.Entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MainUser implements UserDetails {//Clase encargada de generar la seguridad
    private String name;
    private String nameUser;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority>  authorities;

    public MainUser(String name, String nameUser, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.name = name;
        this.nameUser = nameUser;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static MainUser build(User_model userModel){
        List<GrantedAuthority> authorities = userModel.getRoles().stream().map(roll -> new SimpleGrantedAuthority(roll.getRollName().name())).collect(Collectors.toList());
        return new MainUser(userModel.getName(), userModel.getUserName(), userModel.getEmail(), userModel.getPassword(),authorities );
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return nameUser;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
