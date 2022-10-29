package test;

import com.ek.vendingmachine.dao.VendingMachineDao;
import com.ek.vendingmachine.dao.VendingMachineDaoImpl;
import com.ek.vendingmachine.dao.VendingMachinePersistenceException;
import com.ek.vendingmachine.dto.Item;

import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class VendingMachineDaoImplTest {
    public static VendingMachineDao testDao;

    public VendingMachineDaoImplTest(){}

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception{
        String testFile = "testItem.txt";
        String testFileTwo = "testOutputItem.txt";
        //new FileWriter(testFile);
        testDao = new VendingMachineDaoImpl(testFile);
    }

    @org.junit.jupiter.api.Test
    void testGetAllItems() throws VendingMachinePersistenceException {
        Map<String, Item> testMap = new LinkedHashMap<>();
        Map<String, Item> fileMap;
        //A0:Test Doritos:1.50:6
        Item testItemOne = new Item("A0", "Test Doritos", new BigDecimal (1.50),  6);
        //B0:Test Lays:1.00:6
        Item testItemTwo = new Item("B0", "Test Lays", new BigDecimal (1.00),  6);
        testMap.put("A0", testItemOne);
        testMap.put("B0", testItemTwo);
        fileMap = testDao.getAllItems();
        //now compare testDao.itemMap contents with testMap contents, they should all be the same
        assertEquals("Checking the number of items in the map", testMap.size(), fileMap.size());
    }

    @org.junit.jupiter.api.Test
    void testGetItem() throws VendingMachinePersistenceException {
        Map<String, Item> fileMap;
        //A0:Test Doritos:1.50:6
        Item testItemOne = new Item("A0", "Test Doritos", new BigDecimal ("1.50"),  6);
        fileMap = testDao.getAllItems();
        //now compare testDao.itemMap contents with testMap contents, they should all be the same
        assertEquals("Checking the item ID", testItemOne.getItemId(), testDao.getItem("A0").getItemId());
        assertEquals("Checking the name", testItemOne.getName(), testDao.getItem("A0").getName());
        assertEquals("Checking the price", testItemOne.getPrice(), testDao.getItem("A0").getPrice());
        assertEquals("Checking the inventory", testItemOne.getInventory(), testDao.getItem("A0").getInventory());
    }
}