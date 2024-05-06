
package Control;

import DAL.districtDAO;
import Model.district;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.elecRegistration;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author tuyen
 */
@WebServlet(name = "ElecRegistration", urlPatterns = {"/ElecRegistration"})
public class ElecRegistration extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<district> listDis = new districtDAO().getAll();
        request.setAttribute("listDis", listDis);
        request.getRequestDispatcher("ElecRegistration.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
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
            String personalDoc = "Thieu";
            String dits = "Thieu";
            String status = "Chờ duyệt";

            String res = elecRegistration.elecRegistration(fullName, phone, gmail,
                    placeOfResidence, elecAddress, city, district, wards, idCard, dateOfId, placeOfId, phaseNumber, personalDoc, dits, status);
            if (res != null) {
                out.println("insert success");
            } else {
                out.println("loi");
            }
        }
        List<district> listDis = new districtDAO().getAll();
        request.setAttribute("listDis", listDis);
        request.getRequestDispatcher("ElecRegistration.jsp").forward(request, response);
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
