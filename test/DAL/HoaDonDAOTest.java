/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package DAL;

import Model.Bill;
import Model.User;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author lvt-195
 */
public class HoaDonDAOTest {
    
    public HoaDonDAOTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getBillsByUserCCCD method, of class HoaDonDAO.
     */
    @Test
    public void testGetBillsByUserCCCD() throws Exception {
        System.out.println("getBillsByUserCCCD");
        String cccd = "";
        HoaDonDAO instance = new HoaDonDAO();
        List<Bill> expResult = null;
        List<Bill> result = instance.getBillsByUserCCCD(cccd);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBillDetail method, of class HoaDonDAO.
     */
    @Test
    public void testGetBillDetail() throws Exception {
        System.out.println("getBillDetail");
        int id = 0;
        HoaDonDAO instance = new HoaDonDAO();
        Bill expResult = null;
        Bill result = instance.getBillDetail(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePaymentStatusInDatabase method, of class HoaDonDAO.
     */
    @Test
    public void testUpdatePaymentStatusInDatabase() {
        System.out.println("updatePaymentStatusInDatabase");
        int id_bill = 0;
        String payment_status = "";
        String id_admin = "";
        HoaDonDAO instance = new HoaDonDAO();
        boolean expResult = false;
        boolean result = instance.updatePaymentStatusInDatabase(id_bill, payment_status, id_admin);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadUser method, of class HoaDonDAO.
     */
    @Test
    public void testLoadUser() {
        System.out.println("loadUser");
        int id = 0;
        HoaDonDAO instance = new HoaDonDAO();
        User expResult = null;
        User result = instance.loadUser(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
