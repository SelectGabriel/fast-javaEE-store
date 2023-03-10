package com.ufpr.tads.web2.beans;

import java.io.Serializable;

public final class Produto implements Serializable{
    private int id;
    private String nome;
    private String descricao;
    private int preco;
    private int prazo;

    public Produto() {}
    
    public Produto(String nome, int preco ,int prazo){
        setNome(nome);
        setPreco(preco);
        setPrazo(prazo);
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
    public int getPreco(){
        return this.preco;
    }
    public void setPreco(int preco){
        this.preco = preco;
    }
    public int getPrazo(){
        return this.prazo;
    }
    public void setPrazo(int prazo){
        this.prazo = prazo;
    }
}
