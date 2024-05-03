package DAL;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author hi
 */
public class DBConnect {
 
    private static Connection con;
    private static final String URL = "jdbc:mysql://localhost:3306/electricity"; // Đã khởi tạo và sử dụng URL ở đây
    private static final String USER = "root"; // Đã khởi tạo và sử dụng USER ở đây
    private static final String PASSWORD = "thiet19502"; // Đã khởi tạo và sử dụng PASSWORD ở đây
 
    public static Connection getConnection() throws SQLException {
        con = null;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            con = DriverManager.getConnection(URL, USER, PASSWORD); // Sử dụng các biến đã khởi tạo
        } catch (SQLException ex) {
            ex.printStackTrace(); // In stack trace để xem chi tiết lỗi
        }
        return con;
    }

    public static void closeConnection() {
        try {
            if(con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // In stack trace để xem chi tiết lỗi
        }
    }

    public static void closeAll(ResultSet rs, PreparedStatement ps, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
 
}
