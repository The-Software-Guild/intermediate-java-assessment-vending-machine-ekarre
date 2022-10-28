package com.ek.vendingmachine.dto;

import com.ek.vendingmachine.ui.UserIO;
import com.ek.vendingmachine.ui.UserIOImpl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.Map;


public class Change {
    private UserIO io = new UserIOImpl();
    private Map<Coins, Integer> change = new LinkedHashMap<Coins, Integer>();

    public void getChange(BigDecimal balance){
        BigDecimal localChange = balance;
        localChange.setScale(2, RoundingMode.HALF_UP);
        for (Coins coins : Coins.values()) {
            BigDecimal[] results = localChange.divideAndRemainder(coins.getValue(), MathContext.DECIMAL32);
            results[1].setScale(2, RoundingMode.HALF_UP);
            // results[0] now contains the quotient, e.g., how many quarters do I need?
            // results[1] now contains the remaining balance after taking out the quarters.
            // if (number of quarters is not zero AND remainder is not zero)
            if (!results[0].equals(BigDecimal.valueOf(0.0)) && !results[1].equals(BigDecimal.valueOf(0.0))){
                //adding the type of coin and number of coins to our returnChange map
                change.put(coins, results[0].intValue());
                localChange = results[1]; //updating the balance to whatever is left
                localChange.setScale(2, RoundingMode.HALF_UP);
            }
        }
    }

    public void print() {
        System.out.println("Here is your change");
        for (Map.Entry<Coins, Integer> entry : this.change.entrySet()){
            System.out.println(entry.getValue() + " " + entry.getKey());
        }
    }
}
