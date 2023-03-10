package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.Pessoa;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PessoaDao {
    private final String selectStaff = "SELECT id, cpf, nome, email, endereco, telefone FROM tb_pessoa where role = 'funcionario';";
    private final String selectById = "SELECT id, cpf, nome, email, endereco, telefone, role FROM tb_pessoa WHERE id = ?;";
    private final String checkCredentials = "SELECT id, cpf, nome, email, endereco, telefone, role FROM tb_pessoa WHERE email = ? and senha = ?;";
    private final String selectByEmail = "SELECT id, cpf, nome, email, endereco, telefone, senha, role FROM tb_pessoa WHERE email = ?;";
    private final String insert = "INSERT INTO tb_pessoa (cpf, nome, email, endereco, telefone, senha, role) VALUES (?,?,?,?,?,?,?);";
    private final String update = "UPDATE tb_pessoa SET cpf=?, nome=?, email=?, endereco=?, telefone=?, senha=?, role=? WHERE id = ?;";
    private final String deleteStaff = "DELETE from tb_pessoa WHERE id=? AND role = 'funcionario';";
    
    public PessoaDao() {}
   
    public ArrayList<Pessoa> retornaListaFuncionarios() throws SQLException, ClassNotFoundException
    {
        Connection con = null;
        PreparedStatement pstm = null;

        ArrayList<Pessoa> pessoas = new ArrayList<>();
         try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(selectStaff);
            ResultSet rs = pstm.executeQuery();
            
            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(rs.getInt("id"));
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setEndereco(rs.getString("endereco"));
                pessoa.setTelefone(rs.getString("telefone"));
                
                pessoas.add(pessoa);
            }
            return pessoas;
        } finally {
            pstm.close();
            con.close();
        }
    }
    
    public Pessoa retornaPessoaPorId(int id) throws SQLException, ClassNotFoundException
    {
        Connection con = null;
        PreparedStatement pstm = null;

        Pessoa pessoa = new Pessoa();
        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(selectById);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            
            while (rs.next()) {
                pessoa.setId(rs.getInt("id"));
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setEndereco(rs.getString("endereco"));
                pessoa.setTelefone(rs.getString("telefone"));
                pessoa.setRole(rs.getString("role"));
            }
            return pessoa;
        } finally {
            pstm.close();
            con.close();
        }
    }
    
    public Pessoa checkCredentials(String email, String senha) throws SQLException, ClassNotFoundException
    {
        Connection con = null;
        PreparedStatement pstm = null;

        Pessoa p = new Pessoa();
        p.setEmail(email);
        try {
            p.setNovaSenha(senha);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PessoaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Pessoa pessoa = new Pessoa();
        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(checkCredentials);
            pstm.setString(1, p.getEmail());
            pstm.setString(2, p.getSenha());
            ResultSet rs = pstm.executeQuery();
            
            while (rs.next()) {
                pessoa.setId(rs.getInt("id"));
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setEndereco(rs.getString("endereco"));
                pessoa.setTelefone(rs.getString("telefone"));
                pessoa.setRole(rs.getString("role"));
            }
            return pessoa;
        } finally {
            pstm.close();
            con.close();
        }
    }
    
    public Pessoa retornaPessoaPorEmail(String email) throws SQLException, ClassNotFoundException
    {
        Connection con = null;
        PreparedStatement pstm = null;

        Pessoa pessoa = new Pessoa();
        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(selectByEmail);
            pstm.setString(1, email);
            ResultSet rs = pstm.executeQuery();
            
            while (rs.next()) {
                pessoa.setId(rs.getInt("id"));
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setEndereco(rs.getString("endereco"));
                pessoa.setTelefone(rs.getString("telefone"));
                pessoa.setSenha(rs.getString("senha"));
                pessoa.setRole(rs.getString("role"));
            }
            return pessoa;
        } finally {
            pstm.close();
            con.close();
        }
    }
    
    public Pessoa adicionaPessoa(Pessoa pessoa) throws SQLException, ClassNotFoundException
    {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            
            pstm.setString(1, pessoa.getCpf());
            pstm.setString(2, pessoa.getNome());
            pstm.setString(3, pessoa.getEmail());
            pstm.setString(4, pessoa.getEndereco());
            pstm.setString(5, pessoa.getTelefone());
            pstm.setString(6, pessoa.getSenha());
            pstm.setString(7, pessoa.getRole());
            
            pstm.executeUpdate();
            ResultSet rsKey = pstm.getGeneratedKeys();
            
            if (rsKey.next())
            {
                Long generatedKey = rsKey.getLong(1);
                int id = generatedKey.intValue();
                pessoa.setId(id);
            }
            return pessoa;
        } finally {
            pstm.close();
            con.close();
        }
    }
        
    public boolean modificaPessoa(Pessoa pessoa) throws SQLException, ClassNotFoundException
    {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(update);
            pstm.setString(1, pessoa.getCpf());
            pstm.setString(2, pessoa.getNome());
            pstm.setString(3, pessoa.getEmail());
            pstm.setString(4, pessoa.getEndereco());
            pstm.setString(5, pessoa.getTelefone());
            pstm.setString(6, pessoa.getSenha());
            pstm.setString(7, pessoa.getRole());
            
            pstm.setInt(8, pessoa.getId());
            
            int i = pstm.executeUpdate();
            return i > 0;
        } finally {
            pstm.close();
            con.close();
        }
    }
    
    public boolean removePessoa(Pessoa pessoa) throws SQLException, ClassNotFoundException
    {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(deleteStaff);
            pstm.setInt(1, pessoa.getId());
            
            int i = pstm.executeUpdate();
            return i > 0;
        } finally {
            pstm.close();
            con.close();
        }
    }
}
