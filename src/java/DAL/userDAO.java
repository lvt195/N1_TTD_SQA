/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author lvt-195
 */
public class userDAO {
    public static String[] checkDangNhap(String name, String pass) {
        try (Connection con = DBConnect.getConnection()) {
            String[] result = new String[3];
            String select = "select password, role, id, cccd from user where username = ?";
            PreparedStatement ps = con.prepareStatement(select);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String password = rs.getString("password");
                String role = rs.getString("role");
                int id = rs.getInt("id");
                String cccd = rs.getString("cccd"); 
                if (pass.equals(password)) {
                    result[0] = role;
                    result[1] = String.valueOf(id);
                    result[2] = cccd;
                    return result;
                } else {
                    result[0] = "-1";
                    result[1] = "-1";
                    result[2] = "-1";
                    return result;
                }
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new String[]{"-1", "-1"};
        }
    }

    public static String DangKi(String name, String pass, String hoten, String contact, String dinhdanh) throws ClassNotFoundException {
        try (Connection con = DBConnect.getConnection()) {
            System.out.println(con);
            System.out.println("connected!!!");
            String select = "insert into user (username, password, fullname, contact, cccd, role) values('%s', '%s', '%s','%s','%s', 'user');";
            select = String.format(select, name, pass, hoten, contact, dinhdanh);
            PreparedStatement ps = con.prepareStatement(select);
            ps.execute();
            return "OK";
        } catch (SQLException ex) {
            if (ex.getSQLState().equals("23000")) {
                if (ex.getMessage().contains("contact")) {
                    return "contact";
                } else if (ex.getMessage().contains("username")) {
                    return "username";
                } else if (ex.getMessage().contains("cccd")) {
                    return "dinhdanh";
                }
            }
        }
        return null;
    }
    public static String DoiMatKhau(String name , String newpass1) throws ClassNotFoundException, SQLException{
        try (Connection con = DBConnect.getConnection()){
            System.out.println(con);
            System.out.println("connected!!!");
            String select = "update user set password = '%s'  where username = '%s'";
            select = String.format(select, newpass1, name);
            PreparedStatement ps = con.prepareStatement(select);
            ps.execute();
            return "Yes";
        } 
        catch (Exception e) {
            System.out.println("error: " + e);
            return "No";
        }
    }
}
