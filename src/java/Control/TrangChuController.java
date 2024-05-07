/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mai-NT
 */
@WebServlet(name = "TrangChuController", urlPatterns = {"/trangchucontroller"})
public class TrangChuController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        try {
            String name = (String)session.getAttribute("name");
            String VT = (String)session.getAttribute("vaitro");
            switch (VT) {
                case "admin":
                    session.setAttribute("message", "Chào mừng " + name);
                    request.getRequestDispatcher("gdChinhAdmin.jsp").forward(request, response);
//                    response.sendRedirect("gdChinhAdmin.jsp");
                    break;
                case "user":
                    session.setAttribute("message", "Chào mừng " + name);
                    request.getRequestDispatcher("gdChinhUser.jsp").forward(request, response);
//                    response.sendRedirect("gdChinhUser.jsp");
                    break;
                default:
                    request.getRequestDispatcher("trangchu.jsp").forward(request, response);
//                    response.sendRedirect("trangchu.jsp");
                    break;
            }
        } catch (Exception e) {
            request.getRequestDispatcher("trangchu.jsp").forward(request, response);
//            response.sendRedirect("trangchu.jsp");
        }
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
