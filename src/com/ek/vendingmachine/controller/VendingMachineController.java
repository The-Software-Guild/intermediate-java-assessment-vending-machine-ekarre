package com.ek.vendingmachine.controller;

import com.ek.vendingmachine.dao.VendingMachineDao;
import com.ek.vendingmachine.dao.VendingMachinePersistenceException;
import com.ek.vendingmachine.dto.Change;
import com.ek.vendingmachine.service.VendingMachineInsufficientFundsException;
import com.ek.vendingmachine.service.VendingMachineItemInventoryException;
import com.ek.vendingmachine.ui.UserIO;
import com.ek.vendingmachine.ui.UserIOImpl;
import com.ek.vendingmachine.ui.VendingMachineView;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class VendingMachineController {
    private VendingMachineDao dao;
    private VendingMachineView view;
    private UserIO io = new UserIOImpl();
    private Change change = new Change();

    public VendingMachineController(VendingMachineDao dao, VendingMachineView view) throws VendingMachinePersistenceException {
        this.dao = dao;
        this.view = view;
    }

    //create method to run the menu
    public void run() throws VendingMachineInsufficientFundsException, VendingMachinePersistenceException, VendingMachineItemInventoryException {
        BigDecimal balance = new BigDecimal(0.0);
        balance.setScale(2, RoundingMode.HALF_UP);
        boolean keepGoing = true;

        while(keepGoing) {
            view.miniMenu();
            io.print("Your balance is: " + balance);
            String operation = getMiniMenuSelection();
            switch(operation) {
                case "1": //add funds
                    balance = balance.add(addFunds());
                    break;
                case "2": //select item
                    balance = buyItems(this.dao, balance);
                    break;
                case "3": //exit
                    if (balance.compareTo(new BigDecimal (0.0))>0){
                        change.getChange(balance);
                        change.print();
                    } else {
                        io.print("You have no change");
                    }
                    keepGoing = false;
                    //write to file here
                    dao.putAllItems();
                    break;
            }
        }
        io.print("Thank you! Goodbye");
    }

    //calling methods from the view to put into our menu switch case
    private BigDecimal addFunds() {
       return view.addFunds();
    }
    private String getMiniMenuSelection() {
        return view.getMiniMenuSelection();
    }
    private BigDecimal buyItems(VendingMachineDao dao, BigDecimal balance) throws VendingMachinePersistenceException, VendingMachineInsufficientFundsException, VendingMachineItemInventoryException {
        return view.printAllItems(dao, balance);
    }

}
