package com.andes.microAndres.Security.Entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class User_model {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String name;
    @NotNull
    @Column(unique = true)
    private String UserName;
    @NotNull
    @Column(unique = true)
    private String email;
    @NotNull
    private String password;
    @NotNull
    @ManyToMany
    @JoinTable(name = "user_rol",joinColumns = @JoinColumn(name="iser_id"),
    inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Roll> roles = new HashSet<>();

    public User_model() {
    }

    public User_model(String name, String userName, String email, String password) {
        this.name = name;
        UserName = userName;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Roll> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roll> roles) {
        this.roles = roles;
    }
}
