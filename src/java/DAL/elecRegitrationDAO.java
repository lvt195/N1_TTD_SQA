
package DAL;

import Model.elecRegistration;
import com.mysql.cj.xdevapi.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tuyen
 */
public class elecRegitrationDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List<elecRegistration> getAll(){
        try {
            String query="SELECT * FROM electricity.elecregistration;";
            con= new DBConnect().getConnection();
            ps=con.prepareStatement(query);
            rs=ps.executeQuery();
            List<elecRegistration> list= new ArrayList<>();
            while (rs.next()){
                elecRegistration elecRegis;
                elecRegis = new elecRegistration(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16));
                list.add(elecRegis);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(elecRegitrationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public elecRegistration getElecRegisbyphone(String phone){
        try {
            String query="SELECT * FROM electricity.elecregistration where phone=?;";
            con= new DBConnect().getConnection();
            if (con!=null){
                System.out.println("tuyen");
            }
            ps=con.prepareStatement(query);
            ps.setString(1, phone);
            rs=ps.executeQuery();
            while (rs.next()){
                elecRegistration elecRegis;
                elecRegis = new elecRegistration(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16));
                return elecRegis;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(elecRegitrationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
