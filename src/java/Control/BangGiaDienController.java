package Control;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/BaiCoNgoc/getData")
public class BangGiaDienController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json"); // Đảm bảo rằng bạn đang trả về JSON
        PrintWriter out = response.getWriter();

        try (Connection conn = DAL.DBConnect.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM electricity.bang_gia_dien")) {

            JSONArray jsonArray = new JSONArray();
            while (rs.next()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("bacThang", rs.getInt("bacThang"));
                jsonObject.put("donGia", rs.getInt("donGia"));
                jsonObject.put("sanLuong", rs.getInt("sanLuong"));
                jsonArray.put(jsonObject);
            }

            out.print(jsonArray.toString());

        } catch (SQLException e) {
            e.printStackTrace();
            out.print("[]");
        }
    }
}
