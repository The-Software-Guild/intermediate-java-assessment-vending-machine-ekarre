package test;

import com.ek.vendingmachine.dao.VendingMachineDao;
import com.ek.vendingmachine.dao.VendingMachineDaoImpl;

import java.io.FileWriter;

import static org.junit.jupiter.api.Assertions.*;

public class VendingMachineDaoImplTest {
    public static VendingMachineDao testDao;

    public VendingMachineDaoImplTest(){}

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception{
        String testFile = "testItem.txt";
        new FileWriter(testFile);
        testDao = new VendingMachineDaoImpl(testFile);
    }

    @org.junit.jupiter.api.Test
    void getItemMap() {
    }

    @org.junit.jupiter.api.Test
    void getAllItems() {
    }

    @org.junit.jupiter.api.Test
    void putAllItems() {
    }

    @org.junit.jupiter.api.Test
    void getItem() {
    }
}