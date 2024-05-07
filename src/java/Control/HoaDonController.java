package Control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import org.json.JSONArray;
import org.json.JSONObject;
import DAL.HoaDonDAO;
import Model.Bill;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@WebServlet("/BaiCoNgoc/getHoaDon")
public class HoaDonController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        String cccd = (String) session.getAttribute("cccd");
        String vaitro = (String) session.getAttribute("vaitro");

        if (cccd == null) {
            if ("admin".equals(vaitro)) {
                cccd = "1"; // Đặt giá trị mặc định cho admin
            } else {
                response.sendRedirect("dangnhap.jsp");
                return;
            }
        }


        try {
            HoaDonDAO hoaDonDAO = new HoaDonDAO();
            JSONArray jsonArray = new JSONArray();
            for (Bill bill : hoaDonDAO.getBillsByUserCCCD(cccd)) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id_bill", bill.getId_bill());
                jsonObject.put("meter_code", bill.getElectricBoard().getMeter_code());
                jsonObject.put("fullname", bill.getElecRegistration().getFullName());
                jsonObject.put("elecaddress", bill.getElecRegistration().getElecAddress());
                jsonObject.put("period", bill.getElectricBoard().getPeroid());
                jsonObject.put("time_start", bill.getElectricBoard().getTime_start());
                jsonObject.put("time_edit", bill.getElectricBoard().getTime_edit());
                jsonObject.put("meter_number", bill.getElectricBoard().getMeter_number());
                jsonObject.put("e_bill", bill.getElectricBoard().getE_bill());

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(bill.getElectricBoard().getTime_edit());
                calendar.add(Calendar.DAY_OF_MONTH, 15);
                Date payment_due_date = calendar.getTime();
                // Trước khi đưa vào JSON, định dạng ngày theo định dạng "dd/MM/yyyy"
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String paymentDueDateStr = sdf.format(payment_due_date);
                jsonObject.put("payment_due_date", paymentDueDateStr);
               
                jsonObject.put("is_paid", bill.isIs_paid());
                jsonObject.put("id_electricboard", bill.getElectricBoard().getId());
                jsonObject.put("id_admin", bill.getId_admin());
                jsonObject.put("vaitro", vaitro);
                jsonArray.put(jsonObject);
            }
            out.print(jsonArray.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        
    }
}
