import java.io.*;
import java.util.*;

// Parent card class
public class Card {
    String cardNumber;
    String expirationDate;
    String balance;

    public Card(String cardNumber, String expirationDate, String balance) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.balance = balance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getBalance() {
        return balance;
    }

    // Checks to see that the amount being withdrawn is valid
    public boolean updateBalance(double amount) {
        double temp = Double.parseDouble(balance);
        if (temp - amount < 0) {
            return false;
        } else {
            balance = Double.toString(temp);
            return true;
        }
    }

    // Displays the card info
    public void display() {
        String line = "CardNumber          ExpirationDate     Balance";
        String line2 = "---------------------------------------------------";
        String line3 = String.format("%9s %3s %-5s %8s %-7s", cardNumber, "", expirationDate, "", balance);
        System.out.println(line);
        System.out.println(line2);
        System.out.println(line3);
    }

/* List of methods needed for the subclasses, but will never be called by the parent class */
    public void calcBalanceDue() {
        //System.out.println("This shouldn't print"); // this is called instead so have to test
    }

    public String getBalanceDue() {
        return "This shouldn't print part 2";
    }

    public boolean cardType() {
        System.out.println("This should also not print");
        return true;
    }

    public void setBalance(String s) {
        balance = s;
    }

    public boolean payCredit(int n) {
        System.out.println("This shouldn't run");
        return false;
    }
}