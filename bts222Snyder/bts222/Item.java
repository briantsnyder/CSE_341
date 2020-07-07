// Simple item class that displays and returns info regarding an item
public class Item {
    String itemID;
    String price;
    String store;
    public Item(String itemID, String price, String store) {
        this.itemID = itemID;
        this.price = price;
        this.store = store;
    }

    public String getItemID() {
        return itemID;
    }

    public String getPrice() {
        return price;
    }

    public String getStore() {
        return store;
    }

    public void display() {
        String line = String.format("%10s %1s %-5s %1s %-10s", itemID, price, store);
        System.out.println(line);
        
    }
}