
package Control;

import DAL.elecRegitrationDAO;
import Model.elecRegistration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author tuyen
 */
@WebServlet(name = "ManageElecRegis", urlPatterns = {"/ManageElecRegis"})
public class ManageElecRegis extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        elecRegitrationDAO elecDao = new elecRegitrationDAO();
        List<elecRegistration> elecList = elecDao.getAll();
        request.setAttribute("listElecRegis", elecList);
        request.getRequestDispatcher("showListElect.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        writer.println("doPost");
        elecRegitrationDAO elecDao = new elecRegitrationDAO();
        List<elecRegistration> elecList = elecDao.getAll();
        request.setAttribute("listElecRegis", elecList);
        request.getRequestDispatcher("showListElect.jsp").forward(request, response);
    }
}
