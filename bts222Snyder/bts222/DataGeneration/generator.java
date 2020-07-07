import java.util.*;
import java.io.*;

public class generator {
    public static void main(String args[]) {
        try {
            File transaction = new File("testing.txt");
            FileWriter myWriter = new FileWriter("transactionIDs.txt");
            FileWriter myWriter2 = new FileWriter("transactionDates.txt");
            FileWriter myWriter3 = new FileWriter("transactionAmounts.txt");
            int transactions[] = new int[9];
            ArrayList<String> list = new ArrayList<String>();
            Scanner scrn = new Scanner(transaction);
            while (scrn.hasNextLine()) {
                String id = scrn.nextLine();
                list.add(id);
            }
            for (int i = 0; i < transactions.length; i++) {
                int num1 = 0;
                int num2 = 0;
                int day = 0;
                int month = 0;
                int year = 0;
                String line = "";
                while (true) {
                    num1 = (int)(10000000 * Math.random());
                    num2 = (int)(1000 * Math.random());
                    day = (int)(31 * Math.random());
                    month = (int)(13 * Math.random());
                    year = (int)(2020 * Math.random());
                    if (day == 0) {
                        continue;
                    }
                    if (month == 0) {
                        continue;
                    }
                    if (year > 2020 || year < 1950) {
                        continue;
                    }
                    line = String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year);
                    if (num1 > 1000000) {
                        for (int j = 0; j < transactions.length; j++) {
                            if (num1 == transactions[j] || list.contains(Integer.toString(num1))) {
                                continue;
                            }
                        }
                        break;
                    }
                }
                //socialSecurities[i] = num;
                myWriter.write(String.valueOf(num1) + "\n");
                myWriter3.write(String.valueOf(num2) + "\n");
                myWriter2.write(line + "\n");
                //System.out.println(num);
            }

            myWriter.close();
            myWriter2.close();
            myWriter3.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}