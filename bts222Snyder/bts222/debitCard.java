import java.io.*;
import java.util.*;

// Debit card class
public class debitCard extends Card{

    public debitCard(String cardNumber, String expirationDate, String balance) {
        super(cardNumber, expirationDate, balance);
    }

    // Checks to see what will happen to the account balance if a certain amount is withdrawn
    public boolean updateBalance(double amount) {
        double temp = Double.parseDouble(balance);
        if (temp - amount < 0) {
            return false;
        } else {
            temp = temp - amount;
            balance = Double.toString(temp);
            return true;
        }
        
    }

    // diffierentiates between credit and debit cards, debit cards always return true
    public boolean cardType() {
        return true; // true refers to debit card
    }
}