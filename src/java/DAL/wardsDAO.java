
package DAL;

import Model.wards;
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
public class wardsDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List<wards> getWards(int idDistrict){
        try {
            String query="SELECT * FROM electricity.wards where idDistrict=?;";
            con= new DBConnect().getConnection();
            ps=con.prepareStatement(query);
            ps.setInt(1, idDistrict);
            rs=ps.executeQuery();
            List<wards> list= new ArrayList<>();
            while (rs.next()){
                wards ward =new wards(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4));
                list.add(ward);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(elecRegitrationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null; 
    } 
}
