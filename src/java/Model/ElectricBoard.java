/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;

public class ElectricBoard{
    private int id;
	private String meter_code;
	private String meter_address;
	private String peroid;
	private int meter_number;
	private Date time_start;
	private Date time_edit;
	private Date time_update;
	private int total_electricity;
	private User admin;
	private elecRegistration eRegistration;
        private long e_bill;
	public ElectricBoard() {
	}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMeter_code() {
        return meter_code;
    }

    public void setMeter_code(String meter_code) {
        this.meter_code = meter_code;
    }

    public String getMeter_address() {
        return meter_address;
    }

    public void setMeter_address(String meter_address) {
        this.meter_address = meter_address;
    }

    public String getPeroid() {
        return peroid;
    }

    public void setPeroid(String peroid) {
        this.peroid = peroid;
    }

    public int getMeter_number() {
        return meter_number;
    }

    public void setMeter_number(int meter_number) {
        this.meter_number = meter_number;
    }

    public Date getTime_start() {
        return time_start;
    }

    public void setTime_start(Date time_start) {
        this.time_start = time_start;
    }

    public Date getTime_edit() {
        return time_edit;
    }

    public void setTime_edit(Date time_edit) {
        this.time_edit = time_edit;
    }

    public Date getTime_update() {
        return time_update;
    }

    public void setTime_update(Date time_update) {
        this.time_update = time_update;
    }

    public int getTotal_electricity() {
        return total_electricity;
    }

    public void setTotal_electricity(int total_electricity) {
        this.total_electricity = total_electricity;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public elecRegistration geteRegistration() {
        return eRegistration;
    }

    public void seteRegistration(elecRegistration eRegistration) {
        this.eRegistration = eRegistration;
    }

    public long getE_bill() {
        return e_bill;
    }

    public void setE_bill(long e_bill) {
        this.e_bill = e_bill;
    }
    
    @Override
    public String toString() {
        return "ElectricBoard{" + "id=" + id + ", meter_code=" + meter_code + ", meter_address=" + meter_address + ", peroid=" + peroid + ", meter_number=" + meter_number + ", time_start=" + time_start + ", time_edit=" + time_edit + ", time_update=" + time_update + ", total_electricity=" + total_electricity + ", admin=" + admin + ", eRegistration=" + eRegistration + ", e_bill=" + e_bill + '}';
    }
	
}
