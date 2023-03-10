package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.Pedido;
import com.ufpr.tads.web2.beans.PedidoProduto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PedidoProdutoDao {
    private final String selectByPedido = "SELECT id, pedido, produto, valor, prazo, quantidade FROM tb_pedidoproduto where pedido = ?;";
    private final String insert = "INSERT INTO tb_pedidoproduto (pedido, produto, valor, prazo, quantidade) VALUES (?,?,?,?,?);";
    
    public PedidoProdutoDao() {}
   
    public ArrayList<PedidoProduto> RetornaListaPedidoProdutosPorPedido(Pedido pedido) throws SQLException, ClassNotFoundException
    {
        Connection con = null;
        PreparedStatement pstm = null;

        ArrayList<PedidoProduto> pedidoprodutos = new ArrayList<>();
         try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(selectByPedido);
            pstm.setInt(1, pedido.getId());
            ResultSet rs = pstm.executeQuery();
            
            while (rs.next()) {
                PedidoProduto pedidoproduto = new PedidoProduto();
                pedidoproduto.setId(rs.getInt("id"));
                pedidoproduto.setPreco(rs.getInt("valor"));
                pedidoproduto.setPrazo(rs.getInt("prazo"));
                pedidoproduto.setQuantidade(rs.getInt("quantidade"));

                pedidoproduto.setPedido(pedido);
                ProdutoDao produtoDao = new ProdutoDao();
                pedidoproduto.setProduto(produtoDao.retornaProdutoPorId(rs.getInt("produto")));
                
                pedidoprodutos.add(pedidoproduto);
            }
            return pedidoprodutos;
        } finally {
            pstm.close();
            con.close();
        }
    }
    
       public PedidoProduto adicionaPedidoProduto(PedidoProduto pedidoproduto) throws SQLException, ClassNotFoundException
    {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            
            pstm.setInt(1, pedidoproduto.getPedido().getId());
            pstm.setInt(2, pedidoproduto.getProduto().getId());
            pstm.setInt(3, pedidoproduto.getPreco());
            pstm.setInt(4, pedidoproduto.getPrazo());
            pstm.setInt(5, pedidoproduto.getQuantidade());
            
            pstm.executeUpdate();
            ResultSet rsKey = pstm.getGeneratedKeys();
            
            if (rsKey.next())
            {
                Long generatedKey = rsKey.getLong(1);
                int id = generatedKey.intValue();
                pedidoproduto.setId(id);
            }
            return pedidoproduto;
        } finally {
            pstm.close();
            con.close();
        }
    }}
