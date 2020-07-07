/* Insert File Code */
import java.io.*;
import java.sql.*;
import java.util.*;

public class insertCode {
    public static void main(String args[]) throws FileNotFoundException{
        try {
        
        /* FAILED ATTEMPT AT TRYING TO DO IT THROUGH SQL DISK SOMETHING
        try (
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edgar1.cse.lehigh.edu:1521:cse241", "bts222", "P856918280");
            Statement s = con.createStatement();
        ) {
            File Fnames = new File("FirstNameList.txt");
            File Lnames = new File("LastNames.txt");
            File DOBs = new File("DOB.txt");
            File SSN = new File("SocialSecurityNums.txt");
            File AccountIDs = new File("AccountIds.txt");

            String line = "";

            Scanner scrn1 = new Scanner(SSN);
            Scanner scrn2 = new Scanner(Fnames);
            Scanner scrn3 = new Scanner(Lnames);
            Scanner scrn4 = new Scanner(DOBs);
            Scanner scrn5 = new Scanner(AccountIDs);

            while(scrn1.hasNextLine()) {
                line = "('" + scrn1.nextLine() + "', " + 
                "'" + scrn2.nextLine() + "', " +
                "'" + scrn3.nextLine() + "', " +
                "'" + scrn4.nextLine() + "', " +
                "'" + scrn5.nextLine() + "');";
                
                String q;
                ResultSet result;
                q = "INSERT into Customer(SSN, firstName, lastName, DOB, AccountID)" + "\n" + 
                    "Values " + line;
                result = s.executeQuery(q);
            }
            scrn1.close();
            scrn2.close();
            scrn3.close();
            scrn4.close();
            scrn5.close();
        } catch (Exception e) {
            System.out.println(e);
        } */

        /* CREATES CUSTOMER COMMAND LINES
            File Fnames = new File("FirstNameList.txt");
            File Lnames = new File("LastNames.txt");
            File DOBs = new File("DOB.txt");
            File SSN = new File("SocialSecurityNums.txt");
            File AccountIDs = new File("AccountIds.txt");

            String line = "";

            Scanner scrn1 = new Scanner(SSN);
            Scanner scrn2 = new Scanner(Fnames);
            Scanner scrn3 = new Scanner(Lnames);
            Scanner scrn4 = new Scanner(DOBs);
            Scanner scrn5 = new Scanner(AccountIDs);

            while(scrn1.hasNextLine()) {
                line = "INSERT into Customer(SSN, firstName, lastName, DOB, AccountID) values ('" + scrn1.nextLine() + "', " + 
                "'" + scrn2.nextLine() + "', " +
                "'" + scrn3.nextLine() + "', " +
                "'" + scrn4.nextLine() + "', " +
                "'" + scrn5.nextLine() + "');";

                System.out.println(line);
            }
            

            scrn1.close();
            scrn2.close();
            scrn3.close();
            scrn4.close();
            scrn5.close();
            */

            /* CREATES CARD COMMAND LINES
            FileWriter myWriter = new FileWriter("insertCardCommands.txt");

            File CardNum = new File("CardNumbers.txt");
            File ExpirationDate = new File("ExpirationDate.txt");
            File balance = new File("Balance.txt");

            String line = "";

            Scanner scrn1 = new Scanner(CardNum);
            Scanner scrn2 = new Scanner(ExpirationDate);
            Scanner scrn3 = new Scanner(balance);

            while(scrn1.hasNextLine()) {
                line = "INSERT into Card(cardNumber, ExpirationDate, cardBalance) values ('" + scrn1.nextLine() + "', " + 
                "'" + scrn2.nextLine() + "', " + scrn3.nextLine() + ");\n";
                myWriter.write(line);
                //System.out.println(line);
                
            }
            
            scrn1.close();
            scrn2.close();
            scrn3.close();

            myWriter.close();
            */

            /* OWNS INSERT COMMANDS 
            FileWriter myWriter = new FileWriter("insertOwnsCommands.txt");

            File CardNum = new File("CardNumbers.txt");
            File SSN = new File("SocialSecurityNums.txt");

            String line = "";

            Scanner scrn2 = new Scanner(CardNum);
            Scanner scrn1 = new Scanner(SSN);

            while(scrn1.hasNextLine()) {
                line = "INSERT into Owns(ID, CardID) values ('" + scrn1.nextLine() + "', " + 
                "'" + scrn2.nextLine() + "');\n";
                myWriter.write(line);
                //System.out.println(line);
                
            }
            
            scrn1.close();
            scrn2.close();

            myWriter.close();

        } catch (IOException e) {
            System.out.println(e);
        } */

        /* CREDIT AND DEBIT INSERT COMMANDS
            FileWriter myWriter = new FileWriter("insertCreditCardsCommands.txt");
            FileWriter myWriter2 = new FileWriter("insertDebitCardCommands.txt");

            File CardNum = new File("CardNumbers.txt");
            File expirationDate = new File("ExpirationDate.txt");
            File balance = new File("Balance.txt");
            File interestRate = new File("CreditInterestRates.txt");
            File creditLimit = new File("CreditLimits.txt");

            String line = "";

            Scanner scrn1 = new Scanner(CardNum);
            Scanner scrn2 = new Scanner(interestRate);
            Scanner scrn3 = new Scanner(creditLimit);
            Scanner scrn4 = new Scanner(expirationDate);
            Scanner scrn5 = new Scanner(balance);

            while(scrn2.hasNextLine()) {
                line = "INSERT into creditCard(CardNumber, creditRate, balanceDue, creditLimit, expirationDate, cardBalance) values ('" + scrn1.nextLine() + "', " + 
                scrn2.nextLine() + ", " + 0 + ", "  + scrn3.nextLine() + ", " + "'" + scrn4.nextLine() + "', " + scrn5.nextLine() + ");\n";
                myWriter.write(line);
                //System.out.println(line);
            }
            while(scrn1.hasNextLine()) {
                line = "INSERT into debitCard(CardNumber, expirationDate, cardBalance) values ('" + scrn1.nextLine() + "', " + "'" + scrn4.nextLine() + "', " + scrn5.nextLine() + ");\n";
                myWriter2.write(line);
            }
            
            scrn1.close();
            scrn2.close();
            scrn3.close();
            scrn4.close();
            scrn5.close();

            myWriter.close();
            myWriter2.close();

        } catch (IOException e) {
            System.out.println(e);
        } */

        /* INSERT TRANSACTION COMMANDS */
        FileWriter myWriter = new FileWriter("TransactionCommands.txt");

        File T_ID = new File("transactionIDs.txt");
        File Date = new File("transactionDates.txt");
        File amount = new File("transactionAmounts.txt");

        String line = "";

        Scanner scrn1 = new Scanner(T_ID);
        Scanner scrn2 = new Scanner(Date);
        Scanner scrn3 = new Scanner(amount);

        while(scrn1.hasNextLine()) {
            line = "INSERT into transaction(T_ID, transactionDate, amount) values ('" + scrn1.nextLine() + "', " + 
            "'" + scrn2.nextLine() + "', "  + scrn3.nextLine() + ");\n";
            myWriter.write(line);
            //System.out.println(line);
        }
        
        scrn1.close();
        scrn2.close();
        scrn3.close();

        myWriter.close();

    } catch (IOException e) {
        System.out.println(e);
    } 

    /* COMMANDS FOR ALL AGGREGATES OF TRANSACTION
    FileWriter myWriter = new FileWriter("insertCardPaymentsCommands.txt");
    FileWriter myWriter2 = new FileWriter("insertPurchasesCommands.txt");
    FileWriter myWriter3 = new FileWriter("insertLoanPaymentsCommands.txt");
    FileWriter myWriter4 = new FileWriter("insertBorrowCommands.txt");
    FileWriter myWriter5 = new FileWriter("insertDepositCommands.txt");
    FileWriter myWriter6 = new FileWriter("insertWithdrawCommands.txt");

        File T_ID = new File("transactionIDs.txt");
        String line = "";
        Scanner scrn1 = new Scanner(T_ID);
        int num = 1;
        while(num <= 10) {
            line = "INSERT into cardPayments(cardPaymentNum, T_ID) values (" + num + ", '" + scrn1.nextLine() + "');\n";
            myWriter.write(line);
            num++;
            //System.out.println(line);
        }
        num = 0;
        while(num < 10) {
            line = "INSERT into Purchases(T_ID) values ('" + scrn1.nextLine() + "');\n";
            myWriter2.write(line);
            num++;
            //System.out.println(line);
        }
        num = 0;
        while(num < 10) {
            line = "INSERT into LoanPayments(T_ID) values ('" + scrn1.nextLine() + "');\n";
            myWriter3.write(line);
            num++;
            //System.out.println(line);
        }
        num = 0;
        while(num < 10) {
            line = "INSERT into Borrows(T_ID) values ('" + scrn1.nextLine() + "');\n";
            myWriter4.write(line);
            num++;
            //System.out.println(line);
        }
        num = 0;
        while(num < 10) {
            line = "INSERT into Deposits(T_ID) values ('" + scrn1.nextLine() + "');\n";
            myWriter5.write(line);
            num++;
            //System.out.println(line);
        }
        num = 0;
        while(scrn1.hasNextLine()) {
            line = "INSERT into Withdraws(T_ID) values ('" + scrn1.nextLine() + "');\n";
            myWriter6.write(line);
            num++;
            //System.out.println(line);
        }

        scrn1.close();

        myWriter.close();
        myWriter2.close();
        myWriter3.close();
        myWriter4.close();
        myWriter5.close();
        myWriter6.close();

    } catch (IOException e) {
        System.out.println(e);
    } */

    /* MAKES COMMAND
    FileWriter myWriter = new FileWriter("insertMakesCommands.txt");

        File T_ID = new File("transactionIDs.txt");
        File ID = new File("SocialSecurityNums.txt");

        String line = "";

        Scanner scrn1 = new Scanner(ID);
        Scanner scrn2 = new Scanner(T_ID);

        while(scrn2.hasNextLine()) {
            line = "INSERT into makes(ID, T_ID) values ('" + scrn1.nextLine() + "', " + 
            "'" + scrn2.nextLine() + "');\n";
            myWriter.write(line);
            //System.out.println(line);
        }
        
        scrn1.close();
        scrn2.close();

        myWriter.close();

    } catch (IOException e) {
        System.out.println(e);
    } */

    /* COMMANDS FOR INSERTING ACCOUNTS
    FileWriter myWriter = new FileWriter("insertAccountCommands.txt");

        File ID = new File("AccountIDs.txt");
        File Rate = new File("AccountRate.txt");
        File Balance = new File("AccountBalance.txt");

        String line = "";

        Scanner scrn1 = new Scanner(ID);
        Scanner scrn2 = new Scanner(Rate);
        Scanner scrn3 = new Scanner(Balance);

        while(scrn1.hasNextLine()) {
            line = "INSERT into Account(AccountID, AccountRate, AccountBalance) values ('" + scrn1.nextLine() + "', " + 
            scrn2.nextLine() + ", " + scrn3.nextLine() + ");\n";
            myWriter.write(line);
            //System.out.println(line);
        }
        
        scrn1.close();
        scrn2.close();
        scrn3.close();

        myWriter.close();

    } catch (IOException e) {
        System.out.println(e);
    } */
    
    /* CHECKING AND SAVINGS ACCOUNT COMMANDS 
    FileWriter myWriter = new FileWriter("insertCheckingAccountCommands.txt");
    FileWriter myWriter2 = new FileWriter("insertSavingsAccountCommands.txt");

    File ID = new File("AccountIDs.txt");
    File minBalance = new File("minBalance.txt");

    String line = "";

    Scanner scrn1 = new Scanner(ID);
    Scanner scrn2 = new Scanner(minBalance);

    while(scrn2.hasNextLine()) {
        line = "INSERT into checkingAccount(AccountID, minBalance) values ('" + scrn1.nextLine() + "', " + 
        scrn2.nextLine() + ");\n";
        myWriter.write(line);
        //System.out.println(line);
    }
    while(scrn1.hasNextLine()) {
        line = "INSERT into savingsAccount(AccountID) values ('" + scrn1.nextLine() + "');\n";
        myWriter2.write(line);
        //System.out.println(line);
    }
    
    scrn1.close();
    scrn2.close();

    myWriter.close();
    myWriter2.close();

} catch (IOException e) {
    System.out.println(e);
}  */

/*
    FileWriter myWriter = new FileWriter("insertLoanCommands.txt");

    File ID = new File("loanID.txt");
    File amount = new File("loanAmount.txt");
    File rate = new File("loanRate.txt");

    String line = "";

    Scanner scrn1 = new Scanner(ID);
    Scanner scrn2 = new Scanner(amount);
    Scanner scrn3 = new Scanner(rate);

    while(scrn1.hasNextLine()) {
        line = "INSERT into loan(loanID, loanAmount, monthlyPayment, loanRate) values ('" + scrn1.nextLine() + "', " + 
        scrn2.nextLine() + ", " + 1 + ", " + scrn3.nextLine() + ");\n";
        myWriter.write(line);
        //System.out.println(line);
    }
    
    scrn1.close();
    scrn2.close();
    scrn3.close();

    myWriter.close(); 

} catch (IOException e) {
    System.out.println(e);
} */
    }
} 