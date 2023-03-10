package com.ufpr.tads.web2.controllers;

import com.ufpr.tads.web2.beans.Pessoa;
import com.ufpr.tads.web2.beans.Sessao;
import com.ufpr.tads.web2.facade.PessoaFacade;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String acao = request.getParameter("action");
        HttpSession session = request.getSession();

        if (acao != null) {
            switch (acao) {
                case "login" -> {  // fim :D
                    String login = request.getParameter("login");
                    String senha = request.getParameter("pswd");
                    Pessoa pessoa = PessoaFacade.retornaUsuario(login);
                    Pessoa autenticavel = PessoaFacade.verificaSenha(login, senha);
                    System.out.println(autenticavel.getId());
                    //Resolvido. O usuario vem preenchido aqui em cima. Se tiver id != null, ele foi autenticado.
                    try {
                        if ((campoValidado(login, senha)) && autenticavel.getId() != 0) {
                            Sessao logado = new Sessao(pessoa.getId(), pessoa.getNome(), pessoa.getRole());
                            session.setAttribute("logado", logado);
                            request.setAttribute("user", pessoa);
                            encaminhaPagina("/PagesServlet?action=home", request, response, true);
                        } else {
                            encaminhaPagina("/index.jsp?mensagem=Usuario ou senha incorretos", request, response, false);
                        }
                    } catch (NullPointerException e) {
                        encaminhaPagina("/index.jsp?mensagem=Usuario ou senha incorreta", request, response, false);
                    }
                }

                case "logout" -> {
                    if (session != null) {
                        session.invalidate();
                    }
                    encaminhaPagina("/index.jsp", request, response, false);
                }

                case "cadastrar" -> {
                    String cpf = request.getParameter("cpf").replaceAll("[.-]", "");
                    System.out.println(cpf +" " + cpf.length());
                    String nome = request.getParameter("nome");
                    String email = request.getParameter("email");
                    String telefone = request.getParameter("telefone").replaceAll("[()]", "").replaceAll("[ -]", "");
                    String cep = request.getParameter("cep");
                    String rua = request.getParameter("rua");
                    String numero = request.getParameter("numero");
                    String complemento = request.getParameter("complemento");
                    String cidade = request.getParameter("cidade");
                    String estado = request.getParameter("estado");
                    String senha1 = request.getParameter("senha1");
                    String senha2 = request.getParameter("senha2");
                    
                    if (senha1.equals(senha2)){
                        String endereco = cep.concat(" ").concat(rua).concat(" ").concat(numero).concat("").concat(complemento).concat(cidade).concat(" ").concat(estado);
                        System.out.println(endereco);
                        Pessoa pessoa = new Pessoa(cpf,nome,email,endereco,telefone,senha1,"cliente");
                        pessoa = PessoaFacade.criptografaSenha(pessoa);
                        PessoaFacade.adicionaCliente(pessoa);
                    }
                    else{
                        encaminhaPagina("/index.jsp", request, response, false);
                    }
                    encaminhaPagina("/register.jsp", request, response, false);
                }
            }
        }
    }

    private boolean campoValidado(String login, String senha) {
        return login != null && senha != null && !login.isBlank() && !senha.isBlank();
    }

    private void encaminhaPagina(String target, HttpServletRequest request, HttpServletResponse response, boolean tipo) throws ServletException, IOException {
        if (tipo) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(target);
            dispatcher.forward(request, response);
        } else {
            target = target.replaceFirst("/", "");
            response.sendRedirect(target);
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
