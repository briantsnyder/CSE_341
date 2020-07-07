import java.io.*;
import java.sql.*;
import java.util.*;
import java.time.*;
import java.text.*;

public class NickelBank {
    public static void main(String args[]) {
        begin();
    }

    // Asks the user to login and displays the main menu and asks the user which terminal they want to open
    public static void begin() {
        // Prompts user for the their Oracle login credentials
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter in your Oracle ID: ");
        String id = scan.next();
        System.out.print("Enter in your password for " + id + ": ");
        String password = scan.next();

        try (
            // Connects to Oracle
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", id, password);
            Statement s = con.createStatement();
        ) {
            // Displays menu, currently only optsions 2, 3, 7, and 8 work
            String welcome = "\nWelcome to Nickel Savings and Loan, please select one of the following interfaces below (Enter a # 1-8): ";
            String s1 = "1. Bank Management";
            String s2 = "2. Account Deposit / Withdrawal";
            String s3 = "3. Payment on a loan or a credit card";
            String s4 = "4. Opening of a new account";
            String s5 = "5. Obtaining a new or replacement credit or debit card";
            String s6 = "6. Taking out a new loan";
            String s7 = "7. Purchases using a card";
            String s8 = "8. Quit\n";

            boolean atMenu = true;
            while(atMenu) {
                System.out.println(welcome);
                System.out.println("This is the Beta version, and currently only terminals 2, 3, and 7 are operational");
                System.out.println("-----------------------------------");
                System.out.println(s1);
                System.out.println(s2);
                System.out.println(s3);
                System.out.println(s4);
                System.out.println(s5);
                System.out.println(s6);
                System.out.println(s7);
                System.out.println(s8);

                // Continuously prompts user for thier choice and runs the respective methods until the user quits
                String option = scan.next();
                switch(option) {
                    case "1":
                        System.out.println("Sorry, our staff is still currently working on getting this part up and running");
                        break;
                    case "2":
                        AccountDepositWithdraw(s, scan);
                        System.out.println("Exiting the Account Despoit / Withdraw Screen");
                        break;
                    case "3":
                        payLoanOrCredit(s, scan);
                        System.out.println("Exiting the Pay Loan or Credit Terminal");
                        break;
                    case "4":
                        System.out.println("Sorry, our staff is still currently working on getting this part up and running");
                        break;
                    case "5":
                        System.out.println("Sorry, our staff is still currently working on getting this part up and running");
                        break;
                    case "6":
                        System.out.println("Sorry, our staff is still currently working on getting this part up and running");
                        break;
                    case "7":
                        purchaseUsingCard(s, scan);
                        System.out.println("Exiting the Purchase Using a Card Screen");
                        break;
                    case "8":
                        System.out.println("Thank you for visiting Nickel Savings and Loan, please visit our website to rate your experience");
                        atMenu = false;
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice chosen, please enter in a number between 1-8");
                        continue;
                }
            }

        } catch (Exception e) {
            // If there is an error caught, the user is asked to login again
            // This will occur due to logging in with incorrect credintials or lost connection to the database
            System.out.println("An error occurred, please login in try again");
            begin();
        }
        scan.close();
    }

    // Asks the user which customer account they would like to login in and verifies that the user entered a valid choice
    public static int chooseCustomerLogin(Statement s, Scanner scan, ArrayList<Customer> customers) throws SQLException {
        System.out.println("To begin, please choose a customer account to login in as");
        System.out.println("To login, simply enter in the customer's SSN, or 'q' to quit");
        
        String q;
        ResultSet result;

        // Displays all of the available customers to login in as, and creates each one as an object into an ArrayList to use later
        q = "SELECT * from Customer";
        result = s.executeQuery(q);

        System.out.println("SSN         First Name    Last Name       DOB");
        System.out.println("----------------------------------------------------------");
        String SSN = "";
        String fName = "";
        String lName = "";
        while(result.next()) {
            SSN = result.getString("SSN");
            fName = result.getString("FIRSTNAME");
            lName = result.getString("LASTNAME");
            String DOB = result.getString("DOB");
            customers.add(new Customer(fName, lName, SSN, DOB));
            String line = String.format("%9s %1s %-11s %1s %-9s %5s %-12s", SSN, "", fName, "", lName, "", DOB);
            System.out.println(line);
        }
        System.out.println();
        result.close();

        // Goes through arrayList and verifies the user entered a valid choice 
        int index = -1;
        String customerChoice = "";
        boolean inValidChoice = true;
        while(inValidChoice) {
            customerChoice = scan.next();
            for (int i = 0; i < customers.size(); i++) {
                // If the User Entered SSN matches a valid SSN, exit the loop and save the index
                if (customerChoice.equals(customers.get(i).getSSN())) {
                    inValidChoice = false;
                    index = i;
                    return index;
                // Quit if the user asks to quit
                } else if (customerChoice.equals("q") || customerChoice.equals("Q")) {
                    inValidChoice = false;
                    index = -1;
                    return index;
                }
            }
            // Otherwise repeat the loop
            if (inValidChoice) {
                System.out.println("I'm sorry, " + customerChoice + " is not a valid option, please enter in one of the custoemr IDs above or press 'q' to quit");
            }
        }
        return index;
    }

