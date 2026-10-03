package lw03.prelab;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.HashMap;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner (Main.class.getResourceAsStream("playlist.txt"));

        List<String> playlist = new ArrayList<>();

        while (sc.hasNext()) {
            String perintah = sc.next();
            if (perintah.equals("ADD")) {
                String song = sc.next();
                playlist.add(song);
            } else if (perintah.equals("INSERT")) {
                int index = sc.nextInt();
                String song = sc.next();
                playlist.add(index, song);
            } else if (perintah.equals("REMOVE")) {
                String song = sc.next();
                playlist.remove(song);
            }
        }
        sc.close();
        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }

        Scanner sc2 = new Scanner (Main.class.getResourceAsStream("participants.txt"));
        Set<String> participants = new LinkedHashSet<>();

            int duplikat = 0;
            int nomor = 1;

        while (sc2.hasNext()) {
            String name = sc2.next();
            
            if (!participants.contains(name)) {
                participants.add(name);

            } else {
                duplikat++;
            }
        }
        sc2.close();
        System.out.println();
        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + participants.size());

        for (String name : participants) {
            System.out.println(nomor + ". " + name);
            nomor++;
        }
        System.out.println("Duplicate participants: " + duplikat);

        Scanner sc3 = new Scanner (Main.class.getResourceAsStream("inventory.txt"));

        Map<String, Integer> inventory = new HashMap<>();

        int failedSales = 0;

        while (sc3.hasNext()) {
            String type = sc3.next();
            
            if(type.equals("ADD")) {
                String product = sc3.next();
                int quantity = sc3.nextInt();
                if (inventory.containsKey(product)) {
                    inventory.put(product, inventory.get(product) + quantity);
                } else {
                    inventory.put(product, quantity);
                }
            } else if (type.equals("SELL")) {
                String product = sc3.next();
                int quantity = sc3.nextInt();
                if (inventory.containsKey(product) && inventory.get(product) >= quantity) {
                    inventory.put(product, inventory.get(product) - quantity);
                } else {
                    failedSales++;
                }
            }
        }
        sc3.close();
        System.out.println();
        System.out.println("===== Problem 3 =====");
        for (String product : inventory.keySet()) {
            System.out.println(product + ": " + inventory.get(product));
        }
        System.out.println("Failed sales: " + failedSales);
    }
}
