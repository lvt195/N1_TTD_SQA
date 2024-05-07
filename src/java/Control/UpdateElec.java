/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control;

import DAL.ElectricBoardDAO;
import DAL.districtDAO;
import DAL.elecRegitrationDAO;
import Model.User;
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
import javax.servlet.http.HttpSession;

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
        request.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(request.getParameter("id"));
        elecRegitrationDAO dao = new elecRegitrationDAO();
        elecRegistration item = dao.getElecbyId(id);
        List<district> listDis = new districtDAO().getAll();
        request.setAttribute("item", item);
        request.setAttribute("listDis", listDis);
        request.getRequestDispatcher("UpdateElec.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        elecRegistration e=new elecRegistration();
        int id = Integer.parseInt(request.getParameter("id"));
        e.setId(id);
        String fullName = request.getParameter("fullName");
        e.setFullName(fullName);
        String phone = request.getParameter("phone");
        e.setPhone(phone);
        String gmail = request.getParameter("gmail");
           e.setGmail(gmail);
        String placeOfResidence = request.getParameter("placeOfResidence");
        e.setPlaceOfResidence(placeOfResidence);
        String elecAddress = request.getParameter("elecAddress");
        e.setElecAddress(elecAddress);
        String city = request.getParameter("city");
        e.setCity(city);
        String district = request.getParameter("district");
        e.setDistrict(district);
        String wards = request.getParameter("wards");
        e.setWards(wards);
        String idCard = request.getParameter("idCard");
        e.setIdCard(idCard);
        String dateOfId = request.getParameter("dateOfId");
        e.setDateOfId(dateOfId);
        String placeOfId = request.getParameter("placeOfId");
        e.setPlaceOfId(placeOfId);
        String phaseNumber = request.getParameter("phaseNumber");
        e.setPhaseNumber(phaseNumber);
        String personalDoc = "a";
        e.setPersonalDoc(personalDoc);
        String dits = "b";
        e.setDits(dits);
        String status = request.getParameter("status");
        e.setStatus(status);
        elecRegitrationDAO dao= new elecRegitrationDAO();
        //Thêm vào bảng điện
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("name");
        String pass = (String)session.getAttribute("pass");
        User admin=(new ElectricBoardDAO()).getUser(name, pass);
                //
        dao.updateElec(id,fullName,phone, gmail, placeOfResidence, elecAddress, city, district, wards, idCard, dateOfId, placeOfId, phaseNumber, personalDoc, dits, status);
        //
        (new ElectricBoardDAO()).KhoiTaoElectricBoard(e,admin);
        response.sendRedirect("ManageElecRegis");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
