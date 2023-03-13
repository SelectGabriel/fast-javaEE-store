package com.ufpr.tads.web2.controllers;

import com.ufpr.tads.web2.beans.Pedido;
import com.ufpr.tads.web2.beans.Pessoa;
import com.ufpr.tads.web2.beans.Produto;
import com.ufpr.tads.web2.beans.Sessao;
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
import static java.lang.Integer.parseInt;

@WebServlet(name = "FuncionarioServlet", urlPatterns = {"/FuncionarioServlet"})
public class FuncionarioServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String acao = request.getParameter("action");
        HttpSession session = request.getSession();
        Sessao logado = (Sessao) session.getAttribute("logado");

        if ((acao != null) && (logado != null) && (logado.getRole().equals("funcionario"))) {
            switch (acao) {
                case "adicionarRoupa" -> {
                    String nome = request.getParameter("nome");
                    String preco = request.getParameter("preco");
                    int preco2 = parseInt(preco.replaceAll(",", "").replaceAll("[.]", ""));
                    String prazo = request.getParameter("prazo");
                    int prazo2 = parseInt(prazo);
                    Produto prod = new Produto(nome, preco2, prazo2);
                    ProdutoFacade.adicionar(prod);
                    response.sendRedirect(request.getContextPath() + "/PagesServlet?action=roupas"); //arrumar
                }
                case "funcionarioBuscarPedido" -> {
                    String numero = (request.getParameter("pedido"));
                    Pedido pedido = PedidoFacade.buscarQualquerPedidoNumero(numero);
                    System.out.println("passei na servlet funcionarioservlet");
                    if (pedido.getNumero() != null) {
                        request.setAttribute("pedido", pedido);
                        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/PagesServlet?action=verPedido");
                        dispatcher.forward(request, response);
                    } else {
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
                case "editarRoupa" -> {
                    int id = parseInt(request.getParameter("id"));

                    Produto produto = ProdutoFacade.getProduto(id);
                    System.out.println(produto.getNome());
                    request.setAttribute("roupa", produto);
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/PagesServlet?action=editarRoupa");
                    dispatcher.forward(request, response);
                }
                case "confirmarEditarRoupa" -> {
                    int id = parseInt(request.getParameter("id"));
                    int prazo = parseInt(request.getParameter("prazo"));
                    String nome = request.getParameter("nome");
                    int preco = parseInt(request.getParameter("preco").replaceAll(",", "").replaceAll("[.]", ""));

                    Produto roupa = new Produto(nome, preco, prazo);
                    roupa.setId(id);
                    ProdutoFacade.editaProduto(roupa);
                    response.sendRedirect("PagesServlet?action=roupas");
                }
                case "confirmarRemoverRoupa" -> {
                    int id = parseInt(request.getParameter("id"));
                    Produto roupa = new Produto();
                    roupa.setId(id);
                    ProdutoFacade.removeProduto(roupa);
                    response.sendRedirect("PagesServlet?action=roupas");
                }
                case "confirmaAdicionarUsuario" -> {
                    String email = request.getParameter("email");
                    String nome = request.getParameter("nome");
                    String senha = request.getParameter("senha");
                    String cpf = request.getParameter("cpf").replaceAll("[.-]", "");

                    Pessoa novoFuncionario = new Pessoa(cpf, nome, email, "", "", senha, "funcionario");
                    PessoaFacade.criptografaSenha(novoFuncionario);

                    PessoaFacade.adicionaFuncionario(novoFuncionario);
                    response.sendRedirect("PagesServlet?action=funcionarioUsuarios");
                }
                case "editarFuncionario" -> {
                    int id = parseInt(request.getParameter("funcionario"));

                    Pessoa funcionario = PessoaFacade.retornaFuncionario(id);
                    request.setAttribute("funcionario", funcionario);
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/PagesServlet?action=editarFuncionario");
                    dispatcher.forward(request, response);
                }
                case "confirmaEditarFuncionario" -> {
                    int id = parseInt(request.getParameter("id"));
                    String email = request.getParameter("email");
                    String nome = request.getParameter("nome");
                    String senha = request.getParameter("senha");
                    String cpf = request.getParameter("cpf").replaceAll("[.-]", "");

                    Pessoa novoFuncionario = new Pessoa(cpf, nome, email, "", "", senha, "funcionario");
                    novoFuncionario.setId(id);
                    PessoaFacade.editaFuncionario(novoFuncionario);

                    response.sendRedirect("PagesServlet?action=funcionarioUsuarios");
                }
                case "confirmarRemoverFuncionario" -> {
                    int id = parseInt(request.getParameter("id"));
                    Pessoa funcionario = new Pessoa();
                    funcionario.setId(id);
                    PessoaFacade.removeFuncionario(funcionario);
                    response.sendRedirect("PagesServlet?action=funcionarioUsuarios");
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
