import hashfunctions.HashFunction;
import hashfunctions.HashFunction1;
import hashfunctions.HashFunctionTest;
import hashmaps.ChainHashMap;
import hashmaps.HashMap;
import hashmaps.InnerChainHashMap;
import help.FileReader;
import help.Stats;

public class Program {

    public static void main(String[] args) {
        //mapper();
        //iMapper();

        FileReader f = new FileReader();
        long[] arr = f.readFile("test.txt");

        HashMap map = new ChainHashMap(arr.length, new HashFunctionTest(), "testHashmap2.txt");
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i]);
        }
        System.out.println(map.displayToTXT());

    }
    public static void mapper() {
        FileReader f = new FileReader();
        long[] arr = f.readFile("test.txt");

        ChainHashMap map = new ChainHashMap(arr.length, new HashFunction1(), "testHashmap.txt");

        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i]);
        }
        Stats stats = map.displayToTXT();
        System.out.println(stats);
    }
    public static void iMapper() {
        FileReader f = new FileReader();
        long[] arr = f.readFile("numbers.txt");

        InnerChainHashMap map = new InnerChainHashMap(arr.length, new HashFunction1(), "testInnerMap.txt");

        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i]);
        }
        Stats stats = map.displayToTXT();
        System.out.println(stats);
    }

}
