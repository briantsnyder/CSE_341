import java.io.*;
import java.util.*;

// Sub class of Card for credit cards
public class creditCard extends Card {
    String creditRate;
    String creditLimit;
    String balanceDue;
    public creditCard(String cardNumber, String expirationDate, String balance, String creditRate, String creditLimit, String balanceDue) {
        super(cardNumber, expirationDate, balance);
        this.creditRate = creditRate;
        this.creditLimit = creditLimit;
        this.balanceDue = balanceDue;
        calcBalanceDue(); // calculates balance due upon creation to get the most recent numbers
    }

    public String getCreditRate() {
        return creditRate;
    }

    public String getCreditLimit() {
        return creditLimit;
    }

    public String getBalanceDue() {
        return balanceDue;
    }

    // Calculates the amount due by taking the rate times the total debt
    public void calcBalanceDue() {
        double due = Double.parseDouble(balance);
        double rate = Double.parseDouble(creditRate);
        rate = rate / 100;
        balanceDue = Double.toString(((int)((rate * due)*100) / 100));
    }

    // Checks to see if the amount being charged is more than the credit limit
    public boolean updateBalance(double amount) {
        double limit = Double.parseDouble(creditLimit);
        double temp = Double.parseDouble(balance);
        if (temp + amount > limit) {
            System.out.println("This transaction goes beyond the card's credit limit and is declined");
            return false;
        } else {
            temp = temp + amount;
            balance = Double.toString(temp);
            return true;
        } 
    }

    // checks if the amount being paid is more than the minimum amount but less than the overall debt amount, and if so updates the balance
    public boolean payCredit(int amount) {
        double temp = Double.parseDouble(balance);
        double min = Double.parseDouble(balanceDue);
        temp = temp - amount;
        if (temp < 0) {
            System.out.println("I'm sorry, this payment is more than the current total credit owed on this card, and thus this payment cannot be accepted");
            return false;
        } else if (temp == 0) {
            System.out.println("Congratulations, you have successfully paid ball all of this card's credit card debt");
            balance = Double.toString(temp);
            // call calcBalanceDue
            return true;
        } else if (amount >= min) {
            System.out.println("This payment is larger than the minimum amount but not larger than the total credit card debt, and thus is accepted");
            balance = Double.toString(temp);
            return true;
        } else {
            System.out.println("This amount is less than the minimum required payment, and thus this transaction cannot be accepted");
            return false;
        }
    }

    // Displays the card info
    public void display() {
        String line = "CardNumber          ExpirationDate     Balance      creditRate      creditLimit       balanceDue";
        String line2 = "-----------------------------------------------------------------------------------------------";
        String line3 = String.format("%9s %2s %-5s %11s %-7s %4s %-4s %10s %-5s %11s %-5s", cardNumber, "", expirationDate, "", balance, "", creditRate, "", creditLimit, "", balanceDue);
        System.out.println(line);
        System.out.println(line2);
        System.out.println(line3);
    }

    // Used to diffierentiate between credit and debit cards, credit cards always return false;
    public boolean cardType() {
        return false; // false refers to the card being a credit card
    }

}