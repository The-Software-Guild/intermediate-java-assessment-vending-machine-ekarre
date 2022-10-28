package com.ek.vendingmachine.dao;

public interface AuditDao {
    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException;
}
