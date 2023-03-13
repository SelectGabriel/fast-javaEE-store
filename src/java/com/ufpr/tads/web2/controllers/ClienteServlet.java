/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.ufpr.tads.web2.controllers;

import com.ufpr.tads.web2.beans.Pedido;
import com.ufpr.tads.web2.beans.Sessao;
import com.ufpr.tads.web2.facade.PedidoFacade;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import org.owasp.encoder.Encode;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;

/**
 *
 * @author gabrieldacunhaafonso
 */
@WebServlet(name = "ClienteServlet", urlPatterns = {"/ClienteServlet"})
public class ClienteServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String acao = request.getParameter("action");
        HttpSession session = request.getSession();
        Sessao logado = (Sessao) session.getAttribute("logado");
        boolean ok = false;

        if ((acao != null) && (logado != null) && (logado.getRole().equals("cliente"))) {
            switch (acao) {
                case "consultaPedidoCliente" -> {
                    String numero = request.getParameter("pedido");
                    String sanitizedNumero = Encode.forHtmlContent(numero);
                    System.out.println(sanitizedNumero);
                    ArrayList<Pedido> pedidos = PedidoFacade.buscaPedidoCliente(logado.getId());
                    for (Pedido pedido : pedidos) {
                        if (pedido.getNumero().equals(sanitizedNumero)) {
                            ok = true;
                            Pedido pedido_completo = PedidoFacade.buscarQualquerPedidoNumero(pedido.getNumero());
                            request.setAttribute("pedido", pedido_completo);
                            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/PagesServlet?action=verPedido");
                            dispatcher.forward(request, response);
                        }
                    }
                    if (!ok) {
                        request.setAttribute("message", "Pedido não encontrado");
                        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/PagesServlet?action=consultaPedido");
                        dispatcher.forward(request, response);
                    }
                }
                case "verPedidoId" -> {
                    int id = parseInt(request.getParameter("id"));

                    Pedido pedido = PedidoFacade.buscarQualquerPedido(id);
                    request.setAttribute("pedido", pedido);
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/PagesServlet?action=verPedido");
                    dispatcher.forward(request, response);
                }
                default -> {
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/PagesServlet?action=home");
                    dispatcher.forward(request, response);
                }
            }
        }else {
            response.sendRedirect("index.jsp");
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
