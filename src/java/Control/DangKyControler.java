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
@WebServlet(name = "dangkycontroller", urlPatterns = {"/sign-up"})
public class DangKyControler extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            
            HttpSession session = request.getSession();
            String name = request.getParameter("username");
            String pass = request.getParameter("password");
            String hovaten = request.getParameter("hovaten");
            String contact = request.getParameter("contact");
            contact= contact.replaceFirst("\\+84", "0");
            String dinhdanh = request.getParameter("dinhdanh");
            
            String res = User.DangKi(name, pass, hovaten, contact, dinhdanh);
            switch (res) {           
                case "contact":
                    session.setAttribute("message", "Số điện thoại/Email đã có người đăng ký, hãy nhập Số điện thoại/Email khác!");
                    response.sendRedirect("dangky.jsp");
                    break;
                case "username":
                    session.setAttribute("message", "Username đã có người đăng ký, hãy nhập username khác!");
                    response.sendRedirect("dangky.jsp");
                    break;
                
                case "dinhdanh":
                    session.setAttribute("message", "Số CMND/CCCD/Hộ chiếu đã có người đăng ký, hãy nhập Số CMND/CCCD/Hộ chiếu khác!");
                    response.sendRedirect("dangky.jsp");
                    break;
                case "OK":
                    response.sendRedirect("dangkythanhcong.jsp");
                    break;
                default:
                    session.setAttribute("message", res);
                    response.sendRedirect("dangky.jsp");
                    break;
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DangKyControler.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DangKyControler.class.getName()).log(Level.SEVERE, null, ex);
        }
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
