package com.ek.vendingmachine.dao;

import com.ek.vendingmachine.dto.Item;

import java.util.Map;

public interface VendingMachineDao {
    Item getItem(String itemId) throws VendingMachinePersistenceException;
    Map<String,Item> getAllItems() throws VendingMachinePersistenceException;
    public Map<String, Item> getItemMap();
    public void putAllItems() throws VendingMachinePersistenceException;
}
