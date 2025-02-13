package org.example;

import java.util.ArrayList;

public class Usuario {
    //email string, name string, senha string, regime string

    private String name;
    private String email;
    private String regime;
    private ArrayList<ReservaEmergencia> reservaEmergencias;
    private ArrayList<Meta> metas;
    private ArrayList<DespesasFixas> despesasFixas;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegime() {
        return regime;
    }

    public void setRegime(String regime) {
        this.regime = regime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "email: " + email + "\tName: " + name;
    }
}
