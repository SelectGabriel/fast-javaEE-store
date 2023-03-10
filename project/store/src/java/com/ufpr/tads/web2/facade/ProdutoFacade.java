/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufpr.tads.web2.facade;

import com.ufpr.tads.web2.beans.Produto;
import com.ufpr.tads.web2.dao.ProdutoDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabrieldacunhaafonso
 */
public class ProdutoFacade {
    
    public static ArrayList<Produto> listar (){
        try {
            ProdutoDao produtoDao = new ProdutoDao();
            return produtoDao.retornaListaProdutos();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProdutoFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static Produto adicionar(Produto produto){
        ProdutoDao produtoDao = new ProdutoDao();
        try {
            return produtoDao.adicionaProduto(produto);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProdutoFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static Produto getProduto(int id){
        try {
            ProdutoDao pd = new ProdutoDao();
            return pd.retornaProdutoPorId(id);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProdutoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static boolean editaProduto(Produto produto){
        try {
            ProdutoDao pd = new ProdutoDao();
            return pd.modificaProduto(produto);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProdutoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public static boolean removeProduto(Produto roupa){
        try {
            ProdutoDao pd = new ProdutoDao();
            pd.removeProduto(roupa);
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProdutoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
