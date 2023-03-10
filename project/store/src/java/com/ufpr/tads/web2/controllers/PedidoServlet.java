package com.ufpr.tads.web2.controllers;

import com.ufpr.tads.web2.beans.Pedido;
import com.ufpr.tads.web2.beans.Sessao;
import com.ufpr.tads.web2.facade.PedidoFacade;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import static java.lang.Integer.parseInt;

@WebServlet(name = "PedidoServlet", urlPatterns = {"/PedidoServlet"})
public class PedidoServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String acao = request.getParameter("action");
        HttpSession session = request.getSession();
        Sessao logado = (Sessao) session.getAttribute("logado");

        if ((acao != null) && (logado != null) && (logado.getRole().equals("funcionario"))) {
            switch (acao) {
                case "recolher" -> {
                    int id = parseInt(request.getParameter("id"));
                    
                    PedidoFacade.alterarStatusPedido(id, "RECOLHIDO");
                    response.sendRedirect(request.getContextPath() + "/PagesServlet?action=listaPedidos"); //arrumar
                }
                case "lavar" -> {
                    int id = parseInt(request.getParameter("id"));
                    
                    PedidoFacade.alterarStatusPedido(id, "AGUARDANDO PAGAMENTO");
                    response.sendRedirect(request.getContextPath() + "/PagesServlet?action=listaPedidos"); //arrumar
                }
                case "finalizar" -> {
                    int id = parseInt(request.getParameter("id"));
                    
                    PedidoFacade.alterarStatusPedido(id, "FINALIZADO");
                    response.sendRedirect(request.getContextPath() + "/PagesServlet?action=listaPedidos"); //arrumar
                }
            }
        }
        
        if ((acao != null) && (logado != null) && (logado.getRole().equals("cliente"))) {
            switch (acao) {
                case "cancelar" -> {
                    int id = parseInt(request.getParameter("id"));
                    
                    PedidoFacade.alterarStatusPedido(id, "CANCELADO");
                    response.sendRedirect(request.getContextPath() + "/PagesServlet?action=listaPedidosCliente"); //arrumar
                }
                case "pagar" -> {
                    int id = parseInt(request.getParameter("id"));
                    
                    PedidoFacade.alterarStatusPedido(id, "PAGO");
                    response.sendRedirect(request.getContextPath() + "/PagesServlet?action=listaPedidosCliente"); 
                }
                case "fazerpedido" -> {
                    var lista_ids = request.getParameterValues("itens[]");
                    var rejeitar = request.getParameterValues("botao_rejeitar");
                    
                    int[] ids = new int[lista_ids.length];
                    for (int i = 0; i < lista_ids.length; i++){
                        ids[i] = Integer.parseInt(lista_ids[i]);
                    }
                    
                    Pedido p = null;
                    if (rejeitar == null){
                        p = PedidoFacade.processarNovoPedido(logado.getId(), ids);
                    }else{
                        p = PedidoFacade.rejeitarNovoPedido(logado.getId(), ids);
                    }
                    
                    request.setAttribute("pedido", p);
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/PagesServlet?action=verPedido");
                    dispatcher.forward(request, response);

                    //response.sendRedirect(request.getContextPath() + "/PagesServlet?action=listaPedidosCliente"); 
                }     
            }
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
