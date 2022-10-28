package test;

import com.ek.vendingmachine.dao.AuditDao;
import com.ek.vendingmachine.dao.VendingMachinePersistenceException;

public class AuditDaoStubImpl implements AuditDao {
    @Override
    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException {

    }
}
