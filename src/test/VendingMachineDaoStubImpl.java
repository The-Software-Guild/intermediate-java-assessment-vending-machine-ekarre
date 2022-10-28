package test;

import com.ek.vendingmachine.dao.VendingMachineDao;
import com.ek.vendingmachine.dao.VendingMachinePersistenceException;
import com.ek.vendingmachine.dto.Item;

import java.util.Map;

public class VendingMachineDaoStubImpl implements VendingMachineDao {
    @Override
    public Item getItem(String itemId) throws VendingMachinePersistenceException {
        return null;
    }

    @Override
    public Map<String, Item> getAllItems() throws VendingMachinePersistenceException {
        return null;
    }

    @Override
    public Map<String, Item> getItemMap() {
        return null;
    }

    @Override
    public void putAllItems() throws VendingMachinePersistenceException {

    }
}
