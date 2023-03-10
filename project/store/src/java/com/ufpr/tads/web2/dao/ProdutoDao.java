package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class ProdutoDao {
    private final String select = "SELECT id, nome, preco, prazo FROM tb_produto WHERE ativo = TRUE;";
    private final String selectById = "SELECT id, nome, preco, prazo FROM tb_produto WHERE id = ?;";
    private final String insert = "INSERT INTO tb_produto (nome, preco, prazo) VALUES (?,?,?);";
    private final String update = "UPDATE tb_produto SET nome=?, preco=?, prazo=? WHERE id = ?;";
    private final String delete = "UPDATE tb_produto SET ativo= FALSE WHERE id=?;";
    
    public ProdutoDao() {}
    
   
    public ArrayList<Produto> retornaListaProdutos() throws SQLException, ClassNotFoundException
    {
        Connection con = null;
        PreparedStatement pstm = null;

        ArrayList<Produto> produtos = new ArrayList<>();
         try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(select);
            ResultSet rs = pstm.executeQuery();
            
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getInt("preco"));
                produto.setPrazo(rs.getInt("prazo"));
                
                produtos.add(produto);
            }
            return produtos;
        } finally {
            pstm.close();
            con.close();
        }
    }
      
    public Produto adicionaProduto(Produto produto) throws SQLException, ClassNotFoundException
    {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            
            pstm.setString(1, produto.getNome());
            pstm.setInt(2, produto.getPreco());
            pstm.setInt(3, produto.getPrazo());
            
            pstm.executeUpdate();
            ResultSet rsKey = pstm.getGeneratedKeys();
            
            if (rsKey.next())
            {
                Long generatedKey = rsKey.getLong(1);
                int id = generatedKey.intValue();
                produto.setId(id);
            }
            return produto;
        } finally {
            pstm.close();
            con.close();
        }
    }
    
    public Produto retornaProdutoPorId(int id) throws SQLException, ClassNotFoundException
    {
        Connection con = null;
        PreparedStatement pstm = null;

        Produto produto = new Produto();
        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(selectById);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            
            while (rs.next()) {
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getInt("preco"));
                produto.setPrazo(rs.getInt("prazo"));
             
            }
            
            return produto;
        } finally {
            pstm.close();
            con.close();
        }
    }
    
    public boolean modificaProduto(Produto produto) throws SQLException, ClassNotFoundException
    {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(update);
            pstm.setString(1, produto.getNome());
            pstm.setInt(2, produto.getPreco());
            pstm.setInt(3, produto.getPrazo());
            pstm.setInt(4, produto.getId());
            
            int i = pstm.executeUpdate();
            return i > 0;
        } finally {
            pstm.close();
            con.close();
        }
    }
    
    public boolean removeProduto(Produto produto) throws SQLException, ClassNotFoundException
    {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(delete);
            pstm.setLong(1, produto.getId());
            
            int i = pstm.executeUpdate();
            return i > 0;
        } finally {
            pstm.close();
            con.close();
        }
    }
}
