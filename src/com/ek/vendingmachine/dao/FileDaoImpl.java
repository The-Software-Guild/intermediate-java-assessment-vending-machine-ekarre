package com.ek.vendingmachine.dao;

import com.ek.vendingmachine.dto.Item;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class FileDaoImpl implements FileDao {
    private static final String ITEM_FILE = "items.txt";
    private static final String DELIMITER = ":";
    @Override
    public Item unmarshallItem(String line) {
        //take input line read from the file, and split it into pieces
        String[] itemTokens = line.split(DELIMITER);
        //the first token in the string is the item ID, grab it
        String itemId = itemTokens[0];
        //create a new item for this record using the itemId as the main identifier
        Item itemFromFile = new Item(itemId);
        itemFromFile.setName(itemTokens[1]);
        itemFromFile.setPrice(new BigDecimal(itemTokens[2]));
        itemFromFile.setInventory(Integer.parseInt(itemTokens[3]));
        return itemFromFile;
    }

    @Override
    public String marshallItem(Item item) {
        String itemAsText = item.getItemId() + DELIMITER;
        itemAsText += item.getName() + DELIMITER;
        itemAsText += item.getPrice() + DELIMITER;
        itemAsText += item.getInventory();
        return itemAsText;
    }

    @Override
    public void writeFile(Map<String, Item> itemsToWrite) throws VendingMachinePersistenceException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(ITEM_FILE));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException("can't save data", e);
        }
        String itemAsText;
        for (Map.Entry<String,Item> entry : itemsToWrite.entrySet()) {
            itemAsText = marshallItem(entry.getValue());
            out.println(itemAsText);
            out.flush();
        }
        out.close();
    }

    @Override
    public Map<String, Item> readFile(String itemFile) throws VendingMachinePersistenceException {
        Scanner scanner;
        Map<String, Item> localMapItems = new LinkedHashMap<>();
        try{
            scanner = new Scanner(new BufferedReader(new FileReader(ITEM_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException("can't load", e);
        }
        String currentLine;
        Item currentItem;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentItem = unmarshallItem(currentLine);
            localMapItems.put(currentItem.getItemId(), currentItem);
        }
        scanner.close();
        return localMapItems;
    }
}
