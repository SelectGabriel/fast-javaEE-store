/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.ufpr.tads.web2.controllers;

import com.ufpr.tads.web2.beans.Pedido;
import com.ufpr.tads.web2.beans.Pessoa;
import com.ufpr.tads.web2.beans.Produto;
import com.ufpr.tads.web2.beans.Sessao;
import com.ufpr.tads.web2.beans.Status;
import com.ufpr.tads.web2.facade.PedidoFacade;
import com.ufpr.tads.web2.facade.PessoaFacade;
import com.ufpr.tads.web2.facade.ProdutoFacade;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

@WebServlet(name = "PagesServlet", urlPatterns = {"/PagesServlet"})
public class PagesServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("action");
        HttpSession session = request.getSession();
        Sessao logado = (Sessao) session.getAttribute("logado");
        boolean ok = false;

        if ((acao != null) && (logado != null) && (logado.getRole().equals("funcionario"))) {
            ok = true;
            switch (acao) {
                case "home" -> {
                    ArrayList<Pedido> pedidos = PedidoFacade.buscaPedidoAberto();
                    request.setAttribute("pedidosAbertos", pedidos);
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/funcionario/telaInicial.jsp?mensagem=foi");
                    dispatcher.forward(request, response);
                }
                case "funcionarioUsuarios" -> {
                    ArrayList<Pessoa> listaPessoas = PessoaFacade.listaPessoas();
                    request.setAttribute("lista", listaPessoas);
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/funcionario/usuarios.jsp");
                    dispatcher.forward(request, response);

                }
                case "verPedido" -> {
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/verPedido.jsp");
                    dispatcher.forward(request, response);
                }
                case "roupas" -> {
                    ArrayList<Produto> listaRoupas = ProdutoFacade.listar();
                    request.setAttribute("roupas", listaRoupas);
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/funcionario/roupas.jsp");
                    dispatcher.forward(request, response);
                }
                case "novoFuncionario" -> {
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/funcionario/novoUsuario.jsp");
                    dispatcher.forward(request, response);
                }
                case "listaPedidos" -> {
                    ArrayList<Pedido> pedidos = PedidoFacade.buscaTodosPedidos();
                    request.setAttribute("todosPedidos", pedidos);
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/funcionario/listaPedidos.jsp");
                    dispatcher.forward(request, response);
                }
                case "listaPedidosStatus" -> {
                    String status = request.getParameter("status");
                    Status st = PedidoFacade.buscaStatus(status);
                    ArrayList<Pedido> pedidos = PedidoFacade.buscaListaPedidosStatus(st.getId());
                    request.setAttribute("todosPedidos", pedidos);
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/funcionario/listaPedidos.jsp");
                    dispatcher.forward(request, response);
                }
                case "editarRoupa" -> {
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/funcionario/editarRoupa.jsp");
                    dispatcher.forward(request, response);
                }
                case "consultaPedido" -> {
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/funcionario/consulta.jsp");
                    dispatcher.forward(request, response);
                }
                case "relatorios" -> {
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/funcionario/relatorios.jsp");
                    dispatcher.forward(request, response);
                }
                case "novoRoupa" -> {
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/funcionario/novoRoupa.jsp");
                    dispatcher.forward(request, response);
                }
                case "editarFuncionario" -> {
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/funcionario/editarUsuario.jsp");
                    dispatcher.forward(request, response);
                }
                default -> {
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/PagesServlet?action=home");
                    dispatcher.forward(request, response);
                }
            }
        }

        if ((acao != null) && (logado != null) && (logado.getRole().equals("cliente"))) {
            ok = true;
            switch (acao) {
                case "home" -> {
                    ArrayList<Pedido> pedidos = PedidoFacade.buscaListaPedidosAbertoCliente(logado.getId());
                    request.setAttribute("pedidosAbertosCliente", pedidos);
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cliente/telaInicial.jsp");
                    dispatcher.forward(request, response);
                }
                case "novoPedido" -> {
                    ArrayList<Produto> listaRoupas = ProdutoFacade.listar();
                    request.setAttribute("roupas", listaRoupas);
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cliente/novoPedido.jsp");
                    dispatcher.forward(request, response);
                }
                case "listaPedidosCliente" -> {
                    ArrayList<Pedido> pedidos = PedidoFacade.buscaPedidoCliente(logado.getId());
                    request.setAttribute("pedidosCliente", pedidos);
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cliente/listaPedidos.jsp");
                    dispatcher.forward(request, response);
                }
                case "listaPedidosStatusCliente" -> {
                    String status = request.getParameter("status");
                    ArrayList<Pedido> pedidos = PedidoFacade.buscaListaPedidosStatusCliente(logado.getId(), status);
                    request.setAttribute("pedidosCliente", pedidos);
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cliente/listaPedidos.jsp");
                    dispatcher.forward(request, response);
                }
                case "verPedido" -> {
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/verPedido.jsp");
                    dispatcher.forward(request, response);
                }
                case "consultaPedido" -> {
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cliente/consulta.jsp");
                    dispatcher.forward(request, response);
                }
                default -> {
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/PagesServlet?action=home");
                    dispatcher.forward(request, response);
                }
            }
        }
        if (!ok) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
