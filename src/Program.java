import hashfunctions.HashFunction;
import hashfunctions.HashFunction1;
import hashfunctions.HashFunctionTest;
import hashmaps.ChainHashMap;
import hashmaps.HashMap;
import hashmaps.InnerChainHashMap;
import help.FileReader;
import help.Stats;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        mapTester("test.txt", "testHashmap.txt", new HashFunctionTest(), false);

    }
    public static HashMap createMap(int M, HashFunction hashFunction, boolean innerMap) {
        HashMap map;

        if (innerMap) {
            map = new InnerChainHashMap(M, hashFunction);
        } else {
            map = new ChainHashMap(M, hashFunction);
        }
        return map;
    }
    public static void mapTester(String fileToRead, String fileToWrite, HashFunction hashFunction, boolean innerMap) {
        FileReader f = new FileReader();
        long[] arr = f.readFile(fileToRead);
        HashMap map = createMap(arr.length, hashFunction, innerMap);
        map.setFileName(fileToWrite);

        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i]);
        }
        Stats stats = map.displayToTXT();
        System.out.println(stats);
    }
    public static void options() {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Opcja:\n[1]Odczyt z pliku i zapisanie wynikow [2]Wszystkie mozliwosci [0]Zakoncz");
            switch (input.nextInt()) {
                case 1:
                    //return false;
                case 2:
                    //return true;
                case 0:
                    System.exit(0);
                    break;
            }
        }
    }
    public static boolean hashMapOptions(){
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Wybor hashmapu:\n[1]Lancuchowy [2]Lancuchowy wewnetrznie [0]Zakoncz");
            switch (input.nextInt()) {
                case 1:
                    return false;
                case 2:
                    return true;
                case 0:
                    System.exit(0);
                    break;
            }
        }
    }
    public static HashFunction hashFunOptions(){
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Wybor funkcji haszujacej:\n[1]fun1() [2]funTest() [0]Zakoncz");
            switch (input.nextInt()) {
                case 1:
                    return new HashFunction1();
                case 2:
                    return new HashFunctionTest();
                case 0:
                    System.exit(0);
                    break;
            }
        }
    }
    public static int MOptions(){
        Scanner input = new Scanner(System.in);
        System.out.println("Podanie wielkosci tablicy M");
        return input.nextInt();
    }

}
