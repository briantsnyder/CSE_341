/* DOB Generator */
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
public class DOBGenerator {
    public static void main(String args[]) {
        try {
            FileWriter myWriter = new FileWriter("ExpirationDate.txt");
            String DOB [] = new String[100];
            for (int i = 0; i < DOB.length; i++) {
                String temp = "";
                int day = 0;
                int month = 0;
                int year = 0;
                while (true) {
                    //day = (int)(31 * Math.random());
                    month = (int)(13 * Math.random());
                    year = (int)(2050 * Math.random());
                    if (month == 0) {
                        continue;
                    }
                    if (year < 2020) {
                        continue;
                    }
                    break;
                }
                //temp = String.valueOf(month) + "/" + String.valueOf(day) + "/" + String.valueOf(year);
                temp = String.valueOf(month) + "/" + String.valueOf(year);
                DOB[i] = temp;
                myWriter.write(temp + "\n");
                System.out.println(temp);
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}