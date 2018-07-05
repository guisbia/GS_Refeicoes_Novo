/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;

import DAOs.DAOPratoPrincipal;
import Entidades.PratoPrincipal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bianc
 */
@WebServlet(name = "PratoPrincipalServlet", urlPatterns = {"/pratoPrincipal"})
public class PratoPrincipalServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            DAOPratoPrincipal daoPratoPrincipal = new DAOPratoPrincipal();
            PratoPrincipal pratoPrincipal = new PratoPrincipal();
            String tabela = "";
            String id = request.getParameter("idPratoPrincipal");
            String nome = request.getParameter("pratoPrincipal");
            String status = request.getParameter("status");
            
            if (request.getParameter("idPratoPrincipal") == "" || request.getParameter("idPratoPrincipal") == null) {
                List<PratoPrincipal> lista = daoPratoPrincipal.listInOrderId();
                for (PratoPrincipal p : lista) {
                    tabela+="<tr>"
                            +"<td>" + p.getIdPratoPrincipal() + "</td>"
                            +"<td>" + p.getNomePratoPrincipal() + "</td>"
                            +"<td>" + p.getStatus() + "</td>"
                            +"</tr>";
                }
            } else{
                inserir(id, nome, status);
                
                List<PratoPrincipal> lista = daoPratoPrincipal.listInOrderId();
                for (PratoPrincipal p : lista) {
                    tabela+="<tr>"
                            +"<td>" + p.getIdPratoPrincipal() + "</td>"
                            +"<td>" + p.getNomePratoPrincipal() + "</td>"
                            +"<td>" + p.getStatus() + "</td>"
                            +"</tr>";
                }
            }
            request.getSession().setAttribute("resultado", tabela);
            response.sendRedirect(request.getContextPath()+"/paginas/listaPratoPrincipal.jsp");
            id = "";
            nome = "";
            status = "";
        }
    }
    
    public void inserir (String id, String nome, String status){
        DAOPratoPrincipal daoPratoPrincipal = new DAOPratoPrincipal();
        PratoPrincipal pratoPrincipal = new PratoPrincipal();
        pratoPrincipal.setIdPratoPrincipal(Integer.valueOf(id));
        pratoPrincipal.setNomePratoPrincipal(nome);
        pratoPrincipal.setStatus(Short.valueOf(status));
        daoPratoPrincipal.atualizar(pratoPrincipal);
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
