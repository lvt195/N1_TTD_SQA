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

    public List<elecRegistration> getAll() {
        try {
            String query = "SELECT * FROM electricity.elecregistration;";
            con = new DBConnect().getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            List<elecRegistration> list = new ArrayList<>();
            while (rs.next()) {
                elecRegistration elecRegis;
                elecRegis = new elecRegistration(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16));
                list.add(elecRegis);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(elecRegitrationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public elecRegistration getElecbyId(int id) {
        try {
            String query = "select * from electricity.elecregistration where id=?;";
            con = new DBConnect().getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                elecRegistration elecRegis;
                elecRegis = new elecRegistration(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16));
                return elecRegis;
            }

        } catch (SQLException ex) {
            Logger.getLogger(elecRegitrationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public elecRegistration getElecRegisbyphone(String phone) {
        try {
            String query = "SELECT * FROM electricity.elecregistration where phone=?;";
            con = new DBConnect().getConnection();
            if (con != null) {
                System.out.println("tuyen");
            }
            ps = con.prepareStatement(query);
            ps.setString(1, phone);
            rs = ps.executeQuery();
            while (rs.next()) {
                elecRegistration elecRegis;
                elecRegis = new elecRegistration(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16));
                return elecRegis;
            }

        } catch (SQLException ex) {
            Logger.getLogger(elecRegitrationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateElec(int id,String fullName, String phone, String gmail, String placeOfResidence, String elecAddress, String city, String district,String wards, String idCard,String dateOfId, String placeOfId, String phaseNumber, String personalDoc, String dits, String status) {
        try {
            String query = "SELECT * FROM electricity.elecregistration;\n"
                    + "Update electricity.elecregistration\n"
                    + "fullName=?,\n"
                    + "phone=?,\n"
                    + "gmail=?,\n"
                    + "placeOfResidence=?,\n"
                    + "elecAddress=?,\n"
                    + "city=?,\n"
                    + "district=?,\n"
                    + "wards=?,\n"
                    + "idCard=?,\n"
                    + "dateOfId=?,\n"
                    + "placeOfId=?,\n"
                    + "phaseNumber=?,\n"
                    + "personalDoc=?,\n"
                    + "dits=?,\n"
                    + "status=?\n"
                    + "where idElecRegis=?;";
            con = new DBConnect().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1,fullName);
            ps.setString(2,phone);
            ps.setString(3,gmail);
            ps.setString(4,placeOfResidence);
            ps.setString(5,elecAddress);
            ps.setString(6,city);
            ps.setString(7,district);
            ps.setString(8,wards);
            ps.setString(9,idCard);
            ps.setString(10,dateOfId);
            ps.setString(11,placeOfId);
            ps.setString(12,phaseNumber);
            ps.setString(13,personalDoc);
            ps.setString(14,dits);
            ps.setString(15,status);
            ps.setInt(16, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
    }

    public void deleteRegis(int id) {
        try {
            String query = "delete from electricity.elecregistration where idElecRegis=?;";
            con = new DBConnect().getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
        }
    }
}
