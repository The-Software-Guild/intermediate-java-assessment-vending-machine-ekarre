package com.ek.vendingmachine.dao;

import com.ek.vendingmachine.dto.Item;

import java.util.Map;

public class VendingMachineDaoImpl implements VendingMachineDao{
    public Map<String, Item> getItemMap() {
        return itemMap;
    }

    Map<String, Item> itemMap;
    FileDao fileDao;
    private final String ITEM_FILE;

    public VendingMachineDaoImpl(String ItemTextFile){
        ITEM_FILE = ItemTextFile;
    }
    public VendingMachineDaoImpl() throws VendingMachinePersistenceException {
        fileDao = new FileDaoImpl();
        itemMap = getAllItems();
        ITEM_FILE = "item.txt";
    }
    @Override
    public Map<String, Item> getAllItems() throws VendingMachinePersistenceException {
        return fileDao.readFile(ITEM_FILE);
    }
    @Override
    public void putAllItems() throws VendingMachinePersistenceException {
        fileDao.writeFile(this.itemMap);
    }
    @Override
    public Item getItem(String itemId) throws VendingMachinePersistenceException {
        return itemMap.get(itemId);
    }
}
