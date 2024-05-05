//package Model;
//
//import java.util.Date;
//
//public final class Bill {
//    private int id_bill;
//    private String elec_provider;
//    private String fullname;
//    private String elecAddress;
//    private String period;
//    private Date time_start;
//    private Date time_edit;
//    private int meter_number;
//    private int e_bill;
//    private boolean is_paid;
//    private int id_electricboard;
//    private int id_admin;
//
//    public Bill() {
//    }
//
//    public Bill(int id_bill, String elec_provider, String fullname, String elecAddress, String period, Date time_start, Date time_edit, int meter_number, int e_bill, boolean is_paid, int id_electricboard, int id_admin) {
//        this.id_bill = id_bill;
//        this.elec_provider = elec_provider;
//        this.fullname = fullname;
//        this.elecAddress = elecAddress;
//        this.period = period;
//        this.time_start = time_start;
//        this.time_edit = time_edit;
//        this.meter_number = meter_number;
//        this.e_bill = e_bill;
//        this.is_paid = is_paid;
//        this.id_electricboard = id_electricboard;
//        this.id_admin = id_admin;
//    }
//
//    public int getId_bill() {
//        return id_bill;
//    }
//
//    public void setId_bill(int id_bill) {
//        this.id_bill = id_bill;
//    }
//
//    public String getElec_provider() {
//        return elec_provider;
//    }
//
//    public void setElec_provider(String elec_provider) {
//        this.elec_provider = elec_provider;
//    }
//
//    public String getFullname() {
//        return fullname;
//    }
//
//    public void setFullname(String fullname) {
//        this.fullname = fullname;
//    }
//
//    public String getElecAddress() {
//        return elecAddress;
//    }
//
//    public void setElecAddress(String elecAddress) {
//        this.elecAddress = elecAddress;
//    }
//
//    public String getPeriod() {
//        return period;
//    }
//
//    public void setPeriod(String period) {
//        this.period = period;
//    }
//
//    public Date getTime_start() {
//        return time_start;
//    }
//
//    public void setTime_start(Date time_start) {
//        this.time_start = time_start;
//    }
//
//    public Date getTime_edit() {
//        return time_edit;
//    }
//
//    public void setTime_edit(Date time_edit) {
//        this.time_edit = time_edit;
//    }
//
//    public int getMeter_number() {
//        return meter_number;
//    }
//
//    public void setMeter_number(int meter_number) {
//        this.meter_number = meter_number;
//    }
//
//    public int getE_bill() {
//        return e_bill;
//    }
//
//    public void setE_bill(int e_bill) {
//        this.e_bill = e_bill;
//    }
//
//    public boolean isIs_paid() {
//        return is_paid;
//    }
//
//    public void setIs_paid(boolean is_paid) {
//        this.is_paid = is_paid;
//    }
//
//    public int getId_electricboard() {
//        return id_electricboard;
//    }
//
//    public void setId_electricboard(int id_electricboard) {
//        this.id_electricboard = id_electricboard;
//    }
//
//    public int getId_admin() {
//        return id_admin;
//    }
//
//    public void setId_admin(int id_admin) {
//        this.id_admin = id_admin;
//    }
//    
//}

package Model;

import java.util.Date;

public class Bill {
    private int id_bill;
    private ElectricBoard electricBoard;
    private elecRegistration elecRegistration;
    private User user;
    private int id_admin;
    private boolean is_paid;
    private Date time_pay;
    private Date update_at;
    
    public Bill() {
    }
    
    public Bill(int id_bill, ElectricBoard electricBoard, elecRegistration elecRegistration,User user, int id_admin, boolean is_paid, Date time_pay, Date update_at) {
        this.id_bill = id_bill;
        this.electricBoard = electricBoard;
        this.elecRegistration = elecRegistration;
        this.user = user;
        this.id_admin = id_admin;
        this.is_paid = is_paid;
        this.time_pay = time_pay;
        this.update_at = update_at;
    }
    
    public Bill(ElectricBoard electricBoard, elecRegistration elecRegistration,User user, int id_admin) {
        this.electricBoard = electricBoard;
        this.elecRegistration = elecRegistration;
        this.user = user;
        this.id_admin = id_admin;
        this.is_paid = false;
//        this.time_pay = time_pay;, Date time_pay, Date update_at
//        this.update_at = update_at;
    }

    public int getId_bill() {
        return id_bill;
    }

    public void setId_bill(int id_bill) {
        this.id_bill = id_bill;
    }

    public ElectricBoard getElectricBoard() {
        return electricBoard;
    }

    public void setElectricBoard(ElectricBoard electricBoard) {
        this.electricBoard = electricBoard;
    }

    public elecRegistration getElecRegistration() {
        return elecRegistration;
    }

    public void setElecRegistration(elecRegistration elecRegistration) {
        this.elecRegistration = elecRegistration;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public boolean isIs_paid() {
        return is_paid;
    }

    public void setIs_paid(boolean is_paid) {
        this.is_paid = is_paid;
    }

    public Date getTime_pay() {
        return time_pay;
    }

    public void setTime_pay(Date time_pay) {
        this.time_pay = time_pay;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }
    
    
    
   
    

}

