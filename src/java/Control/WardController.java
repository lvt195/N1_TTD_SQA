
package Control;

import DAL.districtDAO;
import DAL.wardsDAO;
import Model.wards;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author tuyen
 */
@WebServlet(name = "WardController", urlPatterns = {"/WardController"})
public class WardController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String selectedDistrict = request.getParameter("district");
        
        // Tìm id của tỉnh dựa trên tên tỉnh trong cơ sở dữ liệu
        int districtId = findidDistrict(selectedDistrict);
        
        
        
        // Truy vấn cơ sở dữ liệu để lấy danh sách quận huyện tương ứng với id tỉnh được chọn
        List<String> districtList = getDistrictList(districtId, response);
        // Tạo chuỗi HTML chứa danh sách quận huyện
        StringBuilder html = new StringBuilder();
        for (String district : districtList) {
            html.append("<option value='").append(district).append("'>" ).append(district).append("</option>");
        }
        
        // Gửi chuỗi HTML dưới dạng phản hồi Ajax
        response.setContentType("text/html");
       
        response.getWriter().write(html.toString());
    }
    
    // Phương thức tìm id của tỉnh dựa trên tên tỉnh trong cơ sở dữ liệu
    private int findidDistrict(String districtName) {
        int id=new districtDAO().getidDistrict(districtName);
        return id;
    }
    
    // Phương thức truy vấn cơ sở dữ liệu để lấy danh sách quận huyện dựa trên id tỉnh
    private List<String> getDistrictList(int provinceId, HttpServletResponse response) throws IOException {
        // Thực hiện truy vấn cơ sở dữ liệu để lấy danh sách quận huyện tương ứng với id tỉnh được chọn
        // ...
        // Trả về danh sách quận huyện
        List<wards> list = new wardsDAO().getWards(provinceId);
        List<String> ds = new ArrayList<>();
        PrintWriter writer = response.getWriter();
        
        for (wards s:list){
           ds.add(s.getNameOfWard());
        }
        writer.println(222);
        writer.println(ds);
        return ds;
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String selectedDistrict = request.getParameter("district");
        
        // Tìm id của tỉnh dựa trên tên tỉnh trong cơ sở dữ liệu
        int districtId = findidDistrict(selectedDistrict);
        
        
        
        // Truy vấn cơ sở dữ liệu để lấy danh sách quận huyện tương ứng với id tỉnh được chọn
        List<String> districtList = getDistrictList(districtId, response);
        // Tạo chuỗi HTML chứa danh sách quận huyện
        StringBuilder html = new StringBuilder();
        for (String district : districtList) {
            html.append("<option value='").append(district).append("'>" ).append(district).append("</option>");
        }
        
        // Gửi chuỗi HTML dưới dạng phản hồi Ajax
        response.setContentType("text/html");
       
        response.getWriter().write(html.toString());
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}