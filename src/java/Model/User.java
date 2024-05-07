/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAL.DBConnect;
import DAL.userDAO;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hi
 */
public class User {

    private int id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String cccd;
    private String role;

    // Constructors
    public User() {
    }

    public User(int id, String username, String password, String email, String phone, String cccd, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.cccd = cccd;
        this.role = role;
    }

    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static String[] checkDangNhap(String name, String pass) {
        return userDAO.checkDangNhap(name, pass);
    }

    public static String DangKi(String name, String pass, String hoten, String contact, String dinhdanh) throws ClassNotFoundException {
        return userDAO.DangKi(name, pass, hoten, contact, dinhdanh);
    }
    public static String DoiMatKhau(String name , String newpass1) throws ClassNotFoundException, SQLException{
        return userDAO.DoiMatKhau(name, newpass1);
    }

}
