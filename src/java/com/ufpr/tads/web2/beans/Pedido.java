package com.ufpr.tads.web2.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public final class Pedido implements Serializable{
    private int id;
    private String numero;
    private Status status;
    private Pessoa pessoa;
    private int valor;
    private int prazo;
    private Calendar dthrcriacao;
    private Calendar dthrpagamento;
    private ArrayList<PedidoProduto> produtos;

    public Pedido() {}
    
    public Pedido(String numero, Status status, Pessoa pessoa, int valor, int prazo, Calendar dthrcriacao, ArrayList<PedidoProduto> produtos){
        setNumero(numero);
        setStatus(status);
        setPessoa(pessoa);
        setValor(valor);
        setPrazo(prazo);
        setDthrcriacao(dthrcriacao);
        setProdutos(produtos);
    }   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getPrazo() {
        return prazo;
    }

    public void setPrazo(int prazo) {
        this.prazo = prazo;
    }

    public Calendar getDthrcriacao() {
        return dthrcriacao;
    }

    public void setDthrcriacao(Calendar dthrcriacao) {
        this.dthrcriacao = dthrcriacao;
    }

    public Calendar getDthrpagamento() {
        return dthrpagamento;
    }

    public void setDthrpagamento(Calendar dthrpagamento) {
        this.dthrpagamento = dthrpagamento;
    }

    public ArrayList<PedidoProduto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<PedidoProduto> produtos) {
        this.produtos = produtos;
    }
    
}