    // Prompts the user if they would want to make a payment for a credit or loan payment and verifies that the choice is valid
    public static int chooseCreditOrLoan(Scanner scan) {
        System.out.println("Would you like to make a credit or loan payment?");
        System.out.println("Enter '1' for Credit, '2' for Loan, or 'q' to quit");

        boolean inValidChoice = true;
        int creditOrLoan = -1; // 0 = credit, 1 = loan, -1 = error / user exit
        String optionChoice = "";
        while (inValidChoice) {
            System.out.println();
            optionChoice = scan.next();
	        if (optionChoice.equals("1")) {
                creditOrLoan = 0;
                inValidChoice = false;
            } else if (optionChoice.equals("2")) {
                creditOrLoan = 1;
                inValidChoice = false;
            } else if (optionChoice.equals("q") || optionChoice.equals("Q")) {
                return -1;
            } else {
                System.out.println("I'm sorry '" + optionChoice + "' is an invalid option, please enter '1' or '2' or hit 'q' to quit");
            }  
        }
        // Returns an interger where 0 means credit, 1 means a loan, and -1 means the user wants to quit
        return creditOrLoan;
    }

    // Method which generates a random but unique id for a transaction, and then inserts the transaction as well as an entry into makes
    public static int insertTransactionAndMakes(Statement s, ArrayList<String> transactionIDs, ArrayList<Customer> customers, int amount, int customerIndex) throws SQLException {
        String q = "";
        ResultSet result;
        // Adds used transaction IDs into an arrayList
        q = "SELECT T_ID from Transaction";
        result = s.executeQuery(q);
        while(result.next()) {
            String id = result.getString("T_ID");
            transactionIDs.add(id);
        }

        // Generates a random ID, and checks if this ID is not in the list of currently used IDs
        int id = (int)(Math.random() * 10000000);
        while (true) {
            if (transactionIDs.contains(id)) {
                id = (int)(Math.random() * 10000000);
            } else {
                break;
            }
        }

        // Inserts into the transaciton table an entry using today's date and the randomly but unique id generated, and the transaction amount which is given
        String date = new SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date());
        q = "INSERT into Transaction(T_ID, transactionDate, amount) values ('" + id + "', '" + date + "', " + Integer.toString(amount) + ")";
        result = s.executeQuery(q);

