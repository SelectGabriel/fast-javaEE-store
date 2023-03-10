package com.ufpr.tads.web2.beans;

import java.io.Serializable;

public final class Status implements Serializable{
    private int id;
    private String nome;
    private String descricao;

    public Status() {}
    
    public Status(String nome, String descricao){
        setNome(nome);
        setDescricao(descricao);
    }   
        
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getNome(){
        return this.nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public String getDescricao(){
        return this.descricao;
    }
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
}
