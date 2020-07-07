/* Credit Card Generator */
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
public class CreditCardGenerator {
    public static void main(String args[]) {
        try {
            FileWriter myWriter = new FileWriter("CreditInterestRates.txt");
            FileWriter myWriter2 = new FileWriter("CreditLimits.txt");
            int creditCards[] = new int[50];
            for (int i = 0; i < creditCards.length; i++) {
                int interestRate = 0;
                int creditLimit = 0;
                double balanceDue = 0;
                while (true) {
                    interestRate = (int)(100 * Math.random());
                    creditLimit = (int)(10000 * Math.random());
                    if (interestRate > 30) {
                        continue;
                    }
                    break;
                }
                //socialSecurities[i] = num;
                myWriter.write(String.valueOf(interestRate) + "\n");
                myWriter2.write(String.valueOf(creditLimit) + "\n");
                //System.out.println(num);
            }
            myWriter.close();
            myWriter2.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}