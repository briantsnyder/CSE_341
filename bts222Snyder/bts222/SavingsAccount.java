import java.io.*;
import java.util.*;

// Savings account
public class SavingsAccount extends Account {
    
    public SavingsAccount(String SSN, String fName, String lName, String AccountID, String balance, String accountRate) {
        super(SSN, fName, lName, AccountID, balance, accountRate); 
    }

    // Checks to see if the amount being withdrawn will bring the account below 0
    public boolean checkBalance(int amount) {
        double accountBalance = Double.parseDouble(balance);
        if (accountBalance - amount < 0) {
            System.out.println("Error, this withdraw brings the account balance below 0 and cannot be processed");
            return false;
        } else {
            accountBalance = accountBalance - amount;
            balance = Double.toString(accountBalance);
            return true;
        }
    }
}