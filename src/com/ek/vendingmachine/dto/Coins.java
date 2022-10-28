package com.ek.vendingmachine.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public enum Coins {
    //list coin names and create a new BigDecimal value to them
    QUARTERS(new BigDecimal(0.25)),
    DIMES(new BigDecimal(0.10)),
    NICKELS(new BigDecimal(0.05)),
    PENNIES(new BigDecimal(0.01));

    //declare BigDecimal object with name of "value"
    private final BigDecimal value;

    //declare a method (Coins) with parameters being a BigDecimal called value
    //this instance of value = same name of value
    private Coins(BigDecimal value) {
        this.value = value;
    }

    //creating a "getter" to get the value and return with these parameters
    public BigDecimal getValue() {
        return value.setScale(2, RoundingMode.HALF_UP);
    }

    //create getters for each coin value
    public Coins getPenny(){
        return PENNIES;
    }
    public Coins getNickel(){
        return NICKELS;
    }
    public Coins getDime(){
        return DIMES;
    }
    public Coins getQuarter(){
        return QUARTERS;
    }
}