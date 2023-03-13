package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.Status;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class StatusDao {
    private final String select = "SELECT id, nome, descricao FROM tb_status;";
    private final String selectById = "SELECT id, nome, descricao FROM tb_status WHERE id = ?;";
    private final String selectByName = "SELECT id, nome, descricao FROM tb_status WHERE nome = ?;";
    
    public StatusDao() {}
    
   
    public ArrayList<Status> retornaListaStatus() throws SQLException, ClassNotFoundException
    {
        Connection con = null;
        PreparedStatement pstm = null;

        ArrayList<Status> statuses = new ArrayList<>();
         try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(select);
            ResultSet rs = pstm.executeQuery();
            
            while (rs.next()) {
                Status status = new Status();
                status.setId(rs.getInt("id"));
                status.setNome(rs.getString("nome"));
                status.setDescricao(rs.getString("descricao"));
                
                statuses.add(status);
            }
            return statuses;
        } finally {
            pstm.close();
            con.close();
        }
    }
          
    public Status retornaStatusPorId(int id) throws SQLException, ClassNotFoundException
    {
        Connection con = null;
        PreparedStatement pstm = null;

        Status status = new Status();
        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(selectById);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            
            while (rs.next()) {
                status.setId(rs.getInt("id"));
                status.setNome(rs.getString("nome"));
                status.setDescricao(rs.getString("descricao")); 
            }
            return status;
        } finally {
            pstm.close();
            con.close();
        }
    }
    
    public Status retornaStatusPorNome(String nome) throws SQLException, ClassNotFoundException
    {
        Connection con = null;
        PreparedStatement pstm = null;

        Status status = new Status();
        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(selectByName);
            pstm.setString(1, nome);
            ResultSet rs = pstm.executeQuery();
            
            while (rs.next()) {
                status.setId(rs.getInt("id"));
                status.setNome(rs.getString("nome"));
                status.setDescricao(rs.getString("descricao")); 
            }
            return status;
        } finally {
            pstm.close();
            con.close();
        }
    }
}
