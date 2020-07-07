/* Balance Generator */
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
public class balanceGenerator {
    public static void main(String args[]) {
        try {
            FileWriter myWriter = new FileWriter("loanRate.txt");
            FileWriter myWriter2 = new FileWriter("loanAmount.txt");
            double Balance[] = new double[20];
            for (int i = 0; i < Balance.length; i++) {
                int num = 0;
                int num2 = 0;
                while (true) {
                    num = (int)(10 * Math.random());
                    num2 = (int)(1000000 * Math.random());
                    if (num < 1) {
                        continue;
                    }
                        /*
                        for (int j = 0; j < socialSecurities.length; j++) {
                            if ( num == socialSecurities[j]) {
                                continue;
                            }
                        } */
                        break;
                }
                //socialSecurities[i] = num;
                myWriter.write(String.valueOf(num) + "\n");
                myWriter2.write(String.valueOf(num2) + "\n");
                //System.out.println(num);
            }
            myWriter.close();
            myWriter2.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}