package com.ek.vendingmachine.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class Item {

    private String itemId;
    private String name;
    private BigDecimal price;
    private int inventory; //this is how many of a certain item is left

    public Item(String itemId){
        this.itemId = itemId;
    }
    public Item(String itemId, String name, BigDecimal price, int inventory){
        this.name = name;
        this.price = price;
        this.inventory = inventory;
        this.itemId = itemId;
    }

    public String getItemId() {
        return itemId;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public BigDecimal getPrice(){
        return price;
    }
    public void setPrice(BigDecimal price){
        this.price = price;
    }
    public int getInventory(){
        return inventory;
    }
    public void setInventory(int inventory){
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "Item {" + " item Id: "+ itemId + ", name: " + name + ", price: " + price + ", inventory: " + inventory + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return getInventory() == item.getInventory() && getItemId().equals(item.getItemId()) && getName().equals(item.getName()) && getPrice().equals(item.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItemId(), getName(), getPrice(), getInventory());
    }
}
