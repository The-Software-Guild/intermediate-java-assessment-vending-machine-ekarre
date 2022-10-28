package com.ek.vendingmachine.service;

public class VendingMachineItemInventoryException extends Exception{
    public VendingMachineItemInventoryException(String message){
        super(message);
    }
    public VendingMachineItemInventoryException(String message, Throwable cause){
        super(message, cause);
    }
}
