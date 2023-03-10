package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.Pedido;
import com.ufpr.tads.web2.beans.Status;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Timestamp;

public class PedidoDao {
    private final String selectAll = """
                                     SELECT
                                       pedido.id,
                                       pedido.numero,
                                       pedido.status,
                                       sts.nome,
                                       sts.id AS stsid,
                                       pedido.dthrcriacao
                                     FROM
                                       tb_pedido AS pedido
                                       LEFT JOIN tb_status AS sts ON sts.id = pedido.status;""";
    private final String selectByStatusId = "SELECT id, numero, status, pessoa, valor, prazo, dthrcriacao, dthrpagamento FROM tb_pedido where status = ? order by dthrcriacao;";
    private final String selectByPessoaId = "SELECT id, numero, status, pessoa, valor, prazo, dthrcriacao, dthrpagamento FROM tb_pedido where pessoa = ? order by dthrcriacao;";
    private final String selectByPessoaIdAndStatusName = "SELECT p.id, p.numero, p.status, p.pessoa, p.valor, p.prazo, p.dthrcriacao, p.dthrpagamento FROM tb_pedido p, tb_status s where p.pessoa = ? and p.status = s.id and s.nome = ? order by dthrcriacao;";
    private final String selectById = "SELECT id, numero, status, pessoa, valor, prazo, dthrcriacao, dthrpagamento FROM tb_pedido WHERE id = ?;";
    private final String selectByNumero = "SELECT id, numero, status, pessoa, valor, prazo, dthrcriacao, dthrpagamento FROM tb_pedido WHERE numero = ?;";
    private final String insert = "INSERT INTO tb_pedido (numero, status, pessoa, valor, prazo, dthrcriacao) VALUES (?,?,?,?,?,?);";
    private final String update_status = "UPDATE tb_pedido SET status=? WHERE id = ?;";
    private final String update_pago = "UPDATE tb_pedido SET status=?, dthrpagamento=? WHERE id = ?;";
    
    public PedidoDao() {}
   
