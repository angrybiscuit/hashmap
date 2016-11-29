import hashfunctions.HashFunction;
import hashfunctions.HashFunction1;
import hashfunctions.HashFunction2;
import hashmaps.ChainHashMap;
import hashmaps.HashMap;
import hashmaps.InnerChainHashMap;
import help.FileReader;
import help.Stats;

import java.util.Scanner;
import java.util.StringJoiner;

public class Program {
    public static FileReader fileReader = new FileReader();

    public static void main(String[] args) {
        options();
    }

    public static void dataTestting() {
        System.out.println("BinarnieZewnetrznie");
        mapTester("numbers.txt", "BinarnieZewnetrznie.txt", new HashFunction2(), false);
        System.out.println("SrodkowychBitowZewnetrznie");
        mapTester("numbers.txt", "SrodkowychBitowZewnetrznie.txt", new HashFunction1(), false);
        System.out.println("BinarnieWewnetrznie");
        mapTester("numbers.txt", "BinarnieWewnetrznie.txt", new HashFunction2(), true);
        System.out.println("SrodkowychBitowWewnetrznie");
        mapTester("numbers.txt", "SrodkowychBitowWewnetrznie.txt", new HashFunction1(), true);

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
    public static long[] readFromFile(String filename) {
        return fileReader.readFile(filename);
    }
    public static void mapTester(String fileToRead, String fileToWrite, HashFunction hashFunction, boolean innerMap) {
        long[] arr = readFromFile(fileToRead);

        HashMap map = createMap(arr.length, hashFunction, innerMap);
        map.setFileName(fileToWrite);
        for (int i = 0; i < arr.length; i++) {
            map.add(arr[i]);
        }
        Stats stats = map.displayToTXT();
        System.out.println(stats);
    }
    public static void options() {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Opcje:\n[1]Odczyt z pliku i zapisanie wynikow [2]Rozszezone mozliwosci [3]Testowanie [0]Zakoncz");
            switch (input.nextInt()) {
                case 1:
                    mapTester(fileName("odczytu"), fileName("zapisu"), hashFunOptions(), hashMapOptions());
                    break;
                case 2:
                    playWithMap(createMap(MOptions(), hashFunOptions(), hashMapOptions()));
                    break;
                case 3:
                    dataTestting();
                    break;
                case 0:
                    System.exit(0);
                    break;
            }
        }
    }
    public static boolean hashMapOptions() {
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
    public static HashFunction hashFunOptions() {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("fun1() -> (M * ((k * a) % 1))");
            System.out.println("fun2() -> (k ^ (k >>> 7) ^ (k >>> 4))");
            System.out.println("Wybor funkcji haszujacej:\n[1]fun1() [2]fun2() [0]Zakoncz");
            switch (input.nextInt()) {
                case 1:
                    return new HashFunction1();
                case 2:
                    return new HashFunction2();
                case 0:
                    System.exit(0);
                    break;
            }
        }
    }
    public static int MOptions() {
        Scanner input = new Scanner(System.in);
        System.out.println("Podanie wielkosci tablicy M");
        return input.nextInt();
    }
    public static String fileName(String text) {
        Scanner input = new Scanner(System.in);
        System.out.println("Podanie nazwy pliku do "+text);
        return input.next();
    }
    public static Long keyValue() {
        Scanner input = new Scanner(System.in);
        System.out.println("Podaj wartosc klucza");
        return input.nextLong();
    }
    public static void playWithMap(HashMap map) {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Wybor: [1]Dodaj [2]Znajc [3]Usun [4]Wyswietl [5]Zapisz w TXT [0]Zakoncz");
            switch (input.nextInt()) {
                case 1:
                    System.out.println(map.put(keyValue()));
                    break;
                case 2:
                    System.out.println(map.get(keyValue()));
                    break;
                case 3:
                    System.out.println(map.remove(keyValue()));
                    break;
                case 4:
                    map.display();
                    break;
                case 5:
                    map.setFileName(fileName("zapisu"));
                    System.out.println(map.displayToTXT());
                    break;
                case 0:
                    System.exit(0);
                    break;
            }
        }
    }

}
