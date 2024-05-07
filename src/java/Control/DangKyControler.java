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
//        response.setContentType("text/html;charset=UTF-8");
//        try ( PrintWriter out = response.getWriter()) {
//            
//            HttpSession session = request.getSession();
//            String name = request.getParameter("username");
//            String pass = request.getParameter("password");
//            String hovaten = request.getParameter("hovaten");
//            String contact = request.getParameter("contact");
//            contact= contact.replaceFirst("\\+84", "0");
//            String dinhdanh = request.getParameter("dinhdanh");
//            out.println("before");
//            String res = User.DangKi(name, pass, hovaten, contact, dinhdanh);
//            out.println("before1  " +res );
//            switch (res) {           
//                case "contact":
//                    session.setAttribute("message", "Số điện thoại/Email đã có người đăng ký, hãy nhập Số điện thoại/Email khác!");
//                    out.println("contact="+res);
////                    response.sendRedirect("dangky.jsp");
//                    break;
//                case "username":
//                    session.setAttribute("message", "Username đã có người đăng ký, hãy nhập username khác!");
//                    out.println("username="+res);
////                    response.sendRedirect("dangky.jsp");
//                    break;
//                
//                case "dinhdanh":
//                    session.setAttribute("message", "Số CMND/CCCD/Hộ chiếu đã có người đăng ký, hãy nhập Số CMND/CCCD/Hộ chiếu khác!");
//                    out.println("dinhdanh="+res);
////                    response.sendRedirect("dangky.jsp");
//                    break;
//                case "OK":
//                    response.sendRedirect("dangkythanhcong.jsp");
//                    break;
//                default:
//                    session.setAttribute("message", res);
//                    response.sendRedirect("dangky.jsp");
//                    break;
//            }
//            
//        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
            session.invalidate();
            session = request.getSession();
        request.getRequestDispatcher("dangky.jsp").forward(request, response);
//        try {
//            
//            processRequest(request, response);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(DangKyControler.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) { 
            HttpSession session = request.getSession();
//            session.invalidate();
//            session = request.getSession();
            String name = request.getParameter("username");
            String pass = request.getParameter("password");
            String hovaten = request.getParameter("hovaten");
            String contact = request.getParameter("contact");
            contact= contact.replaceFirst("\\+84", "0");
            String dinhdanh = request.getParameter("dinhdanh");
            out.println("before");
            String res = User.DangKi(name, pass, hovaten, contact, dinhdanh);
            out.println("before1  " +res );
            switch (res) {           
                case "contact":
                    session.setAttribute("message-dangky", "Số điện thoại/Email đã có người đăng ký, hãy nhập Số điện thoại/Email khác!");
                    out.println("contact="+res);
                    request.getRequestDispatcher("dangky.jsp").forward(request, response);
//                    response.sendRedirect("dangky.jsp");
                    break;
                case "username":
                    session.setAttribute("message-dangky", "Username đã có người đăng ký, hãy nhập username khác!");
                    request.getRequestDispatcher("dangky.jsp").forward(request, response);
//                    response.sendRedirect("dangky.jsp");
                    break;
                
                case "dinhdanh":
                    session.setAttribute("message-dangky", "Số CMND/CCCD/Hộ chiếu đã có người đăng ký, hãy nhập Số CMND/CCCD/Hộ chiếu khác!");
                    out.println("dinhdanh="+res);
                    request.getRequestDispatcher("dangky.jsp").forward(request, response);
//                    response.sendRedirect("dangky.jsp");
                    break;
                case "OK":
                    request.getRequestDispatcher("dangkythanhcong.jsp").forward(request, response);
//                    response.sendRedirect("dangkythanhcong.jsp");
                    break;
                default:
                    session.setAttribute("message-dangky", res);
                    request.getRequestDispatcher("dangky.jsp").forward(request, response);
//                    response.sendRedirect("dangky.jsp");
                    break;
            }
            
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