    public ArrayList<Pedido> RetornaListaPedidos() throws SQLException, ClassNotFoundException
    {
        Connection con = null;
        PreparedStatement pstm = null;

        ArrayList<Pedido> pedidos = new ArrayList<>();
         try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(selectAll);
            ResultSet rs = pstm.executeQuery();
            
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("id"));
                pedido.setNumero(rs.getString("numero"));
                pedido.setDthrcriacao(timestampToCalendar(rs.getTimestamp("dthrcriacao")));
                Status sts = new Status();
                sts.setId(rs.getInt("status"));
                sts.setNome(rs.getString("nome"));
                pedido.setStatus(sts);
                pedidos.add(pedido);
            }
            return pedidos;
        } finally {
            pstm.close();
            con.close();
        }
    }
    
    public ArrayList<Pedido> RetornaListaPedidosPorStatusId(int id) throws SQLException, ClassNotFoundException
    {
        Connection con = null;
        PreparedStatement pstm = null;

        ArrayList<Pedido> pedidos = new ArrayList<>();
         try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(selectByStatusId);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("id"));
                pedido.setNumero(rs.getString("numero"));
                pedido.setValor(rs.getInt("valor"));
                pedido.setPrazo(rs.getInt("prazo"));
                pedido.setDthrcriacao(timestampToCalendar(rs.getTimestamp("dthrcriacao")));
                pedido.setDthrpagamento(timestampToCalendar(rs.getTimestamp("dthrpagamento")));
                
                PessoaDao pessoaDao = new PessoaDao();
                pedido.setPessoa(pessoaDao.retornaPessoaPorId(rs.getInt("pessoa")));
                StatusDao statusDao = new StatusDao();
                pedido.setStatus(statusDao.retornaStatusPorId(rs.getInt("status")));
                
                pedidos.add(pedido);
            }
            return pedidos;
        } finally {
            pstm.close();
            con.close();
        }
    }
    
    public ArrayList<Pedido> RetornaListaPedidosPorPessoaId(int id) throws SQLException, ClassNotFoundException
    {
        Connection con = null;
        PreparedStatement pstm = null;

        ArrayList<Pedido> pedidos = new ArrayList<>();
         try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(selectByPessoaId);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("id"));
                pedido.setNumero(rs.getString("numero"));
                pedido.setValor(rs.getInt("valor"));
                pedido.setPrazo(rs.getInt("prazo"));
                pedido.setDthrcriacao(timestampToCalendar(rs.getTimestamp("dthrcriacao")));
                pedido.setDthrpagamento(timestampToCalendar(rs.getTimestamp("dthrpagamento")));
                
                PessoaDao pessoaDao = new PessoaDao();
                pedido.setPessoa(pessoaDao.retornaPessoaPorId(rs.getInt("pessoa")));
                StatusDao statusDao = new StatusDao();
                pedido.setStatus(statusDao.retornaStatusPorId(rs.getInt("status")));
                
                pedidos.add(pedido);
            }
            return pedidos;
        } finally {
            pstm.close();
            con.close();
        }
    }
        
    public ArrayList<Pedido> RetornaListaPedidosPorPessoaIdStatusName(int id, String statusname) throws SQLException, ClassNotFoundException
    {
        Connection con = null;
        PreparedStatement pstm = null;

        ArrayList<Pedido> pedidos = new ArrayList<>();
         try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(selectByPessoaIdAndStatusName);
            pstm.setInt(1, id);
            pstm.setString(2, statusname);
            ResultSet rs = pstm.executeQuery();
            
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("id"));
                pedido.setNumero(rs.getString("numero"));
                pedido.setValor(rs.getInt("valor"));
                pedido.setPrazo(rs.getInt("prazo"));
                pedido.setDthrcriacao(timestampToCalendar(rs.getTimestamp("dthrcriacao")));
                pedido.setDthrpagamento(timestampToCalendar(rs.getTimestamp("dthrpagamento")));
                
                PessoaDao pessoaDao = new PessoaDao();
                pedido.setPessoa(pessoaDao.retornaPessoaPorId(rs.getInt("pessoa")));
                StatusDao statusDao = new StatusDao();
                pedido.setStatus(statusDao.retornaStatusPorId(rs.getInt("status")));
                
                pedidos.add(pedido);
            }
            return pedidos;
        } finally {
            pstm.close();
            con.close();
        }
    }
    public Pedido retornaPedidoPorId(int id) throws SQLException, ClassNotFoundException
    {
        Connection con = null;
        PreparedStatement pstm = null;

        Pedido pedido = new Pedido();
        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(selectById);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            
            while (rs.next()) {
                pedido.setId(rs.getInt("id"));
                pedido.setNumero(rs.getString("numero"));
                pedido.setValor(rs.getInt("valor"));
                pedido.setPrazo(rs.getInt("prazo"));
                pedido.setDthrcriacao(timestampToCalendar(rs.getTimestamp("dthrcriacao")));
                pedido.setDthrpagamento(timestampToCalendar(rs.getTimestamp("dthrpagamento")));
                
                System.out.println("passei pela pedidoDao");
                PessoaDao pessoaDao = new PessoaDao();
                pedido.setPessoa(pessoaDao.retornaPessoaPorId(rs.getInt("pessoa")));
                StatusDao statusDao = new StatusDao();
                pedido.setStatus(statusDao.retornaStatusPorId(rs.getInt("status")));
                
                PedidoProdutoDao ppd = new PedidoProdutoDao();
                pedido.setProdutos(ppd.RetornaListaPedidoProdutosPorPedido(pedido));
            }
            return pedido;
        } finally {
            pstm.close();
            con.close();
        }
    }
    
    public Pedido retornaPedidoPorNumero(String numero) throws SQLException, ClassNotFoundException
    {
        Connection con = null;
        PreparedStatement pstm = null;

        Pedido pedido = new Pedido();
        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(selectByNumero);
            pstm.setString(1, numero);
            ResultSet rs = pstm.executeQuery();
            
            while (rs.next()) {
                pedido.setId(rs.getInt("id"));
                pedido.setNumero(rs.getString("numero"));
                pedido.setValor(rs.getInt("valor"));
                pedido.setPrazo(rs.getInt("prazo"));
                pedido.setDthrcriacao(timestampToCalendar(rs.getTimestamp("dthrcriacao")));
                pedido.setDthrpagamento(timestampToCalendar(rs.getTimestamp("dthrpagamento")));
                
                System.out.println("passei pela pedidoDao");
                PessoaDao pessoaDao = new PessoaDao();
                pedido.setPessoa(pessoaDao.retornaPessoaPorId(rs.getInt("pessoa")));
                StatusDao statusDao = new StatusDao();
                pedido.setStatus(statusDao.retornaStatusPorId(rs.getInt("status")));
                
                PedidoProdutoDao ppd = new PedidoProdutoDao();
                pedido.setProdutos(ppd.RetornaListaPedidoProdutosPorPedido(pedido));
            }
            return pedido;
        } finally {
            pstm.close();
            con.close();
        }
    }
    
    public Pedido adicionaPedido(Pedido pedido) throws SQLException, ClassNotFoundException
    {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            
            pstm.setString(1, pedido.getNumero());
            pstm.setInt(2, pedido.getStatus().getId());
            pstm.setInt(3, pedido.getPessoa().getId());
            pstm.setInt(4, pedido.getValor());
            pstm.setInt(5, pedido.getPrazo());
            pstm.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            
            pstm.executeUpdate();
            ResultSet rsKey = pstm.getGeneratedKeys();
            
            if (rsKey.next())
            {
                Long generatedKey = rsKey.getLong(1);
                int id = generatedKey.intValue();
                pedido.setId(id);
            }
            return pedido;
        } finally {
            pstm.close();
            con.close();
        }
    }
        
    public boolean modificaStatusPedido(Pedido pedido, Status status) throws SQLException, ClassNotFoundException
    {      
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = ConnectionFactory.getConnection();
            
            if ("PAGO".equals(status.getNome())) {
                pstm = con.prepareStatement(update_pago);
                pstm.setInt(1, status.getId());
                pstm.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
                pstm.setInt(3, pedido.getId());
            } else {
                pstm = con.prepareStatement(update_status);
                pstm.setInt(1, status.getId());
                pstm.setInt(2, pedido.getId());
            }
            
            int i = pstm.executeUpdate();
            return i > 0;
        } finally {
            pstm.close();
            con.close();
        }
    }
    
    private static Calendar timestampToCalendar(final Timestamp timestamp) {
        if (timestamp == null)
            return null;
        final Calendar cal = Calendar.getInstance();
        cal.setTime(timestamp);
        return cal;
    }
}
