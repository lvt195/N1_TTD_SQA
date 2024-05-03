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


@WebServlet("/BaiCoNgoc/getTaxGTGT")
public class TaxController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8"); 
        PrintWriter out = response.getWriter();

        try (Connection conn = DAL.DBConnect.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT tax FROM electricity.tax WHERE id = (SELECT MAX(id) FROM electricity.tax)")) {

            int taxGTGT = 0;

            if (rs.next()) {
                taxGTGT = rs.getInt("tax");
            }

            out.print( taxGTGT );

        } catch (SQLException e) {
            e.printStackTrace();
            out.print("<p>Thuế GTGT <span>Không xác định</span> tiền điện</p>");
        }
    }
}
