package com.andes.microAndres.Security.Entity;

import com.andes.microAndres.Security.Enums.RollName;
import com.sun.istack.NotNull;

import javax.persistence.*;

public class Roll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private RollName rollName;

    public Roll() {
    }

    public Roll(RollName rollName) {
        this.rollName = rollName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RollName getRollName() {
        return rollName;
    }

    public void setRollName(RollName rollName) {
        this.rollName = rollName;
    }
}
