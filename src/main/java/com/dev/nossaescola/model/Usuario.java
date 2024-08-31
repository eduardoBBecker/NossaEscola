package com.dev.nossaescola.model;


public class Usuario {
    
    private int id;
    private String username;
    private String senha;

    public Usuario(int id, String username, String senha) {
        this.id = id;
        this.username = username;
        this.senha = senha;
    }

    public Usuario() {
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
