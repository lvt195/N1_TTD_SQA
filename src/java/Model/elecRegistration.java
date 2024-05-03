
package Model;

import DAL.DBConnect;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author tuyen
 */
public class elecRegistration {
    private int id;
    private String fullName;
    private String phone;
    private String gmail;
    private String placeOfResidence;
    private String elecAddress;
    private String city;
    private String district;
    private String wards;
    private String idCard;
    private String dateOfId;
    private String placeOfId;
    private String phaseNumber;
    private String personalDoc;
    private String dits;
    private String status;

    public elecRegistration() {
    }

    public elecRegistration(int id, String fullName, String phone, String gmail, String placeOfResidence, String elecAddress, String city, String district, String wards, String idCard, String dateOfId, String placeOfId, String phaseNumber, String personalDoc, String dits, String status) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.gmail = gmail;
        this.placeOfResidence = placeOfResidence;
        this.elecAddress = elecAddress;
        this.city = city;
        this.district = district;
        this.wards = wards;
        this.idCard = idCard;
        this.dateOfId = dateOfId;
        this.placeOfId = placeOfId;
        this.phaseNumber = phaseNumber;
        this.personalDoc = personalDoc;
        this.dits = dits;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }   
    
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getPlaceOfResidence() {
        return placeOfResidence;
    }

    public void setPlaceOfResidence(String placeOfResidence) {
        this.placeOfResidence = placeOfResidence;
    }

    public String getElecAddress() {
        return elecAddress;
    }

    public void setElecAddress(String elecAddress) {
        this.elecAddress = elecAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWards() {
        return wards;
    }

    public void setWards(String wards) {
        this.wards = wards;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getDateOfId() {
        return dateOfId;
    }

    public void setDateOfId(String dateOfId) {
        this.dateOfId = dateOfId;
    }

    public String getPlaceOfId() {
        return placeOfId;
    }

    public void setPlaceOfId(String placeOfId) {
        this.placeOfId = placeOfId;
    }

    public String getPhaseNumber() {
        return phaseNumber;
    }

    public void setPhaseNumber(String phaseNumber) {
        this.phaseNumber = phaseNumber;
    }

    public String getPersonalDoc() {
        return personalDoc;
    }

    public void setPersonalDoc(String personalDoc) {
        this.personalDoc = personalDoc;
    }

    public String getDits() {
        return dits;
    }

    public void setDits(String dits) {
        this.dits = dits;
    }

    @Override
    public String toString() {
        return "elecRegistration{" + "id=" + id + ", fullName=" + fullName + ", phone=" + phone + ", gmail=" + gmail + ", placeOfResidence=" + placeOfResidence + ", elecAddress=" + elecAddress + ", city=" + city + ", district=" + district + ", wards=" + wards + ", idCard=" + idCard + ", dateOfId=" + dateOfId + ", placeOfId=" + placeOfId + ", phaseNumber=" + phaseNumber + ", personalDoc=" + personalDoc + ", dits=" + dits + ", status=" + status + '}';
    }
    
    

    public static String elecRegistration(String fullName, String phone, String gmail, String placeOfResidence, String elecAddress, String city, String district,String wards, String idCard,String dateOfId, String placeOfId, String phaseNumber, String personalDoc, String dits, String status) {
        String select = "insert into elecregistration (fullName, phone, gmail, placeOfResidence, elecAddress, city, district, wards, idCard, dateOfId, placeOfId, phaseNumber, personalDoc, dits, status) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try(Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(select);
            ){
                ps.setString(1, fullName);
                ps.setString(2, phone);
                
                ps.setString(3, gmail);
                ps.setString(4, placeOfResidence);
                ps.setString(5, elecAddress);
                ps.setString(6, city);
                ps.setString(7, district);
                ps.setString(8, wards);
                ps.setString(9, idCard);
                ps.setString(10, dateOfId);
                ps.setString(11, placeOfId);
                ps.setString(12, phaseNumber);
                ps.setString(13, personalDoc);
                ps.setString(14, dits);
                ps.setString(15,status);
                ps.executeUpdate();
                return "OK";
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
}
