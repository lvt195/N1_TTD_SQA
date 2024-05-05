package DAL;

import Model.Bill;
import Model.elecRegistration;
import Model.ElectricBoard;
import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO {

    public List<Bill> getBillsByUserCCCD(String cccd) throws SQLException {
        List<Bill> bills = new ArrayList<>();
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String q = "SELECT * FROM bills "
                + "INNER JOIN electricboard eb ON bills.id_electricboard = eb.id "
                + "INNER JOIN elecregistration er ON eb.id_elecRegistration = er.id ";
        try {

            if (cccd.equals("1")) {
                ps = conn.prepareStatement(q);
            } else {
                q += "WHERE er.idCard = ?";
                ps = conn.prepareStatement(q);
                ps.setString(1, cccd);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                Bill bill = new Bill();
                bill.setId_bill(rs.getInt("id_bill"));
                ElectricBoard electricBoard = loadElectricBoard(rs.getInt("id_electricboard"));
                elecRegistration elecRegistration = loadElecRegistration(rs.getInt("id_elecregistration"));
                User admin = loadUser(rs.getInt("id_admin"));
                bill.setElectricBoard(electricBoard);
                bill.setElecRegistration(elecRegistration);
//                bill.setId_admin(rs.getInt("id_admin"));
                bill.setIs_paid(rs.getBoolean("is_paid"));
                bill.setTime_pay(rs.getDate("time_pay"));
                bill.setUpdate_at(rs.getDate("update_at"));
                bills.add(bill);
            }
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
        return bills;
    }

    public Bill getBillDetail(int id) throws SQLException {
        Bill bill = null;
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String q = "SELECT * FROM bills "
                + "INNER JOIN electricboard eb ON bills.id_electricboard = eb.id "
                + "INNER JOIN elecregistration er ON eb.id_elecRegistration = er.id "
                + "WHERE bills.id_bill = ?";
        try {
            ps = conn.prepareStatement(q);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) { // Di chuyển con trỏ đến dòng dữ liệu đầu tiên
                bill = new Bill(); // Khởi tạo đối tượng Bill
                bill.setId_bill(rs.getInt("id_bill"));
                ElectricBoard electricBoard = loadElectricBoard(rs.getInt("id_electricboard"));
                elecRegistration elecRegistration = loadElecRegistration(rs.getInt("id_elecregistration"));
                bill.setElectricBoard(electricBoard);
                bill.setElecRegistration(elecRegistration);
                bill.setId_admin(rs.getInt("id_admin"));
                bill.setIs_paid(rs.getBoolean("is_paid"));
                bill.setTime_pay(rs.getDate("time_pay"));
                bill.setUpdate_at(rs.getDate("update_at"));
            }

        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
        return bill;
    }

    public boolean updatePaymentStatusInDatabase(int id_bill, String payment_status, String id_admin) {
    String sql = "UPDATE bills SET is_paid = ?, id_admin = ? WHERE id_bill = ?";
    try (Connection conn = DBConnect.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setBoolean(1, "paid".equals(payment_status));
        stmt.setString(2, id_admin);
        stmt.setInt(3, id_bill);
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    
    public void addBill(Bill bill) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = DBConnect.getConnection(); // Lấy kết nối đến cơ sở dữ liệu
            String sql = "INSERT INTO bills (id_electricboard, id_elecregistration, id_admin, is_paid) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            
            // Thiết lập các tham số cho câu lệnh SQL
            stmt.setInt(1, bill.getElectricBoard().getId());
            stmt.setInt(2, bill.getElecRegistration().getId());
            stmt.setInt(3, bill.getId_admin());
            stmt.setBoolean(4, bill.isIs_paid());

            
            // Thực hiện truy vấn SQL
            int rowsAffected = stmt.executeUpdate();
            
            // Kiểm tra xem câu lệnh đã được thực hiện thành công hay không
            if (rowsAffected == 1) {
                System.out.println("Hóa đơn đã được thêm vào cơ sở dữ liệu thành công.");
            } else {
                System.out.println("Có lỗi xảy ra khi thêm hóa đơn vào cơ sở dữ liệu.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối và tuyên bố đối tượng
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    private ElectricBoard loadElectricBoard(int id) throws SQLException {
        ElectricBoard electricBoard = null;
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM electricboard WHERE id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                electricBoard = new ElectricBoard();
                electricBoard.setPeroid(rs.getString("period"));
                electricBoard.setTime_start(rs.getDate("time_start"));
                electricBoard.setMeter_number(rs.getInt("id"));
                electricBoard.setMeter_code(rs.getString("meter_code"));
                electricBoard.setMeter_address(rs.getString("meter_address"));
                electricBoard.setTime_edit(rs.getDate("time_edit"));
                electricBoard.setTime_update(rs.getDate("time_update"));
                electricBoard.setTotal_electricity(rs.getInt("total_electricity"));
                electricBoard.setE_bill(rs.getLong("e_bill"));
            }
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
        return electricBoard;
    }

    private elecRegistration loadElecRegistration(int id) throws SQLException {
        elecRegistration elecRegistration = null;
        Connection conn = DBConnect.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM elecregistration WHERE id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                elecRegistration = new elecRegistration();
                elecRegistration.setFullName(rs.getString("fullName"));
                elecRegistration.setElecAddress(rs.getString("elecAddress"));
                elecRegistration.setIdCard(rs.getString("idcard"));
                elecRegistration.setStatus(rs.getString("status"));
            }
        } finally {
            DBConnect.closeAll(rs, ps, conn);
        }
        return elecRegistration;
    }

    public User loadUser(int id) {
        User user = null;
        String sql = "SELECT * FROM user WHERE id = ?";
        try (Connection con = DBConnect.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setPhone(rs.getString("phone"));
                user.setCccd(rs.getString("cccd"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
