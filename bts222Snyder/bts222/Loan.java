// Loan class which saves and returns info about a loan
public class Loan {
    String loanID;
    String loanAmount;
    String loanRate;
    String monthlyPayment;

    public Loan(String loanID, String loanAmont, String loanRate, String monthlyPayment) {
        this.loanID = loanID;
        this.loanAmount = loanAmont;
        this.loanRate = loanRate;
        this.monthlyPayment = monthlyPayment;
        calcMonthlyPayment(); // Calls this method upon creation to ensure the monthly payment is the most up to date
    }

    public String getLoanID() {
        return loanID;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public String getLoanRate() {
        return loanRate;
    }

    public String getMonthlyPayment() {
        return monthlyPayment;
    }

    // Calculates Monthly Payment by multiplying the total debt times the rate
    public void calcMonthlyPayment() {
        double rate = Double.parseDouble(loanRate);
        rate = rate / 100;
        double amount = Double.parseDouble(loanAmount);
        double payment = (int)((100*(rate * amount)) / 100);
        monthlyPayment = Double.toString(payment);
    }

    // Checks if the amount attempting to be paid is more than the current debt, or less than the minimum, if neither than this amount is subtracted from the total debt
    public boolean payLoan(int num) {
        double amount = Double.parseDouble(loanAmount);
        double temp = amount - num;
        double payment = Double.parseDouble(monthlyPayment);
        if (temp < 0) {
            System.out.println("I'm sorry this payment is more than is currently owed, and thus cannot be accepted");
            return false;
        } else if (temp == 0) {
            System.out.println("Congrautulations, you have successfully paid off your entire loan");
            loanAmount = Double.toString(temp);
            // add in code for calc monthly payment maybe
            return true;
        } else if (num >= payment) {
            System.out.println("Your payment is more than the required minimum amount, but less than the total due, so this payment is accepted");
            loanAmount = Double.toString(temp);
            return true;
        } else {
            System.out.println("This amount is less than the required monthly payment, and thus cannot be accepted");
            return false;
        }
    }

    // prints the loan
    public void display() {
        String line1 = "LoanID    Loan Amount   Loan Rate   Monthly Payment";
        String line2 = "-----------------------------------------------------";
        String line3 = String.format("%8s %3s %-5s %3s %-4s %3s %-5s", loanID, "", loanAmount, "", loanRate, "", monthlyPayment);
        System.out.println(line1);
        System.out.println(line2);
        System.out.println(line3);
        System.out.println();
    }
}