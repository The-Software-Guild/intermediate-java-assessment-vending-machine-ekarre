package com.ek.vendingmachine;

import com.ek.vendingmachine.controller.VendingMachineController;
import com.ek.vendingmachine.dao.*;
import com.ek.vendingmachine.dto.Change;
import com.ek.vendingmachine.service.VendingMachineInsufficientFundsException;
import com.ek.vendingmachine.service.VendingMachineItemInventoryException;
import com.ek.vendingmachine.service.VendingMachineService;
import com.ek.vendingmachine.service.VendingMachineServiceImpl;
import com.ek.vendingmachine.ui.UserIO;
import com.ek.vendingmachine.ui.UserIOImpl;
import com.ek.vendingmachine.ui.VendingMachineView;

public class App {

    public static void main(String[] args) throws VendingMachineInsufficientFundsException, VendingMachinePersistenceException, VendingMachineItemInventoryException {
        UserIO myIo = new UserIOImpl();
        Change myChange = new Change();
        VendingMachineView myView = new VendingMachineView(myIo, myChange);
        VendingMachineDao myDao = new VendingMachineDaoImpl();
        AuditDao myAuditDao = new AuditDaoImpl();
        VendingMachineService myService = new VendingMachineServiceImpl(myAuditDao);
        VendingMachineController controller = new VendingMachineController(myDao, myView);
        controller.run();

    }
}
