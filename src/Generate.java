import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

public class Generate {
    private Random r = new Random();

    public int randomNum() {
        return 100000000 + r.nextInt(900000000);
    }
    public int[] numArray(long number){
        int length = String.valueOf(number).length();
        long mod = 10;
        long dif = 1;
        int[] mas = new int[length];
        for (int i = length-1; i >= 0; i--) {
            mas[i] = (int)((number % mod) / dif);
            mod *= 10;
            dif *= 10;
        }
        return mas;
    }
    public int calcControlNumber(int[] numArr) {
        int[] sums = new int[3];
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sums[j] += numArr[index];
                index++;
            }
        }
        return (sums[0] * sums[1]) + sums[2];
    }
    public long generateNumber() {
        long output = randomNum();
        int controlNum = calcControlNumber(numArray(output));
        output *= 1000;
        output += controlNum;
        return output;
    }
    public void writeToTXT(String fileName, int numberOfWrites) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(fileName, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < numberOfWrites; i++) {
            writer.println(generateNumber());
        }
        writer.close();
    }
}