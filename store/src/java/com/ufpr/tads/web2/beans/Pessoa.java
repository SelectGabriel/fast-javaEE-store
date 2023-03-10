package com.ufpr.tads.web2.beans;

import java.io.Serializable;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Pessoa implements Serializable{
    private int id;
    private String cpf;
    private String nome;
    private String email;
    private String endereco;
    private String telefone;
    private String senha;
    private String role;

    public Pessoa() {}
    
    public Pessoa(String cpf, String nome, String email, String endereco, String telefone, String senha, String role){
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
        this.senha = senha;
        this.role = role;
    }
    
    public Pessoa(String cpf, String nome, String email, String endereco, String telefone, String role){
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
        this.role = role;
    }   
        
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getCpf(){
        return this.cpf;
    }
    public void setCpf(String cpf){
        this.cpf = cpf;
    }
    public String getNome(){
        return this.nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEndereco(){
        return this.endereco;
    }
    public void setEndereco(String endereco){
        this.endereco = endereco;
    }
    public String getTelefone(){
        return this.telefone;
    }
    public void setTelefone(String telefone){
        this.telefone = telefone;
    }
    public String getSenha(){
        return this.senha;
    }
    public void setSenha(String senha){
        this.senha = senha;
    }
    public void setNovaSenha(String senha) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(senha.getBytes(StandardCharsets.UTF_8));
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        
        while (hexString.length() < 64)
        {
            hexString.insert(0, '0');
        }
        
        this.senha = hexString.toString();
    }
    public String getRole(){
        return this.role;
    }
    public void setRole(String role){
        this.role = role;
    }
}
