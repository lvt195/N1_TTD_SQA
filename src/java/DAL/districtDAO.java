
package DAL;

/**
 *
 * @author lvt-195
 */
import Model.district;
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
public class districtDAO {
    Connection con=null;
    PreparedStatement ps=null;
    ResultSet rs=null;
    
    
    
    public List<district> getAll(){
        try {
            String query="SELECT * FROM electricity.district;";
            con= new DBConnect().getConnection();
            ps=con.prepareStatement(query);
            rs=ps.executeQuery();
            List<district> list= new ArrayList<>();
            while (rs.next()){
                district dis =new district(rs.getInt(1), rs.getString(2));
                list.add(dis);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(elecRegitrationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null; 
    }
    
    public int getidDistrict(String name){
        try {
            String query="SELECT * FROM electricity.district where district.nameOfDistrict=?;";
            con= new DBConnect().getConnection();
            ps=con.prepareStatement(query);
            ps.setString(1, name);
            rs=ps.executeQuery();
            int id = 0;
            while (rs.next()){
                id=rs.getInt(1);
            }
            return id;
        } catch (SQLException ex) {
            Logger.getLogger(elecRegitrationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0; 
    }
}