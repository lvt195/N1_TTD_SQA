/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control;

import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "DoiMatKhauTrongDataBaseController", urlPatterns = {"/doimatkhautrongdatabasecontroller"})
public class DoiMatKhauTrongDataBaseController extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            String name = (String) session.getAttribute("name");
            String pass = (String) session.getAttribute("pass");
            if (name == null ) response.sendRedirect("dangnhap.jsp");
            if (pass == null ) response.sendRedirect("dangnhap.jsp");
            String crpass = request.getParameter("crpass");
            String newpass1 = request.getParameter("newpass1");
            if (pass.equals(crpass)){
                    if(User.DoiMatKhau(name, newpass1).equals("Yes")) {
                        System.out.println("okeeeeeeeeeeeeeeeeeeeee");
                        session.setAttribute("thongbaocapnhatmatkhau", "Đổi mật khẩu thành công");
                        response.sendRedirect("doimatkhauthanhcong.jsp");
                    }
                    else{
                        session.setAttribute("thongbaocapnhatmatkhau", "Mật khẩu mới không hợp lệ");
                        response.sendRedirect("doimatkhau.jsp");
                    }
               
            }
            else{
               session.setAttribute("thongbaocapnhatmatkhau", "Sai mật khẩu");
               response.sendRedirect("trangchu.jsp");
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
            Logger.getLogger(DoiMatKhauTrongDataBaseController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DoiMatKhauTrongDataBaseController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DoiMatKhauTrongDataBaseController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DoiMatKhauTrongDataBaseController.class.getName()).log(Level.SEVERE, null, ex);
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
