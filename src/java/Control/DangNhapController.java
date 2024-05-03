/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control;

import Model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hi
 */
@WebServlet(name = "dangnhapcontroller", urlPatterns = {"/sign-in"})
public class DangNhapController extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            String name = request.getParameter("name");
            String pass = request.getParameter("pass");
            String[] result = User.checkDangNhap(name, pass);
            String VT = result[0];
            String id = result[1];
            String cccd = result[2];
            switch (VT) {
                case "user":
                    session.setAttribute("name", name);
                    session.setAttribute("pass", pass);
                    session.setAttribute("vaitro", VT);
                    session.setAttribute("message", "Chào mừng " + name);
                    session.setAttribute("id_user", id);
                    session.setAttribute("cccd", cccd);
                    response.sendRedirect("gdChinhUser.jsp");
                    break;
                case "admin":
                    session.setAttribute("name", name);
                    session.setAttribute("pass", pass);
                    session.setAttribute("vaitro", VT);
                    session.setAttribute("id_admin", id);
                    session.setAttribute("message", "Chào mừng " + name);
                    response.sendRedirect("gdChinhAdmin.jsp");
                    break;

                default:
                    request.setAttribute("message", "Tên hoặc mật khẩu sai!");
                    response.sendRedirect("dangnhap.jsp");
                    break;
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
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
