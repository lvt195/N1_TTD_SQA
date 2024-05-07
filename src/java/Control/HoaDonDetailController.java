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
import org.json.JSONObject;
import DAL.HoaDonDAO;
import Model.Bill;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@WebServlet("/BaiCoNgoc/getChiTietHoaDon")
public class HoaDonDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json"); // Đảm bảo rằng bạn đang trả về JSON
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        String vaitro = (String) session.getAttribute("vaitro");

        // Kiểm tra xem session có chứa thông tin đăng nhập không
        if (vaitro == null) {
            // Nếu không phải admin hoặc chưa đăng nhập, trả về mã lỗi 401 Unauthorized
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // Lấy id_bill từ request parameter
        
        String idBillStr = request.getParameter("id");
        if (idBillStr == null || idBillStr.isEmpty()) {
            // Nếu không có id_bill, trả về mã lỗi 400 Bad Request
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            int idBill = Integer.parseInt(idBillStr);
            HoaDonDAO hoaDonDAO = new HoaDonDAO();
            Bill bill = hoaDonDAO.getBillDetail(idBill);

            JSONObject jsonObject = new JSONObject();
            if (bill != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                jsonObject.put("id_bill", bill.getId_bill());
                jsonObject.put("elec_provider", "EVN Hà Nội");
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
                String paymentDueDateStr = sdf.format(payment_due_date);
                jsonObject.put("payment_due_date", paymentDueDateStr);

                jsonObject.put("is_paid", bill.isIs_paid());
                jsonObject.put("id_electricboard", bill.getElectricBoard().getId());
                jsonObject.put("id_admin", bill.getId_admin());
                jsonObject.put("vaitro", vaitro);
            }

            out.print(jsonObject.toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println(e);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String vaitro = (String) session.getAttribute("vaitro");
        if (vaitro == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        
        String id_admin = (String) session.getAttribute("id_admin");
        String idBillStr = request.getParameter("id");
        String paymentStatus = request.getParameter("status");
        
        if (idBillStr == null || idBillStr.isEmpty() || paymentStatus == null || paymentStatus.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        int idBill;
        try {
            idBill = Integer.parseInt(idBillStr);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        HoaDonDAO hoaDonDAO = new HoaDonDAO();

        boolean success = hoaDonDAO.updatePaymentStatusInDatabase(idBill, paymentStatus, id_admin); // Truyền vaitro vào để cập nhật id_admin

        if (success) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Trạng thái thanh toán đã được cập nhật thành công");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Có lỗi xảy ra khi cập nhật trạng thái thanh toán");
        }
    }

}
