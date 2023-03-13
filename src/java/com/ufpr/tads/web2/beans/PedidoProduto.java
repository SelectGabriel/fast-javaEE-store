package com.ufpr.tads.web2.beans;

import java.io.Serializable;

public class PedidoProduto implements Serializable{
    private int id;
    private int preco;
    private int prazo;
    private int quantidade;
    private Pedido pedido;
    private Produto produto;

    public PedidoProduto() {}
    
    public PedidoProduto(int preco ,int prazo, int quantidade, Pedido pedido, Produto produto){
        setPreco(preco);
        setPrazo(prazo);
        setQuantidade(quantidade);
        setPedido(pedido);
        setProduto(produto);
    }   
        
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
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
    
    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
