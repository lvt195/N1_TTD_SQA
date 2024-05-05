/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control;

import DAL.districtDAO;
import DAL.elecRegitrationDAO;
import Model.district;
import Model.elecRegistration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "UpdateElec", urlPatterns = {"/UpdateElec"})
public class UpdateElec extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateElec</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateElec at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        elecRegitrationDAO dao = new elecRegitrationDAO();

        elecRegistration item = dao.getElecbyId(id);

        List<district> listDis = new districtDAO().getAll();

        request.setAttribute("item", item);

        request.setAttribute("listDis", listDis);
        request.getRequestDispatcher("Update.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String fullName = request.getParameter("fullName");
        String phone = request.getParameter("phone");
        String gmail = request.getParameter("gmail");
        String placeOfResidence = request.getParameter("placeOfResidence");
        String elecAddress = request.getParameter("elecAddress");
        String city = request.getParameter("city");
        String district = request.getParameter("district");
        String wards = request.getParameter("wards");
        String idCard = request.getParameter("idCard");
        String dateOfId = request.getParameter("dateOfId");
        String placeOfId = request.getParameter("placeOfId");
        String phaseNumber = request.getParameter("phaseNumber");
        String personalDoc = "a";
        String dits = "b";
        String status = request.getParameter("status");
        elecRegitrationDAO dao= new elecRegitrationDAO();
        dao.updateElec(id,fullName,phone, gmail, placeOfResidence, elecAddress, city, district, wards, idCard, dateOfId, placeOfId, phaseNumber, personalDoc, dits, status);
        response.sendRedirect("ManageElecRegis");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
