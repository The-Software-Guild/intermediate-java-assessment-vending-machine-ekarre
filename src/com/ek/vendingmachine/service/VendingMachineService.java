package com.ek.vendingmachine.service;

import com.ek.vendingmachine.dao.VendingMachineDao;
import com.ek.vendingmachine.dao.VendingMachinePersistenceException;
import com.ek.vendingmachine.dto.Item;

import java.math.BigDecimal;

public interface VendingMachineService{
    void inventoryDecreaseCount(Item selectedItem) throws VendingMachinePersistenceException;

    void checkInventory(Item selectedItem) throws VendingMachineItemInventoryException, VendingMachinePersistenceException;

}
