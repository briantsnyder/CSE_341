
// Account class which stores and returns info regarding an account
import java.io.*;
import java.util.*;

public class Account {
    String fName;
    String lName;
    String SSN;
    String AccountID;
    String balance;
    String accountRate;

    public Account(String SSN, String fName, String lName, String AccountID, String balance, String accountRate) {
        this.SSN = SSN;
        this.fName = fName;
        this.lName = lName;
        this.AccountID = AccountID;
        this.balance = balance;
        this.accountRate = accountRate;
    }

    public String getOwnerName() {
        return fName + " " + lName;
    }

    public String getOwnerID() {
        return SSN;
    }

    public String getAccountID() {
        return AccountID;
    }

    public String getBalance() {
        return balance;
    }

    public String getAccountRate() {
        return accountRate;
    }

    // checks to see if removing a certain amount from the account is valid
    public boolean checkBalance(int num) {
    	if (Integer.parseInt(balance) - num < 0) {
		    return false;
        } else {
		    return true;
	    }
    }

    // increases the account when a deposit occurs
    public void increaseBalance(int num) {
        int temp = Integer.parseInt(balance);
        temp = temp + num;
        balance = Integer.toString(temp);
        //System.out.println(balance);
    }

    // displays account
    public void display() {
        String line = "SSN         First Name     Last Name    Account ID     Balance     Account Rate";
        String line2 = "---------------------------------------------------------------------------------";
        String line3 = String.format("%9s %1s %-12s %1s %-6s %5s %-13s %1s %-6s %3s %-3s", SSN, " ", fName, " ", lName, " ", AccountID, " ", balance, " ", accountRate);
        System.out.println(line);
        System.out.println(line2);
        System.out.println(line3);
        System.out.println();
    }
}