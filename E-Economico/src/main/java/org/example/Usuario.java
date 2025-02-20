package org.example;

import java.util.ArrayList;

public class Usuario {

    private String nome;
    private String email;
    private String senha;
    private String regimeTrab;
    private float salario;
    public ComplementoDados complemento;
    

    public float getSalario(){return salario;}
    public String getNome() {
        return nome;
    }
    public String getRegime() {
        return regimeTrab;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public String getSenha() {
        return senha;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario(String nome, String email, String senha, String regimeTrab, float salario){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.regimeTrab = regimeTrab;
        this.salario = salario;
        this.complemento = new ComplementoDados(email);

    }

    @Override
    public String toString() {
        return "email: " + email + "\tName: " + nome;
    }


}
