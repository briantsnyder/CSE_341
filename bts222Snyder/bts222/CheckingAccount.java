import java.io.*;
import java.util.*;

// Sub class of account, includes min balance
public class CheckingAccount extends Account {
    String minBalance;
    public CheckingAccount(String SSN, String fName, String lName, String AccountID, String balance, String accountRate, String minBalance) {
        super(SSN, fName, lName, AccountID, balance, accountRate);
        this.minBalance = minBalance; 
    }

    public String getMinBalance() {
        return minBalance;
    }

    // checks if hte amount t being removed will take the balance bellow the minimum balance
    public boolean checkBalance(int amount) {
        double accountBalance = Double.parseDouble(balance);
        double min = Double.parseDouble(minBalance);
        if (accountBalance - amount < min) {
            if (accountBalance - amount < -100) { // the bank allows up to 100 below the minimum balance before declining all withdraws
                System.out.println("This transaction brings the balance below the minimum balance and past $-100, so this transaction is declined");
                return false;
            } else {
                System.out.println("This transacton brings the account below the minimum balance, a fee of $25 will be applied");
                accountBalance = accountBalance - amount - 25;
                balance = Double.toString(accountBalance);
                return true;
            }
        } else {
            accountBalance = accountBalance - amount;
            balance = Double.toString(accountBalance);
            return true;
        }
    }

    // Displays the account info
    public void display() {
        String line = "SSN         First Name     Last Name    Account ID     Balance     Account Rate     Min Balance";
        String line2 = "----------------------------------------------------------------------------------------------";
        String line3 = String.format("%9s %1s %-12s %1s %-6s %5s %-13s %1s %-6s %3s %-3s %12s %-8s", SSN, " ", fName, " ", lName, " ", AccountID, " ", balance, " ", accountRate, "", minBalance);
        System.out.println(line);
        System.out.println(line2);
        System.out.println(line3);
        System.out.println();
    }
}
