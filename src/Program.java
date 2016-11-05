import java.util.HashSet;

public class Program {

    public static void main(String[] args) {
        //mapper();
        //iMapper();

    }
    public static void mapper() {
        FileReader f = new FileReader();
        long[] arr = f.readFile("test.txt");

        ChainHashMap map = new ChainHashMap(arr.length, "testHashmap.txt");

        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i]);
        }
        Stats stats = map.displayTotxt();
        System.out.println(stats);
    }
    public static void iMapper() {
        FileReader f = new FileReader();
        long[] arr = f.readFile("numbers.txt");

        InnerChainHashMap map = new InnerChainHashMap(arr.length, "testInnerMap.txt");

        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i]);
        }
        Stats stats = map.displayTotxt();
        System.out.println(stats);
    }

}
