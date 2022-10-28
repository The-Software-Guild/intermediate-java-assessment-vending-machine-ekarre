package com.ek.vendingmachine.ui;

import com.ek.vendingmachine.dao.AuditDao;
import com.ek.vendingmachine.dao.AuditDaoImpl;
import com.ek.vendingmachine.dao.VendingMachineDao;
import com.ek.vendingmachine.dao.VendingMachinePersistenceException;
import com.ek.vendingmachine.dto.Change;
import com.ek.vendingmachine.dto.Item;
import com.ek.vendingmachine.service.VendingMachineInsufficientFundsException;
import com.ek.vendingmachine.service.VendingMachineItemInventoryException;
import com.ek.vendingmachine.service.VendingMachineService;
import com.ek.vendingmachine.service.VendingMachineServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VendingMachineView {
    private UserIO io;
    private Change change;
    private AuditDao auditDao = new AuditDaoImpl();
    private VendingMachineService service = new VendingMachineServiceImpl(auditDao);

    public VendingMachineView(UserIO io, Change change) throws VendingMachinePersistenceException {
        this.io = io;
        this.change = change;
    }

    //add money
    public BigDecimal addFunds(){
        addMoneyBanner();
        BigDecimal funds = io.readBigDecimal("Enter funds to add ($0 - $100): ", new BigDecimal(0), new BigDecimal(100));
        return funds;
    }
    //add money banner
    public void addMoneyBanner(){
        io.print("Add Money");
    }

    //welcome banner
    public void welcomeBanner(){
        io.print("Welcome to the Vending Machine");
    }

    //mini menu
    public void miniMenu(){
        welcomeBanner();
        io.print("1. Add money");
        io.print("2. Choose item");
        io.print("3. Exit");
    }

    //have user choose what they want to do
    public String getMiniMenuSelection() {
        return io.readString("Please select an operation ");
    }

    //all items banner
    public void allItemsBanner(){
        io.print("Here are your choices. Choose wisely!");
    }

    //print out all items so user can choose
    public BigDecimal printAllItems(VendingMachineDao dao, BigDecimal balance) throws VendingMachinePersistenceException, VendingMachineInsufficientFundsException, VendingMachineItemInventoryException {
        BigDecimal newBalance = new BigDecimal(0.0);
        allItemsBanner();
        //create a lambda and stream to filter out the items that have an inventory greater than 0
        List<Item> list = new ArrayList<Item>(dao.getItemMap().values());
        List<Item> filteredList = list
                                 .stream()
                                 .filter(item -> item.getInventory() > 0)
                                 .collect(Collectors.toList());
        filteredList.forEach(item -> System.out.println(item.getItemId() + " : " + item.getName() + " : " + item.getPrice() + " : (" + item.getInventory() + " items left)"));
        String selectedItemId = io.readString("Choose your snack! ");
        Item selectedItem = dao.getItem(selectedItemId);
        if (selectedItem == null){
            System.out.println("Sorry, you chose an invalid selection");
        } else {
            System.out.println("You selected " + selectedItem.getName());
            service.checkInventory(selectedItem);
            if (balance.compareTo(selectedItem.getPrice()) >= 0){
                System.out.println("You have enough money!");
                newBalance = balance.subtract(selectedItem.getPrice());
                service.inventoryDecreaseCount(selectedItem);
            } else {
                throw new VendingMachineInsufficientFundsException("Not enough funds!");
            }
        }
        auditDao.writeAuditEntry("User bought " + selectedItem);
        change.getChange(balance);
        return newBalance;
    }
}
