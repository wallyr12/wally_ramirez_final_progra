/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet(urlPatterns = {"/NewServlet"})
public class NewServlet extends HttpServlet {
    Usuario usuario;

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
        try (PrintWriter out = response.getWriter()) {
            usuario = new Usuario();
            String user = request.getParameter("user");
            String pass = request.getParameter("pass");
            if(user.equals("")||pass.equals("")){
                request.setAttribute("success", 0);
                request.setAttribute("mensaje", "Ingrese todos los campos");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
            
            String usuarioConsultado= usuario.validarUsuario(request.getParameter("user"), request.getParameter("pass"));
            if(usuarioConsultado.equals(request.getParameter("user"))){
               request.getSession().setAttribute("user", request.getParameter("user"));
                request.getSession().setAttribute("pass", request.getParameter("pass"));
                response.sendRedirect(request.getContextPath()+"/LoginController");              
            }else{
                request.setAttribute("success", 0);
                request.setAttribute("mensaje", "Usuario y/o contraseña incorrecta");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
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
