package com.ufpr.tads.web2.beans;

import java.io.Serializable;

public class Sessao implements Serializable{
    private String nome;
    private int id;
    private String role;
    
    public Sessao() {}
    
    public Sessao(int id, String nome, String role){
        this.id   = id;
        this.nome = nome;
        this.role =role;
    }
    
    public int getId(){
        return id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public String getNome(){
        return nome;
    }
}
