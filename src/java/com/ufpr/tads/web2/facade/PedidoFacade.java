package com.ufpr.tads.web2.facade;

import com.ufpr.tads.web2.beans.Pedido;
import com.ufpr.tads.web2.beans.PedidoProduto;
import com.ufpr.tads.web2.beans.Pessoa;
import com.ufpr.tads.web2.beans.Produto;
import com.ufpr.tads.web2.beans.Status;
import com.ufpr.tads.web2.dao.PedidoDao;
import com.ufpr.tads.web2.dao.PedidoProdutoDao;
import com.ufpr.tads.web2.dao.PessoaDao;
import com.ufpr.tads.web2.dao.ProdutoDao;
import com.ufpr.tads.web2.dao.StatusDao;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PedidoFacade {
    public static Pedido buscarQualquerPedido(int id){
        try {
            PedidoDao pedidoDao = new PedidoDao();
            return pedidoDao.retornaPedidoPorId(id); 
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PedidoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static Pedido buscarQualquerPedidoNumero(String numero){
        try {
            PedidoDao pedidoDao = new PedidoDao();
            return pedidoDao.retornaPedidoPorNumero(numero); 
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PedidoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
       
    public static Pedido processarNovoPedido(int pessoa_id, int[] ids_produtos){
        boolean sucesso;
        
        Pessoa pessoa = buscaPessoa(pessoa_id);
        ArrayList<Produto> produtos = buscaListaProdutos(ids_produtos);
        Status status = buscaStatus("EM ABERTO");
        Pedido pedido = InsereNovoPedido(pessoa,  produtos, status);
        sucesso = InserePedidoProdutos(pedido, produtos);
        
        return pedido;
    }
    
    public static Pedido rejeitarNovoPedido(int pessoa_id, int[] ids_produtos){
        boolean sucesso;
        
        Pessoa pessoa = buscaPessoa(pessoa_id);
        ArrayList<Produto> produtos = buscaListaProdutos(ids_produtos);
        Status status = buscaStatus("REJEITADO");
        Pedido pedido = InsereNovoPedido(pessoa,  produtos, status);
        sucesso = InserePedidoProdutos(pedido, produtos);
        
        return pedido;
    }
    
    public static boolean alterarStatusPedido(int id_pedido, String novoStatus){
        PedidoDao pd = new PedidoDao();
        StatusDao sd = new StatusDao();
        try {
            Pedido pedido = pd.retornaPedidoPorId(id_pedido);
            Status status = sd.retornaStatusPorNome(novoStatus);
            
            return pd.modificaStatusPedido(pedido, status);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PedidoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    private static Pessoa buscaPessoa(int pessoa_id){
        PessoaDao pd = new PessoaDao();
        Pessoa pessoa = null;
        try {
            pessoa = pd.retornaPessoaPorId(pessoa_id);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PedidoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pessoa;
    }
    
    private static ArrayList<Produto> buscaListaProdutos(int[] ids_produtos){
        
        ArrayList<Produto> produtos = new ArrayList<>();
        ProdutoDao pd = new ProdutoDao();
        
        for (int i = 0; i < ids_produtos.length; i++ ){
            try {
                Produto produto;
                produto = pd.retornaProdutoPorId(ids_produtos[i]);
                produtos.add(produto);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(PedidoFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return produtos;
    }
    
    public static Status buscaStatus(String nome){
        StatusDao sd = new StatusDao();
        Status status = null;
        try {
            status = sd.retornaStatusPorNome(nome);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PedidoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
    
    private static Pedido InsereNovoPedido(Pessoa pessoa, ArrayList<Produto> produtos, Status status){
        Pedido pedido = new Pedido();
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuuMMddHHmmssSSS");
        LocalDateTime now = LocalDateTime.now();
        pedido.setNumero(dtf.format(now));
        
        pedido.setStatus(status);
        pedido.setPessoa(pessoa);
 
        pedido.setPrazo(0);
        pedido.setValor(0);
        for (Produto produto : produtos) { 		      
            pedido.setValor(pedido.getValor() + produto.getPreco());
            if (produto.getPrazo() > pedido.getPrazo()){
                pedido.setPrazo(produto.getPrazo());
            }
        }
        
        PedidoDao pd = new PedidoDao();
        try {
            pedido = pd.adicionaPedido(pedido);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PedidoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pedido;
    }
    
    private static boolean InserePedidoProdutos(Pedido pedido, ArrayList<Produto> produtos){
        PedidoProdutoDao ppd = new PedidoProdutoDao();
        
        ArrayList<PedidoProduto> pedidoProdutos = new ArrayList<>();
        for (Produto produto : produtos) { 
            PedidoProduto pedidoProduto = pedidoProdutos.stream().filter(pp -> pp.getProduto().getId() == produto.getId()).findAny().orElse(null);
            if (pedidoProduto != null){
                pedidoProduto.setQuantidade(pedidoProduto.getQuantidade() + 1);
            } else {
                pedidoProduto = new PedidoProduto();
                pedidoProduto.setPedido(pedido);
                pedidoProduto.setProduto(produto);
                pedidoProduto.setPreco(produto.getPreco());
                pedidoProduto.setPrazo(produto.getPrazo());
                pedidoProduto.setQuantidade(1);
                
                pedidoProdutos.add(pedidoProduto);
            }
        }
        
        for (PedidoProduto pedidoProduto : pedidoProdutos){
            try {
                ppd.adicionaPedidoProduto(pedidoProduto);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(PedidoFacade.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        
        return true;
    }
    
    public static ArrayList<Pedido> buscaPedidoAberto(){
        PedidoDao pd = new PedidoDao();
        try {
            return pd.RetornaListaPedidosPorStatusId(1);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PedidoFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static ArrayList<Pedido> buscaTodosPedidos(){
        PedidoDao pd = new PedidoDao();
        try {
            return pd.RetornaListaPedidos();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PedidoFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static ArrayList<Pedido> buscaPedidoCliente(int id){
        PedidoDao pd = new PedidoDao();
        try {
            return pd.RetornaListaPedidosPorPessoaId(id);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PedidoFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static ArrayList<Pedido> buscaListaPedidosAbertoCliente(int id){
        try {
            PedidoDao pd = new PedidoDao();
            return pd.RetornaListaPedidosPorPessoaIdStatusName(id, "EM ABERTO");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PedidoFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public static ArrayList<Pedido> buscaListaPedidosStatusCliente(int id, String status){
        try {
            PedidoDao pd = new PedidoDao();
            return pd.RetornaListaPedidosPorPessoaIdStatusName(id, status);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PedidoFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static ArrayList<Pedido> buscaListaPedidosStatus(int status){
        try {
            PedidoDao pd = new PedidoDao();
            return pd.RetornaListaPedidosPorStatusId(status);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PedidoFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
