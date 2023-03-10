/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufpr.tads.web2.facade;

import com.ufpr.tads.web2.beans.Pessoa;
import com.ufpr.tads.web2.dao.PessoaDao;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabrieldacunhaafonso
 */
public class PessoaFacade {
    
    private static final PessoaDao pessoaDao = new PessoaDao();
    
    public static Pessoa verificaSenha(String login,String senha){
        try {
            
            return pessoaDao.checkCredentials(login, senha);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PessoaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static Pessoa retornaUsuario (String login){
        try {            
            return pessoaDao.retornaPessoaPorEmail(login);
           
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PessoaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    
    }
    
    public static Pessoa criptografaSenha (Pessoa pessoa){
        try {
            pessoa.setNovaSenha(pessoa.getSenha());
            
            return pessoa;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PessoaFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static boolean adicionaCliente (Pessoa pessoa){
        try {
            pessoaDao.adicionaPessoa(pessoa);
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PessoaFacade.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
        public static boolean adicionaFuncionario (Pessoa pessoa){
        try {
            pessoaDao.adicionaPessoa(pessoa);
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PessoaFacade.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static ArrayList<Pessoa> listaPessoas (){
        try {
            return pessoaDao.retornaListaFuncionarios();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PessoaFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;

        }
    }
    public static Pessoa retornaFuncionario(int id){
        try {
            return pessoaDao.retornaPessoaPorId(id);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PessoaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static boolean editaFuncionario (Pessoa funcionario){
        try {
            funcionario.setNovaSenha(funcionario.getSenha());
            return pessoaDao.modificaPessoa(funcionario);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PessoaFacade.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PessoaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static boolean removeFuncionario(Pessoa funcionario){
        try {
            return pessoaDao.removePessoa(funcionario);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PessoaFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
