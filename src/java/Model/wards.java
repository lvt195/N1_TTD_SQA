
package Model;

/**
 *
 * @author tuyen
 */
public class wards {
    private int idWard;
    private int idDistrict;
    private String nameOfWard;
    private String wardType;

    public wards(int idWard, int idDistrict, String nameOfWard, String wardType) {
        this.idWard = idWard;
        this.idDistrict = idDistrict;
        this.nameOfWard = nameOfWard;
        this.wardType = wardType;
    }

    public int getIdWard() {
        return idWard;
    }

    public void setIdWard(int idWard) {
        this.idWard = idWard;
    }

    public int getIdDistrict() {
        return idDistrict;
    }

    public void setIdDistrict(int idDistrict) {
        this.idDistrict = idDistrict;
    }

    public String getNameOfWard() {
        return nameOfWard;
    }

    public void setNameOfWard(String nameOfWard) {
        this.nameOfWard = nameOfWard;
    }

    public String getWardType() {
        return wardType;
    }

    public void setWardType(String wardType) {
        this.wardType = wardType;
    }
    
    
}

