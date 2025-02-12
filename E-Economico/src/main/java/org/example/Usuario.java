package org.example;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha; 
    private String regimeTrab; 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
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

    public Usuario(String nome, String email, String senha, String regimeTrab){
        this.nome = nome; 
        this.email = email;
        this.senha = senha;
        this.regimeTrab = regimeTrab;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\tName: " + nome;
    }
}
