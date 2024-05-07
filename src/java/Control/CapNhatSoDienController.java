package Control;

import DAL.ElectricBoardDAO;
import Model.ElectricBoard;
import Model.User;
import Model.elecRegistration;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name="CapNhatSoDienController", urlPatterns={"/capnhatsodien"})
public class CapNhatSoDienController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html");
             PrintWriter out = response.getWriter();
             HttpSession session = request.getSession();
             String name = (String) session.getAttribute("name");
             String pass = (String)session.getAttribute("pass");
//             System.out.println(name+pass);
             User admin=(new ElectricBoardDAO()).getUser(name, pass);//
//             System.out.println(admin.getId());
             int i = Integer.parseInt(request.getParameter("i"));
             int new_number = Integer.parseInt(request.getParameter("new_number"));
             int sodiencu=Integer.parseInt(request.getParameter("sodiencu"));
             int tsd=new_number-sodiencu;
             List<ElectricBoard> lelec=(List<ElectricBoard>) session.getAttribute("lelec");
             if((new ElectricBoardDAO()).themElectricBoard(lelec.get(i), new_number,tsd, admin)) {
            	 response.sendRedirect("gdCapNhatSoDien.jsp");
             }
    } 
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("name");
        String pass = (String)session.getAttribute("pass");
        User admin=(new ElectricBoardDAO()).getUser(name, pass);
        int id_elecregistration=Integer.parseInt(request.getParameter("id_elecregistration"));
        String fullName=(String)request.getParameter("fullName");
        elecRegistration e=new elecRegistration();
        e.setId(id_elecregistration);
        e.setFullName(fullName);
        ElectricBoard electricBoard=new ElectricBoard();
        electricBoard.seteRegistration(e);
        electricBoard.setAdmin(admin);
        int id=Integer.parseInt(request.getParameter("id"));
        electricBoard.setId(id);
        String meter_code=(String)request.getParameter("meter_code");
        electricBoard.setMeter_code(meter_code);
        int i = Integer.parseInt(request.getParameter("i"));
        List<ElectricBoard> lelec=(List<ElectricBoard>) session.getAttribute("lelec");
        String meter_address=lelec.get(i).getMeter_address();
        electricBoard.setMeter_address(meter_address);
        String period=(String)request.getParameter("period");
        electricBoard.setPeroid(period);
        java.sql.Date time_start=java.sql.Date.valueOf(request.getParameter("time_start"));
        electricBoard.setTime_update(time_start);
        java.sql.Date time_update=java.sql.Date.valueOf(request.getParameter("time_update"));
        electricBoard.setTime_update(time_update);
        int sodiencu=Integer.parseInt(request.getParameter("sodiencu"));
        int moi=Integer.parseInt(request.getParameter("Meter_number_new"));
        electricBoard.setMeter_number(moi);
        int tsd=moi-sodiencu;
        electricBoard.setTotal_electricity(tsd);
        if((new ElectricBoardDAO()).suaElectricBoard(electricBoard, admin)) {
        	response.sendRedirect("gdCapNhatSoDien.jsp");
        }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
