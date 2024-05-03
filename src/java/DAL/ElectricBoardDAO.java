/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Model.ElectricBoard;
import Model.User;
import Model.elecRegistration;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
//import java.util.Date;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ElectricBoardDAO {
    //

    public ElectricBoardDAO() {

    }
    //

    public List<ElectricBoard> getElectricBoardThangTruoc() {
        List<ElectricBoard> listElectricBoard = new ArrayList<ElectricBoard>();
        String sqlDSCapNhapSoDienThangTruoc = "{call DSCapNhapSoDienThangTruoc()}";
        try (Connection con = DBConnect.getConnection()) {
            CallableStatement cs = con.prepareCall(sqlDSCapNhapSoDienThangTruoc);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ElectricBoard e = new ElectricBoard();
                User admin = new User();
                admin.setId(rs.getInt("id_admin"));
                e.setAdmin(admin);
                elecRegistration elec = new elecRegistration();
                elec.setId(rs.getInt("id_elecregistration"));
                elec.setFullName(rs.getString("fullName"));
                elec.setElecAddress(rs.getString("elecAddress"));
                e.seteRegistration(elec);
                e.setId(rs.getInt("id_electricboard"));
                e.setMeter_code(rs.getString("MaCongTo"));
                e.setMeter_address(rs.getString("DiaChi"));
                e.setPeroid(rs.getString("KiHoaDon"));
                e.setMeter_number(rs.getInt("SoCongTo"));
                e.setTotal_electricity(rs.getInt("TongSoDienDungThangNay"));
                e.setTime_start(rs.getDate("ThoiGianBatDauTinhDien"));
                e.setTime_update(rs.getDate("ThoiGianCapNhatGanNhat"));
                e.setTime_edit(rs.getDate("ThoiGianSuaCuoi"));
                listElectricBoard.add(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listElectricBoard;
    }

    //
    public List<ElectricBoard> getElectricBoardThangTruocTheoTimKiem(String ten, String ma, String diachi) {
        List<ElectricBoard> listElectricBoard = new ArrayList<ElectricBoard>();
        String sqlDSCapNhapSoDienThangTruoc = "{call DSCapNhapSoDienThangTruocTheoTimKiem(?,?,?)}";
        try (Connection con = DBConnect.getConnection()) {
            CallableStatement cs = con.prepareCall(sqlDSCapNhapSoDienThangTruoc);
            cs.setString(1, ten.trim());
            cs.setString(2, ma.trim());
            cs.setString(3, diachi.trim());
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ElectricBoard e = new ElectricBoard();
                User admin = new User();
                admin.setId(rs.getInt("id_admin"));
                e.setAdmin(admin);
                elecRegistration elec = new elecRegistration();
                elec.setId(rs.getInt("id_elecregistration"));
                elec.setFullName(rs.getString("fullName"));
                elec.setElecAddress(rs.getString("elecAddress"));
                e.seteRegistration(elec);
                e.setId(rs.getInt("id_electricboard"));
                e.setMeter_code(rs.getString("MaCongTo"));
                e.setMeter_address(rs.getString("DiaChi"));
                e.setPeroid(rs.getString("KiHoaDon"));
                e.setMeter_number(rs.getInt("SoCongTo"));
                e.setTotal_electricity(rs.getInt("TongSoDienDungThangNay"));
                e.setTime_start(rs.getDate("ThoiGianBatDauTinhDien"));
                e.setTime_update(rs.getDate("ThoiGianCapNhatGanNhat"));
                e.setTime_edit(rs.getDate("ThoiGianSuaCuoi"));
                listElectricBoard.add(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listElectricBoard;
    }

    //
    public ElectricBoard getElectricBoardHienTai(String mc) {
        String sqlDSCapNhapSoDienThangNay = "{call DSCapNhapSoDienThangNay(?)}";
        try (Connection con = DBConnect.getConnection()) {
            CallableStatement cs = con.prepareCall(sqlDSCapNhapSoDienThangNay);
            cs.setString(1, mc);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ElectricBoard e = new ElectricBoard();
                User admin = new User();
                admin.setId(rs.getInt("id_admin"));
                e.setAdmin(admin);
                elecRegistration elec = new elecRegistration();
                elec.setId(rs.getInt("id_elecregistration"));
                elec.setFullName(rs.getString("fullName"));
                elec.setElecAddress(rs.getString("elecAddress"));
                e.seteRegistration(elec);
                e.setId(rs.getInt("id_electricboard"));
                e.setMeter_code(rs.getString("MaCongTo"));
                e.setMeter_address(rs.getString("DiaChi"));
                e.setPeroid(rs.getString("KiHoaDon"));
                e.setMeter_number(rs.getInt("SoCongTo"));
                e.setTotal_electricity(rs.getInt("TongSoDienDungThangNay"));
                e.setTime_start(rs.getDate("ThoiGianBatDauTinhDien"));
                e.setTime_update(rs.getDate("ThoiGianCapNhatGanNhat"));
                e.setTime_edit(rs.getDate("ThoiGianSuaCuoi"));
                return e;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //

    public User getUser(String name, String pass) {
        String sql = "SELECT * FROM user WHERE username=? AND password=?";
        try (Connection con = DBConnect.getConnection()) {
            PreparedStatement cs = con.prepareStatement(sql);
            cs.setString(1, name);
            cs.setString(2, pass);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                User admin = new User();
                admin.setId(rs.getInt("id"));
                admin.setUsername(name);
                admin.setPassword(pass);
                admin.setPhone(rs.getString("phone"));
                admin.setCccd(rs.getString("cccd"));
                admin.setEmail(rs.getString("email"));
                return admin;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean themElectricBoard(ElectricBoard electr, int sd, int tsd, User admin) {
        String sql = "INSERT INTO electricboard(meter_code,meter_address,period,meter_number,total_electricity,time_start,time_edit,time_update,id_admin,id_elecregistration) VALUES(?,?,?,?,?,?,?,?,?,?)";
        int result = 0;
        try (Connection con = DBConnect.getConnection()) {
            Calendar c = Calendar.getInstance();
            int nY = c.get(Calendar.YEAR);
            int nM = c.get(Calendar.MONTH);
            int nD = c.get(Calendar.DAY_OF_MONTH);
            String gd = nY + "-" + String.format("%02d", nM + 1);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, electr.getMeter_code());
            ps.setString(2, electr.getMeter_address());
            ps.setString(3, gd);
            ps.setInt(4, sd);
            ps.setInt(5, tsd);
            ps.setDate(6, electr.getTime_edit());
            ps.setDate(7, new java.sql.Date(nY - 1900, nM, nD));
            ps.setDate(8, new java.sql.Date(nY - 1900, nM, nD));
            ps.setInt(9, admin.getId());
//                        ps.setInt(9,1);
            ps.setInt(10, electr.geteRegistration().getId());
            result = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    public boolean KhoiTaoElectricBoard(elecRegistration e, User admin) {
        String sql = "INSERT INTO electricboard(meter_code,meter_address,period,meter_number,total_electricity,time_start,time_edit,time_update,id_admin,id_elecregistration) VALUES(?,?,?,?,?,?,?,?,?,?)";
        int result = 0;
        String sqlMaxId = "SELECT MAX(id) AS max_id FROM elecregistration";
        try (Connection con = DBConnect.getConnection()) {
            PreparedStatement psMaxId = con.prepareStatement(sqlMaxId);
            ResultSet rsMaxId = psMaxId.executeQuery();
            int maxId = 0;
            if (rsMaxId.next()) {
                maxId = rsMaxId.getInt("max_id");
            }
            rsMaxId.close();
            psMaxId.close();
            Calendar c = Calendar.getInstance();
            int nY = c.get(Calendar.YEAR);
            int nM = c.get(Calendar.MONTH);
            int nD = c.get(Calendar.DAY_OF_MONTH);
            String gd = nY + "-" + String.format("%02d", nM + 1);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "MC" + maxId);
            ps.setString(2, e.getElecAddress());
            ps.setString(3, gd);
            ps.setInt(4, 0);
            ps.setInt(5, 0);
            ps.setDate(6, new java.sql.Date(nY - 1900, nM, nD));
            ps.setDate(7, new java.sql.Date(nY - 1900, nM, nD));
            ps.setDate(8, new java.sql.Date(nY - 1900, nM, nD));
            ps.setInt(9, admin.getId());
            ps.setInt(10, maxId);
            result = ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result > 0;
    }

    public boolean suaElectricBoard(ElectricBoard elect, User admin) {
        String sqlUpdate = "UPDATE electricboard SET meter_code=?,meter_address=?,period=?,meter_number=?,total_electricity=?,time_edit=?,time_update=?,id_admin=?,id_elecregistration=?, e_bill=? WHERE id = ?";
        String sqlTax = "SELECT tax FROM electricity.tax ORDER BY id DESC LIMIT 1";
        String sqlPrice = "SELECT bacThang,donGia, sanLuong FROM electricity.bang_gia_dien";
        //    ORDER BY id ASC";

        String sqlSelectStartTime = "SELECT total_electricity,time_start FROM electricboard WHERE id = ?";
        String sqlUpdateEbill = "UPDATE electricboard SET e_bill=? WHERE id = ?";

        int result = 0;

        try (Connection con = DBConnect.getConnection()) {
            // Sửa thông tin của electricboard
            PreparedStatement psUpdate = con.prepareStatement(sqlUpdate);
            psUpdate.setString(1, elect.getMeter_code());
            psUpdate.setString(2, elect.getMeter_address());
            psUpdate.setString(3, elect.getPeroid());
            psUpdate.setInt(4, elect.getMeter_number());
            psUpdate.setInt(5, elect.getTotal_electricity());
            Calendar c = Calendar.getInstance();
            int nY = c.get(Calendar.YEAR);
            int nM = c.get(Calendar.MONTH);
            int nD = c.get(Calendar.DAY_OF_MONTH);
            psUpdate.setDate(6, new java.sql.Date(nY - 1900, nM, nD));
            psUpdate.setDate(7, elect.getTime_update());
            psUpdate.setInt(8, admin.getId());
            psUpdate.setInt(9, elect.geteRegistration().getId());
            psUpdate.setInt(10, -1);
            psUpdate.setInt(11, elect.getId());
            result = psUpdate.executeUpdate();

            // Truy vấn và in giá trị start_time
            PreparedStatement psSelectStartTime = con.prepareStatement(sqlSelectStartTime);
            psSelectStartTime.setInt(1, elect.getId());
            ResultSet rsStartTime = psSelectStartTime.executeQuery();

            if (rsStartTime.next()) {
                Date startTime = rsStartTime.getDate("time_start");
                Date endTime = new java.sql.Date(nY - 1900, nM, nD);
                System.out.println("Start time: " + startTime);
                System.out.println("End time: " + endTime);
                int total_electricity = rsStartTime.getInt("total_electricity");
                System.out.println("total_electricity" + total_electricity);
                LocalDate startLocalDate = startTime.toLocalDate();
                LocalDate endLocalDate = endTime.toLocalDate();
                System.out.println("Start local date: " + startLocalDate);
                System.out.println("End local date: " + endLocalDate);

                long daysBetween = ChronoUnit.DAYS.between(startLocalDate, endLocalDate);
                int daysInMonth = YearMonth.of(nY, nM + 1).lengthOfMonth();
                System.out.println("Days in month: " + daysInMonth);
                System.out.println("daysBetween: " + daysBetween);

                long tong_tien = 0;

                PreparedStatement psTax = con.prepareStatement(sqlTax);
                ResultSet rsTax = psTax.executeQuery();
                int tax = 0;
                if (rsTax.next()) {
                    tax = rsTax.getInt("tax");
                }
                psTax.close();
                System.out.println("Tax: " + tax + "%");
                PreparedStatement psPrice = con.prepareStatement(sqlPrice);
                ResultSet rsPrice = psPrice.executeQuery();
                while (rsPrice.next()) {
                    int bacThang = rsPrice.getInt("bacThang");
                    int donGia = rsPrice.getInt("donGia");
                    int sanLuong = rsPrice.getInt("sanLuong");
                    long x = Math.round((sanLuong / daysInMonth) * daysBetween);
                    long sl = x;
                    if (total_electricity >= x && sanLuong != 0) {
                        sl = x;
                    } else {
                        sl = total_electricity;
                    }
                    tong_tien = tong_tien + (donGia * sl);
                    System.out.println("Don gia: " + donGia + ", San luong: " + sl + ", Tong tien: " + tong_tien);
                    if (total_electricity >= x && sanLuong != 0) {
                        total_electricity -= x;
                    } else {
                        break;
                    }
                }
                psPrice.close();
                System.out.println("tong_tien " + tong_tien);
                long tien_cuoi = tong_tien + Math.round(tong_tien * tax / 100);
                System.out.println("Final bill: " + tien_cuoi);
                PreparedStatement psUpdateEbill = con.prepareStatement(sqlUpdateEbill);
                psUpdateEbill.setLong(1, tien_cuoi);
                psUpdateEbill.setInt(2, elect.getId());
                int updatedRows = psUpdateEbill.executeUpdate();
                psUpdateEbill.close();
            }
            psSelectStartTime.close();
            psUpdate.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result > 0;
    }
}
