package com.ufpr.tads.web2.controllers;

import com.ufpr.tads.web2.beans.Sessao;
import com.ufpr.tads.web2.dao.ConnectionFactory;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Timestamp;  
import java.util.HashMap;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

@WebServlet(name = "RelatorioServlet", urlPatterns = {"/RelatorioServlet"})
public class RelatorioServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String acao = request.getParameter("action");
        HttpSession session = request.getSession();
        Sessao logado = (Sessao) session.getAttribute("logado");

        if ((acao != null) && (logado != null) && (logado.getRole().equals("funcionario"))) {
            switch (acao) {
                case "relatorioClientes" ->{
                    try{
                        Connection con = ConnectionFactory.getConnection();
                        String host = "http://"+ request.getServerName() + ":" + request.getServerPort(); 
                        String jasper = request.getContextPath() + "/funcionario/relatorios/Clientes.jasper";
                        URL jasperURL = new URL(host + jasper);
                        HashMap params = new HashMap();
                        byte[] bytes = JasperRunManager.runReportToPdf(jasperURL.openStream(), params, con);

                        if(bytes!= null) {
                            response.setContentType("application/pdf");
                            OutputStream ops = response.getOutputStream();  
                            ops.write(bytes);  
                        } 
                    }
                    catch(JRException e) {
                      request.setAttribute("mensagem", "Erro no Jasper: " + e.getMessage());
                      request.getRequestDispatcher("error.jsp").forward(request, response);
                    }
                }
                case "relatorioClientesFieis" -> {
                    try{
                        Connection con = ConnectionFactory.getConnection();
                        String host = "http://"+ request.getServerName() + ":" + request.getServerPort(); 
                        String jasper = request.getContextPath() + "/funcionario/relatorios/ClientesFieis.jasper";
                        URL jasperURL = new URL(host + jasper);
                        HashMap params = new HashMap();
                        byte[] bytes = JasperRunManager.runReportToPdf(jasperURL.openStream(), params, con);

                        if(bytes!= null) {
                            response.setContentType("application/pdf");
                            OutputStream ops = response.getOutputStream();  
                            ops.write(bytes);  
                        } 
                    }
                    catch(JRException e) {
                      request.setAttribute("mensagem", "Erro no Jasper: " + e.getMessage());
                      request.getRequestDispatcher("error.jsp").forward(request, response);
                    }
                }
                case "relatorioReceitas" -> {
                    try{
                        Connection con = ConnectionFactory.getConnection();
                        String host = "http://"+ request.getServerName() + ":" + request.getServerPort(); 
                        String jasper = request.getContextPath() + "/funcionario/relatorios/Receita.jasper";
                        URL jasperURL = new URL(host + jasper);
                        
                        String dataDe = request.getParameter("datade");
                        if (dataDe == null || dataDe == ""){
                            dataDe = "2000-01-01";
                        }                            
                        String dataAte = request.getParameter("dataate");
                        if (dataAte == null || dataAte == ""){
                            dataAte = "2100-01-01";
                        } 
                        
                        HashMap params = new HashMap<String, Object>();
                        
                        String date_string = dataDe;
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        Date dateFrom = formatter.parse(date_string); 
                        Timestamp ts1 = new Timestamp(dateFrom.getTime());  
                        params.put("datefrom", ts1);
                        
                        date_string = dataAte;
                        Date dateTo = formatter.parse(date_string); 
                        Timestamp ts2 = new Timestamp(dateTo.getTime());
                        params.put("dateto", ts2);
                        
                        byte[] bytes = JasperRunManager.runReportToPdf(jasperURL.openStream(), params, con);

                        if(bytes!= null) {
                            response.setContentType("application/pdf");
                            OutputStream ops = response.getOutputStream();  
                            ops.write(bytes);  
                        } 
                    }
                    catch(JRException e) {
                      request.setAttribute("mensagem", "Erro no Jasper: " + e.getMessage());
                      request.getRequestDispatcher("error.jsp").forward(request, response);
                    } 
                    catch (ParseException ex) {
                    Logger.getLogger(RelatorioServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                case "relatorioPedidos" -> {
                    try{
                        Connection con = ConnectionFactory.getConnection();
                        String host = "http://"+ request.getServerName() + ":" + request.getServerPort(); 
                        String jasper = request.getContextPath() + "/funcionario/relatorios/Pedidos.jasper";
                        URL jasperURL = new URL(host + jasper);
                        
                        String dataDe = request.getParameter("datade");
                        if (dataDe == null || dataDe == ""){
                            dataDe = "2000-01-01";
                        }                            
                        String dataAte = request.getParameter("dataate");
                        if (dataAte == null || dataAte == ""){
                            dataAte = "2100-01-01";
                        } 
                        
                        HashMap params = new HashMap<String, Object>();
                        
                        String date_string = dataDe;
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        Date dateFrom = formatter.parse(date_string); 
                        Timestamp ts1 = new Timestamp(dateFrom.getTime());  
                        params.put("datefrom", ts1);
                        
                        date_string = dataAte;
                        Date dateTo = formatter.parse(date_string); 
                        
                        Calendar c = Calendar.getInstance(); 
                        c.setTime(dateTo); 
                        c.add(Calendar.DATE, 1);
                        dateTo = c.getTime();
                        
                        Timestamp ts2 = new Timestamp(dateTo.getTime());
                        params.put("dateto", ts2);
                        
                        byte[] bytes = JasperRunManager.runReportToPdf(jasperURL.openStream(), params, con);

                        if(bytes!= null) {
                            response.setContentType("application/pdf");
                            OutputStream ops = response.getOutputStream();  
                            ops.write(bytes);  
                        } 
                    }
                    catch(JRException e) {
                      request.setAttribute("mensagem", "Erro no Jasper: " + e.getMessage());
                      request.getRequestDispatcher("error.jsp").forward(request, response);
                    } 
                    catch (ParseException ex) {
                    Logger.getLogger(RelatorioServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
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