        // Inserts an entry into Makes, which connects who made which transaction
        q = "INSERT into Makes(ID, T_ID) values ('" + customers.get(customerIndex).getSSN() + "', '" + id + "')";
        result = s.executeQuery(q);
        result.close();
        // returns the transaction ID as this will be used by other inserts that are related to this transaction
        return id;
    }

    // Generates a list of all of the available loans based off of the user entered info
    public static void generateListOfAvailableLoans(Statement s, ArrayList<Loan> loans, ArrayList<Customer> customers, int customerIndex) throws SQLException {
        String q;
        ResultSet result;
        // Displays all loans and then adds them to a list which can be accessed later
        q = "SELECT L.loanID, L.loanAmount, L.loanRate, L.MonthlyPayment from Transaction T, borrows B, lend E, loan L, makes M, customer C where C.ssn = M.id and M.t_id = T.t_id and T.t_id = B.t_id and B.t_id = E.t_id and E.loanid = L.loanid and C.ssn = '" + customers.get(customerIndex).getSSN() + "'";
        result = s.executeQuery(q);
        int counter = 0;
        System.out.println("Below is a list of the current loans attached with your account");
        System.out.println("LoanID    Loan Amount   Loan Rate   Monthly Payment");
        System.out.println("-----------------------------------------------------");
        while(result.next()) {
            String loanID = result.getString("LOANID");
            String loanAmount = result.getString("LOANAMOUNT");
            String loanRate = result.getString("LOANRATE");
            String monthlyPayment = result.getString("MONTHLYPAYMENT");
            loans.add(new Loan(loanID, loanAmount, loanRate, monthlyPayment));
            // Calls getMonthlyPayment method to ensure that the monthly payment is the most up to date before being printed
            String line = String.format("%8s %3s %-5s %3s %-4s %6s %-5s", loanID, "", loanAmount, "", loanRate, "", loans.get(counter).getMonthlyPayment());
            System.out.println(line);
            counter++;
        }
        System.out.println();
        result.close();
    }

    // Chooses a specific loan based off of user input
    public static int selectLoan(Scanner scan, ArrayList<Loan> loans) {
        System.out.println("Please select which loan you would like to make a payment towards by entering the loanID, or by hitting 'q' to quit");
        int loanIndex = 0;
        boolean inValidChoice = true;
        String loanChoice = "";
        // Loop continues until user quits, which is represented by -1, or the user enteres a valid loan
        while (inValidChoice) {
            loanChoice = scan.next();
            for (int i = 0; i < loans.size(); i++) {
                if (loanChoice.equals(loans.get(i).getLoanID())) {
                    inValidChoice = false;
                    loanIndex = i;
                    break;
                } else if (loanChoice.equals("q") || loanChoice.equals("Q")) {
                    return -1;
                }
            }
            if (inValidChoice) {
                System.out.println("I'm sorry, " + loanChoice + " is not a valid option, please enter the loan ID of one of the above loans or press 'q' to quit");
            }
        }
        return loanIndex; // returns the valid index of the loan, or -1 representing the user exited
    }

    // Based off the user input, method checks if the amount is valid
    public static int processLoanPayment(Scanner scan, ArrayList<Loan> loans, int loanIndex) {
        System.out.println("Excelllent, please enter in an amount of the payment you would like to submit, or hit 'q' to quit");
        boolean inValidChoice = true;
        int paymentAmount = -1;
        while(inValidChoice) {
            // If the user entered an integer, checks if it is positive, then if it is above the minimum payment requirement and not greater than the entire loan owed
            if (scan.hasNextInt()) {
                paymentAmount = scan.nextInt();
                if (paymentAmount > 0) {
                    if (!loans.get(loanIndex).payLoan(paymentAmount)) {
                        // Amount was either less than the minimum amount required, or was greater than the actual loan, so the user is reprompted
                        System.out.println("You can enter in an another amount or hit 'q' to quit");
                        continue;
                    } else {
                        // Successful payment
                        System.out.println("Success, your payment of " + paymentAmount + " was successful");
                        break;
                    }
                } else {
                    System.out.println("I'm sorry, please enter in a positive number");
                }
            } else {
                String s = scan.next();
                // The user quits, which is represented by -1
                if (s.equals("q") || s.equals("Q")) {
                    return -1;
                } else {
                    System.out.println("I'm sorry, please enter in an integer");
                }
            }
        }
        return paymentAmount; // returns the amount the user paid or -1 of the user quit
    }

    // Inserts the required entries into the appropriate table following a payment on a loan
    public static void insertNewLoanEntries(Statement s, ArrayList<Loan> loans, int id, int loanIndex) throws SQLException {
        String q = "";
        ResultSet result;
        q = "INSERT into LoanPayments(T_ID) values ('" + id + "')";
        result = s.executeQuery(q);

        q = "INSERT into payLoan(T_ID, LoanID) values ('" + id + "', '" + loans.get(loanIndex).getLoanID() + "')";
        result = s.executeQuery(q);

        q = "UPDATE loan set loanAmount = '" + loans.get(loanIndex).getLoanAmount() + "' where loanID = '" + loans.get(loanIndex).getLoanID() + "'";
        result = s.executeQuery(q);

        q = "UPDATE loan set monthlyPayment = '" + loans.get(loanIndex).getMonthlyPayment() + "' where loanID = '" + loans.get(loanIndex).getLoanID() + "'";
        result = s.executeQuery(q);

        q = "commit";
        result = s.executeQuery(q);
        result.close();
    }

    // Displays list of credit cards based off of use input
    public static void generateListOfAvailableCreditCards(Statement s, ArrayList<Card> cards, ArrayList<Customer> customers, int customerIndex) throws SQLException {
        String q = "";
        ResultSet result;
        q = "SELECT D.cardnumber, D.expirationDate, D.cardbalance, C.creditRate, C.creditlimit, C.balanceDue from owns O, customer M, card D, creditcard C where M.ssn = O.id and O.cardid = D.cardnumber and D.cardnumber = C.cardnumber and M.ssn = '" + customers.get(customerIndex).getSSN() + "'";
        result = s.executeQuery(q);    
        int counter = 0;
        System.out.println("CardNumber          ExpirationDate     Balance      creditRate      creditLimit       balanceDue");
        System.out.println("-------------------------------------------------------------------------------------------------");
        while(result.next()) {
            String cardNumber = result.getString("CARDNUMBER");
            String cardBalance = result.getString("CARDBALANCE");
            String creditLimit = result.getString("CREDITLIMIT");
            String balanceDue = result.getString("BALANCEDUE");
            String creditRate = result.getString("CREDITRATE");
            String expirationDate = result.getString("EXPIRATIONDATE");
            cards.add(new creditCard(cardNumber, expirationDate, cardBalance, creditRate, creditLimit, balanceDue));
            String line = String.format("%9s %2s %-5s %11s %-7s %4s %-4s %10s %-5s %11s %-5s", cardNumber, "", expirationDate, "", cardBalance, "", creditRate, "", creditLimit, "", balanceDue);
            System.out.println(line);
            counter++;
            }
        System.out.println();
        result.close();
    }

    // Chooses a card from the available list based off user input
    public static int selectCard(Scanner scan, ArrayList<Card> cards) {
        String cardChoice = "";
        int index = -1;
        boolean inValidChoice = true;
        while (inValidChoice) {
            cardChoice = scan.next();
            for (int i = 0; i < cards.size(); i++) {
                // User entered a card number contained inside the list
                if (cardChoice.equals(cards.get(i).getCardNumber())) {
                    inValidChoice = false;
                    index = i;
                    break;
                // Or the user quit, represented by a -1;
                } else if (cardChoice.equals("q") || cardChoice.equals("Q")) {
                    return -1;
                }
            }
            if (inValidChoice) {
                System.out.println("I'm sorry, " + cardChoice + " is not a valid option, please enter the accountID of one of the above accounts or press 'q' to quit");
            }
        }
        return index; // returns index of the card or -1 if the user quit
    }

    // Checks if user entered amount is valid for a credit payment
    public static int processCreditPayment(Scanner scan, ArrayList<Card> cards, int cardIndex) {
        boolean inValidChoice = true;
        int paymentAmount = 0;
        while(inValidChoice) {
            if (scan.hasNextInt()) {
                paymentAmount = scan.nextInt();
                if (paymentAmount > 0) {
                    // If the user entered an int which is positive, checks if this amount is greater than the minimum payment but not larger than the total credit debt
                    if (!cards.get(cardIndex).payCredit(paymentAmount)) {
                        // Amount was less than the minimum payment or greater than the amount owed
                        System.out.println("You can enter in an another amount or hit 'q' to quit");
                        continue;
                    } else {
                        System.out.println("Success, your payment of " + paymentAmount + " was successful");
                        break;
                    }
                } else {
                    System.out.println("I'm sorry, please enter in a positive number");
                }
            } else {
                String s = scan.next();
                // Checks if user quit, which is represented by a -1
                if (s.equals("q") || s.equals("Q")) {
                    return -1;
                } else {
                    System.out.println("I'm sorry, please enter in an integer");
                }
            }
        }
        return paymentAmount; // returns payment amount or -1 if the user quit
    }

    // inserts the required entries if a credit card payment was made successfully
    public static void insertNewCreditEntries(Statement s, ArrayList<Card> cards, int id, int cardIndex) throws SQLException {
        String q = "";
        ResultSet result;
        q = "INSERT into cardPayments(T_ID) values ('" + id + "')";
        result = s.executeQuery(q);

        q = "INSERT into payCredit(T_ID, CardID) values ('" + id + "', '" + cards.get(cardIndex).getCardNumber() + "')";
        result = s.executeQuery(q);

        q = "UPDATE card set cardBalance = '" + cards.get(cardIndex).getBalance() + "' where cardNumber = '" + cards.get(cardIndex).getCardNumber() + "'";
        result = s.executeQuery(q);

        q = "UPDATE creditCard set balanceDue = '" + cards.get(cardIndex).getBalanceDue() + "' where cardnumber = '" + cards.get(cardIndex).getCardNumber() + "'";
        result = s.executeQuery(q);

        q = "commit";
        result = s.executeQuery(q);
        result.close();
    }

    // Central method for the pay loan or credit terminal
    public static void payLoanOrCredit(Statement s, Scanner scan) throws SQLException {
        // Lists that will contain all needed datat for this method
        ArrayList<String> transactionIDs = new ArrayList<String>();
        ArrayList<Customer> customers = new ArrayList<Customer>();
        ArrayList<Card> cards = new ArrayList<Card>();
        ArrayList<Loan> loans = new ArrayList<Loan>();
        // Prompts user to login as a customer, if -1 returns the user quit
        System.out.println("Welcome to the Pay Loan or Credit Terminal");
        int customerIndex = chooseCustomerLogin(s, scan, customers);
        if (customerIndex == -1) {
            return;
        }
        // Asks user to choose credit or loan, if -1 returns the user quit
        int creditOrLoan = chooseCreditOrLoan(scan);
        if (creditOrLoan == -1) {
            return;
        }

        if (creditOrLoan == 1) {
            // if the user selected loan, generate all available loans and ask the user to choose one
            generateListOfAvailableLoans(s, loans, customers, customerIndex);
            int loanIndex = selectLoan(scan, loans);
            if (loanIndex == -1) {
                return; // if the user quit
            }
            // Ask the user to enter a payment for this loan
            int paymentAmount = processLoanPayment(scan, loans, loanIndex);
            if (paymentAmount == -1) {
                return; // if the user quit
            } else {
                // Displays updated Loan, and inserts new entries into database
                System.out.println("Here is your updated loan after the payment");
                loans.get(loanIndex).calcMonthlyPayment();
                loans.get(loanIndex).display();

                int id = insertTransactionAndMakes(s, transactionIDs, customers, paymentAmount, customerIndex);
                insertNewLoanEntries(s, loans, id, loanIndex);
            }
            
        } else if (creditOrLoan == 0) {
            // If the user selected Credit, generate list of all credit cards and ask the user to choose one
            System.out.println("Below are the credit cards asssociated with this account");
            System.out.println("To submit a payment to one of these cards, please enter in the Card Number of credit card you would like to submit a payment for or hit 'q' to quit");
            generateListOfAvailableCreditCards(s, cards, customers, customerIndex);
            int cardIndex = selectCard(scan, cards);
            if (cardIndex == -1) {
                return; // if the user quit
            }

            // Prompts the user to enter a payment amount and displays the account
            System.out.println("\nExcellent, here is the information on file about this credit card");
            System.out.println("Note 'BalanceDue' refers to the minimum required payment of the card");
            System.out.println("Please enter an amount that you'd like to pay off of this credit card, or hit 'q' to quit\n");
            cards.get(cardIndex).display();
            System.out.println();

            int paymentAmount = processCreditPayment(scan, cards, cardIndex);
            if (paymentAmount == -1) {
                return; // if the user quit
            } else {
                // Displays updated account and then inserts new entries into database
                System.out.println("The transaction was successful, below is your updated account");
                cards.get(cardIndex).calcBalanceDue();
                cards.get(cardIndex).display();
                System.out.println();

                int id = insertTransactionAndMakes(s, transactionIDs, customers, paymentAmount, customerIndex);
                insertNewCreditEntries(s, cards, id, cardIndex);
            }
        } else {
            // An error occurred where the user did not quit but did not select a valid option
            // Should not logically reach this point, but this exists just in case and returns user to the main menu
            System.out.println("I'm sorry there appears to of been an error, we will be returning you to the main menu");
        }
    }

    // Generates list of all available items for the user to chooose
    public static void generateListOfAvailableItems(Statement s, ArrayList<Item> items) throws SQLException {
        String q;
        ResultSet result;
        q = "SELECT * from Item";
        result = s.executeQuery(q);
        System.out.println("Type in an itemID from one of the availablle items below to purchase it or 'q' to quit");
        String itemID = "";
        String price = "";
        String store = "";
        System.out.println("ItemID          Price          Store");
        System.out.println("------------------------------------------");
        while(result.next()) {
            itemID = result.getString("ITEMID");
            price = result.getString("PRICE");
            store = result.getString("STORE");
            items.add(new Item(itemID, price, store));
            String line = String.format("%8s %6s %-5s %8s %-10s", itemID, "", price, "", store);
            System.out.println(line);
        }
        result.close();
    }

    // User chooses an item from the available list depending upon if the user input is valid
    public static int selectItem(Scanner scan, ArrayList<Item> items) {
        int itemIndex = -1;
        String itemChoice;
        boolean inValidChoice = true;
        while(inValidChoice) {
            System.out.println();
            itemChoice = scan.next();
            for (int i = 0; i < items.size(); i++) {
                // if the user enters in an itemID that is within the array, then its valid and exits the loop
                if (itemChoice.equals(items.get(i).getItemID())) {
                    itemIndex = i;
                    inValidChoice = false;
                    break;
                // if the user quits
                } else if (itemChoice.equals("q") || itemChoice.equals("Q")) {
                    return -1;
                }
            }
            if (inValidChoice) {
                System.out.println("I'm sorry '" + itemChoice + "' is an invalid option, please enter an itemID from list above or hit 'q' to quit");
            }
        }
        return itemIndex; // returns the item index or -1 if the user quit
    }

    // Generates list of all debit cards
    public static void generateListOfAvailableDebitCards(Statement s, ArrayList<Card> cards, ArrayList<Customer> customers, int customerIndex) throws SQLException {
        String q;
        ResultSet result;
        System.out.println("Please select one of your cards to purchase this time with, or hit 'q' to quit");
        System.out.println("Select a card by entering in its Card Number\n");

        System.out.println("Debit Cards");
        System.out.println("Card #            Balance");
        System.out.println("-------------------------");
        
        q = "SELECT * from card C, debitCard D, owns O where C.cardnumber = D.cardnumber and C.cardnumber = O.cardid and O.id = '" + customers.get(customerIndex).getSSN() + "'";
        result = s.executeQuery(q);
        String cardNumber;
        String expirationDate;
        String cardBalance;
        while(result.next()) {
            cardNumber = result.getString("CARDNUMBER");
            expirationDate = result.getString("EXPIRATIONDATE");
            cardBalance = result.getString("CARDBALANCE");
            cards.add(new debitCard(cardNumber, expirationDate, cardBalance));
            String line = String.format("%16s %1s %-4s", cardNumber, "", cardBalance);
            System.out.println(line);
        }
        System.out.println();
        result.close();
    }

    // Creates a temporary account to use the Account class check balance methods later on
    public static void generateTempCheckingAccount(Statement s, ArrayList<CheckingAccount> accounts, ArrayList<Customer> customers, int customerIndex, ArrayList<Card> cards, int cardIndex) throws SQLException {
        String q;
        ResultSet result;
        // Finds the one account that matches the card chosen (if a debit card was chosen, only one account is connected to it)
        q = "SELECT * from debitCard, link, checkingAccount, account where debitcard.cardnumber = link.cardid and checkingaccount.accountid = link.accountid and link.accountid = account.accountid and debitcard.cardnumber = '" + cards.get(cardIndex).getCardNumber() + "'";
        result = s.executeQuery(q);
        while(result.next()) {
            String minBalance = result.getString("MINBALANCE");
            String accountID = result.getString("ACCOUNTID");
            String accountBalance = result.getString("ACCOUNTBALANCE");
            CheckingAccount tempAccount = new CheckingAccount(customers.get(customerIndex).getSSN(), customers.get(customerIndex).getFName(), customers.get(customerIndex).getLName(), accountID, accountBalance, "tempRate", minBalance);
            accounts.add(tempAccount);
        }
        result.close();
    }

    // Inserts the new entreis required when a debit  is entered
    public static void insertNewDebitChargesEntries(Statement s, ArrayList<Card> cards, int cardIndex, ArrayList<Item> items, int itemIndex, CheckingAccount tempAccount, String newBalance, int id) throws SQLException {
        String q;
        ResultSet result;
        q = "UPDATE account set accountBalance = '" + newBalance + "' where accountID = '" + tempAccount.getAccountID() + "'";
        result = s.executeQuery(q);

        q = "UPDATE card set cardBalance = '" + newBalance + "' where cardNumber = '" + cards.get(cardIndex).getCardNumber() + "'";
        result = s.executeQuery(q);

        q = "INSERT into purchases(T_ID) values ('" + id + "')";
        result = s.executeQuery(q);

        q = "INSERT into charges(CardID, T_ID) values ('" + cards.get(cardIndex).getCardNumber() + "', '" + id + "')";
        result = s.executeQuery(q);

        q = "INSERT into buys(ItemID, T_ID) values ('" + items.get(itemIndex).getItemID() + "', '" + id + "')";
        result = s.executeQuery(q);
        result.close();
    }

    // inserts all the required entries when a credit charge is entered
    public static void insertNewCreditChargesEntries(Statement s, ArrayList<Card> cards, int cardIndex, ArrayList<Item> items, int itemIndex, int id) throws SQLException {
        String q;
        ResultSet result;
        q = "UPDATE creditCard set balanceDue = '" + cards.get(cardIndex).getBalanceDue() + "' where cardNumber = '" + cards.get(cardIndex).getCardNumber() + "'";
        result = s.executeQuery(q);

        q = "UPDATE card set cardBalance = '" + cards.get(cardIndex).getBalance() + "' where cardNumber = '" + cards.get(cardIndex).getCardNumber() + "'";
        result = s.executeQuery(q);

        q = "INSERT into purchases(T_ID) values ('" + id + "')";
        result = s.executeQuery(q);

        q = "INSERT into charges(CardID, T_ID) values ('" + cards.get(cardIndex).getCardNumber() + "', '" + id + "')";
        result = s.executeQuery(q);

        q = "INSERT into buys(ItemID, T_ID) values ('" + items.get(itemIndex).getItemID() + "', '" + id + "')";
        result = s.executeQuery(q);
        result.close();
    }

    // Central method for the puchase using a card method
    public static void purchaseUsingCard(Statement s, Scanner scan) throws SQLException {
        // Lists that will contain all of the needed data for this terminal
        ArrayList<String> transactionIDs = new ArrayList<String>();
        ArrayList<Customer> customers = new ArrayList<Customer>();
        ArrayList<Card> cards = new ArrayList<Card>();
        ArrayList<Item> items = new ArrayList<Item>();
        // Prompts user to login and select a customer
        System.out.println("Welcome to the Purhcases using a Card Terminal");
        int customerIndex  =chooseCustomerLogin(s, scan, customers);
        if (customerIndex == -1) { 
            return; // if the user quit
        }
        // generates all the available items and prompts the user to choose an item
        generateListOfAvailableItems(s, items);
        int itemIndex = selectItem(scan, items);
        if (itemIndex == -1) {
            return; // if the user quit
        }
        // Generates a list of all debit and credit cards, and then asks the user to select a card
        generateListOfAvailableDebitCards(s, cards, customers, customerIndex);
        generateListOfAvailableCreditCards(s, cards, customers, customerIndex);
        int cardIndex = selectCard(scan, cards);
        if (cardIndex == -1) {
            return; // if the user quit
        }

        if (cards.get(cardIndex).cardType()) {
            // If a debit card was chosen, generate a temporary checking account to use it's checkBalance method
            // check balance method checks if the amount entered will be a valid transaciton for the checking account, a temporary account is used since not everything about the account is needed
            ArrayList<CheckingAccount> accounts = new ArrayList<CheckingAccount>();
            generateTempCheckingAccount(s, accounts, customers, customerIndex, cards, cardIndex);
            CheckingAccount tempAccount = accounts.get(0);
            // Generates new balance if it is a valid amount
            if (tempAccount.checkBalance(Integer.parseInt(items.get(itemIndex).getPrice()))) {
                String newBalance = tempAccount.getBalance();
                System.out.println("The Card's new balance is now " + newBalance);
                cards.get(cardIndex).setBalance(newBalance);

                // inserts the required entries when the charge is successful
                int id = insertTransactionAndMakes(s, transactionIDs, customers, Integer.parseInt(items.get(itemIndex).getPrice()), customerIndex);
                insertNewDebitChargesEntries(s, cards, cardIndex, items, itemIndex, tempAccount, newBalance, id);
                
                // displays the updated data
                System.out.println("Here is your updated debit card info\n");
                cards.get(cardIndex).display();

            } else {
                System.out.println("I'm sorry but this charge could not be processed and has been declined"); // Goes below min charge value
            }
        } else {
            // If a credit card was chosen check if the amount goes above the creditlimit
            if (cards.get(cardIndex).updateBalance(Double.parseDouble(items.get(itemIndex).getPrice()))) {
                System.out.println("Charge successful");

                cards.get(cardIndex).calcBalanceDue();
                // If cuccessful, generate the new entries
                int id = insertTransactionAndMakes(s, transactionIDs, customers, Integer.parseInt(items.get(itemIndex).getPrice()), customerIndex);
                insertNewCreditChargesEntries(s, cards, cardIndex, items, itemIndex, id);
                // Displays updated account
                System.out.println("Here is your updated credit card info\n");
                cards.get(cardIndex).display();
                System.out.println();

            } else {
                System.out.println("I'm sorry, this transaction was declined");
            }
        }
    }

    // Generatesa list of all the checking accounts that the user can choose from based off the user input
    public static void generateListOfAvailableCheckingAccounts(Statement s, ArrayList<Account> accounts, ArrayList<Customer> customers, int customerIndex) throws SQLException {
        String q;
        ResultSet result;
           
        System.out.println("Welcome " + customers.get(customerIndex).getFullName() +  ", which one of your accounts would you like to access?");
        System.out.println("Below our your accounts, please select which account you would like to look at by entering the Account ID of the account or to go back to the main menu hit 'q'\n");
        System.out.println("Checking Accounts");
        System.out.println("------------------------");
        
        
        q = "SELECT * from account A, checkingAccount C, accountLink L where A.accountid = C.accountid and L.accountid = A.accountid and L.id = '" + customers.get(customerIndex).getSSN() + "'";
        result = s.executeQuery(q);
        String accountID;
        String accountRate;
        String accountBalance;
        String minBalance;
        while(result.next()) {
            accountID = result.getString("ACCOUNTID");
            accountRate = result.getString("ACCOUNTRATE");
            accountBalance = result.getString("ACCOUNTBALANCE");
            minBalance = result.getString("MINBALANCE");
            accounts.add(new CheckingAccount(customers.get(customerIndex).getSSN(), customers.get(customerIndex).getFName(), customers.get(customerIndex).getLName(), accountID, accountBalance, accountRate, minBalance));
            System.out.println(accountID);
        }
        System.out.println();
        result.close();
    }

    // Displays the list of all available savings accounts based off of the user's input
    public static void generateListOfAvailableSavingsAccounts(Statement s, ArrayList<Account> accounts, ArrayList<Customer> customers, int customerIndex) throws SQLException {
        String q;
        ResultSet result;
           
        System.out.println("Savings Accounts");
        System.out.println("------------------------");

        q = "SELECT * from account A, savingsAccount S, accountLink L where A.accountid = S.accountid and L.accountid = A.accountid and L.id = '" + customers.get(customerIndex).getSSN() + "'";
        result = s.executeQuery(q);
        while(result.next()) {
            String accountID = result.getString("ACCOUNTID");
            String accountRate = result.getString("ACCOUNTRATE");
            String accountBalance = result.getString("ACCOUNTBALANCE");
            accounts.add(new SavingsAccount(customers.get(customerIndex).getSSN(), customers.get(customerIndex).getFName(), customers.get(customerIndex).getLName(), accountID, accountBalance, accountRate));
            System.out.println(accountID);
        }
        System.out.println();
        result.close();
    }

    // selects an account based off user input
    public static int selectAccount(Scanner scan, ArrayList<Account> accounts) {
        String accountChoice = "";
	    int index = -1;
        boolean inValidChoice = true;
        while (inValidChoice) {
            accountChoice = scan.next();
	        for (int i = 0; i < accounts.size(); i++) {
                // if the accountID the user entered is valid
	    	    if (accountChoice.equals(accounts.get(i).getAccountID())) {
                	inValidChoice = false;
			        index = i;
                    break;
                // If the user quit
            	} else if (accountChoice.equals("q") || accountChoice.equals("Q")) {
                	return -1;
                }
            }
            if (inValidChoice) {
                System.out.println("I'm sorry, " + accountChoice + " is not a valid option, please enter the accountID of one of the above accounts or press 'q' to quit");
            }
        }
        System.out.println();
        return index; // returns the index of the account or -1 if the user quit
    }

    // User selects to deposit or withdraw
    public static int withdrawOrDeposit(Scanner scan, ArrayList<Account> accounts, int accountIndex) {
        System.out.println("Here is your account, would you like to make a withdraw (Enter 'W'), deposit (Enter 'D') or quit (Enter 'Q')\n");
        accounts.get(accountIndex).display();

        boolean inValidChoice = true;
        int accountAction = -1; // 0 = deposit, 1 = withdraw
        while (inValidChoice) {
            String input = scan.next();
            if (input.equals("D") || input.equals("d")) {
                inValidChoice = false;
                accountAction = 0; // depoit
            } else if (input.equals("W") || input.equals("w")) {
                inValidChoice = false;
                accountAction = 1; // withdraw
            } else if (input.equals("Q") || input.equals("q")) {
                return -1; // quit
            } else {
                System.out.println("I'm sorry, " + input + " was not a valid option, please enter in 'D' for Deposit, 'W' for withdraw, or 'Q' to quit");
                scan.next(); // clears scanner;
            }
        }
        System.out.println();
        return accountAction;
    }

    // Generates list of all available branches
    public static void generateListOfBranches(Statement s, ArrayList<Branch> branches) throws SQLException {
        String q;
        ResultSet result;

        System.out.println("\nExcellent, now please select from one of our available branches by entering in the branch name");
        System.out.println("Or hit 'q' to quit");
        q = "SELECT * from hires";
        result = s.executeQuery(q);
        
        System.out.println("\nAvailable Branches");
        int count = 1;
        System.out.println("-------------------------------------");
        while(result.next()) {
            String employeeID = result.getString("EMPLOYEEID");
            String branchName = result.getString("BRANCHNAME");
            System.out.println(count + "). " + branchName);
            count++;
            branches.add(new Branch(employeeID, branchName));
        }
        System.out.println();
        result.close();
    }

    // selects a branch based off of user input
    public static int selectBranch(Scanner scan, ArrayList<Branch> branches) {
        String branchChoice = "";
        scan.nextLine();
        boolean inValidChoice = true;
        int branchIndex = -1;
        while(inValidChoice) {
            branchChoice = scan.nextLine();
            for (int i = 0; i < branches.size(); i++) {
                // if the branch selected is a valid branch
                if (branchChoice.equals(branches.get(i).getBranchName())) {
                    inValidChoice = false;
                    branchIndex = i;
                    break;
                // if the user quit
                }  else if (branchChoice.equals("q") || branchChoice.equals("Q")) {
                    inValidChoice = false;
                    return -1;
                }
            }
            if (inValidChoice) {
                System.out.println("I'm sorry, " + branchChoice + " is not a valid branch, please type in a valid branch name");
            }
        }
        return branchIndex; // returns the index of the branch or -1 if the user quit
    }

    // asks user to deposit a specific amount and checks if this is valid
    public static int processDeposit(Scanner scan, ArrayList<Branch> branches, int branchIndex, ArrayList<Account> accounts, int accountIndex) {
        int amount = 0;
        if (branches.get(branchIndex).hasATeller()) {
            System.out.println("\nExcellent, please enter in the amount you would like to deposit into this account");
            while (true) {
                if (scan.hasNextInt()) {
                    amount = scan.nextInt();
                    // if amount is valid, that amount is returned, otherwise -1 is returned
                    if (amount > 0) {
                        // increases the amount in the account Class
                        accounts.get(accountIndex).increaseBalance(amount);
                        System.out.println("Success, your deposit of " + amount + " into your account was successful");
                        return amount;
                    } else {
                        System.out.println("I'm sorry, please enter in a positive number");
                        amount = -1; 
                    }
                } else {
                    System.out.println("I'm sorry, please enter in a number");
                    amount = -1;
                    scan.next(); // clears scanner
                }
            }
        } else {
            System.out.println("I'm sorry, this branch does not have a teller, and as a result no deposits can be processed at this branch at this time");
            amount = -1;
            // Include a way to restart the transaction or go back
        }
        return amount;
    }

    // Asks the user to enter an amount to withdraw and checks if this amount is valid
    public static int processWithdraw(Scanner scan, ArrayList<Branch> branches, int branchIndex, ArrayList<Account> accounts, int accountIndex) {
        System.out.println("\nExcellent, please enter in the amount you would like to withdraw from this account");
        int amount = 0;
        while (true) {
            if (scan.hasNextInt()) {
                amount = scan.nextInt();
                if (amount > 0) {
                    // Checks to see if the amount requesting to be withdrawn doesn't violated any min amounts or goes into the negatives
                    if (!accounts.get(accountIndex).checkBalance(amount)) {
                        // Would add code here to allow the user to reenter another value if they do not like this
                        amount = -1;
                        break;
                    } else {
                        System.out.println("Success, your withdraw of " + amount + " into your account was successful");
                        break;
                    }
                } else {
                    System.out.println("I'm sorry, please enter in a positive number");
                    amount = -1;
                }
            } else {
                System.out.println("I'm sorry, please enter in a number");
                amount = -1;
                scan.next(); // clears scanner
            }
        }
        return amount; // returns the amount withdrawn, unless there is an error in which -1 is returned
    }

    // inserts the necessary entries for when a new deposit occurs
    public static void insertNewDepositEntries(Statement s, ArrayList<Account> accounts, int accountIndex, ArrayList<Branch> branches, int branchIndex, int id) throws SQLException {
        String q;
        ResultSet result;
        
        q = "UPDATE account set accountBalance = '" + accounts.get(accountIndex).getBalance() + "' where accountID = '" + accounts.get(accountIndex).getAccountID() + "'";
        result = s.executeQuery(q);

        q = "INSERT into Deposits(T_ID) values ('" + id + "')";
        result = s.executeQuery(q);

        q = "INSERT into D(T_ID, AccountID, tellarID) values ('" + id + "', '" + accounts.get(accountIndex).getAccountID() + "', '" + branches.get(branchIndex).getEmployeeID() + "')";
        result = s.executeQuery(q);

        q = "commit";
        result = s.executeQuery(q);
        result.close();
    }

    // inserts necessary withdraw entries when a withdraw is entered
    public static void insertNewWithdrawEntries(Statement s, ArrayList<Account> accounts, int accountIndex, ArrayList<Branch> branches, int branchIndex, int id) throws SQLException {
        String q;
        ResultSet result;
        
        q = "UPDATE account set accountBalance = '" + accounts.get(accountIndex).getBalance() + "' where accountID = '" + accounts.get(accountIndex).getAccountID() + "'";
        result = s.executeQuery(q);

        q = "INSERT into Withdraws(T_ID) values ('" + id + "')";
        result = s.executeQuery(q);

        q = "INSERT into W(T_ID, AccountID, branch) values ('" + id + "', '" + accounts.get(accountIndex).getAccountID() + "', '" + branches.get(branchIndex).getBranchName() + "')";
        result = s.executeQuery(q);

        q = "commit";
        result = s.executeQuery(q);
        result.close();
    }

    // central account despoit terminal method
    public static void AccountDepositWithdraw(Statement s, Scanner scan) throws SQLException {
        // Lists that will hold all the data needed
        ArrayList<Account> accounts = new ArrayList<Account>();
        ArrayList<Branch> branches = new ArrayList<Branch>();
        ArrayList<String> transactionIDs = new ArrayList<String>();
        ArrayList<Customer> customers = new ArrayList<Customer>();
        // Asks the user to login and select a customer to login as
        System.out.println("Welcome to the Account Deposit / Withdraw Terminal");
        int customerIndex = chooseCustomerLogin(s, scan, customers);
        if (customerIndex == -1) {
            return; // if the user quit
        }
        // Generates a list of the checking and savings accounts, and asks the user to choose an account
        generateListOfAvailableCheckingAccounts(s, accounts, customers, customerIndex);
        generateListOfAvailableSavingsAccounts(s, accounts, customers, customerIndex);
        int accountIndex = selectAccount(scan, accounts);    
        if (accountIndex == -1) {
            return; // if the user quit
        }
        // Asks the uer to choose between withdrawing and depositing
        int accountAction = withdrawOrDeposit(scan, accounts, accountIndex);
        if (accountAction == -1) { // if there was an error of the user exits
            return;
        }
        // Generates a list of branches and asks the user to choose a branch
        generateListOfBranches(s, branches);
        int branchIndex = selectBranch(scan, branches);
        if (branchIndex == -1) {
            return; // if the user quits
        }

        int amount = 0;
        if (accountAction == 0) {
            // If the user chose to deposit, asks the user how much and checks if this is valid
            amount = processDeposit(scan, branches, branchIndex, accounts, accountIndex);
        } else if (accountAction == 1) {
            // If the user chose to withdraw, asks the user how much and checks if this is valid
            amount = processWithdraw(scan, branches, branchIndex, accounts, accountIndex);
        }
        // If the transaction was not valid, the program goes back to the main menu
        if (amount == -1) {
            System.out.println("\nWe'll now be returning you to the main menu now");
            return;
        } else {
            // Otherwise the appropriate entries are created
            System.out.println("Below is your updated account\n");
            accounts.get(accountIndex).display();
            System.out.println();

            int id = insertTransactionAndMakes(s, transactionIDs, customers, amount, customerIndex);

            if (accountAction == 0) {
                insertNewDepositEntries(s, accounts, accountIndex, branches, branchIndex, id);
            } else {
                insertNewWithdrawEntries(s, accounts, accountIndex, branches, branchIndex, id);
            }
            System.out.println("Transaction #" + id + " was successful, now returning to the main menu");
        }
    }
}
