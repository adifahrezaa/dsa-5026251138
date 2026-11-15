package lw02.unguided;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    // cari entri stok berdasarkan nama, null kalau tidak ada
    static String[] find(LinkedList<String[]> stock, String name) {
        for (String[] item : stock) {
            if (item[0].equals(name)) return item;
        }
        return null;
    }

    static boolean available(String[] item) {
        return item != null && Integer.parseInt(item[1]) > 0;
    }

    static void reduce(String[] item) {
        item[1] = String.valueOf(Integer.parseInt(item[1]) - 1);
    }

    public static void main(String[] args) {
        LinkedList<String[]> food = new LinkedList<>();
        LinkedList<String[]> drink = new LinkedList<>();
        LinkedList<String[]> queue = new LinkedList<>();
        LinkedList<String[]> successOrders = new LinkedList<>();
        Stack<String[]> failedOrders = new Stack<>();

        // isi stok awal (langsung, tanpa loop)
        food.add(new String[]{"Bakso", "5"});
        food.add(new String[]{"Sate", "1"});
        food.add(new String[]{"Soto", "2"});
        drink.add(new String[]{"EsTeh", "4"});
        drink.add(new String[]{"EsJeruk", "2"});

        // baca order ke queue
        Scanner sc = new Scanner(Main.class.getResourceAsStream("orders.txt"));
        while (sc.hasNext()) {
            String name = sc.next();
            String sideDish = sc.next();
            String drinkOrder = sc.next();
            int table = sc.nextInt();
            queue.add(new String[]{name, sideDish, drinkOrder, String.valueOf(table)});
        }
        sc.close();

        // proses order satu per satu (FIFO)
        while (!queue.isEmpty()) {
            String[] order = queue.poll();
            String[] f = find(food, order[1]);
            String[] d = find(drink, order[2]);

            if (available(f) && available(d)) {   // cek keduanya dulu
                reduce(f);
                reduce(d);
                successOrders.add(order);
            } else {
                failedOrders.push(order);
            }
        }

        System.out.println("=== Successful Orders ===");
        for (String[] o : successOrders) {
            System.out.println(o[0] + " " + o[1] + " " + o[2] + " " + o[3]);
        }

        System.out.println("=== Remaining Food Stock ===");
        for (String[] s : food) {
            System.out.println(s[0] + " " + s[1]);
        }

        System.out.println("=== Remaining Drink Stock ===");
        for (String[] s : drink) {
            System.out.println(s[0] + " " + s[1]);
        }

        System.out.println("=== Failed Orders ===");
        while (!failedOrders.isEmpty()) {
            String[] o = failedOrders.pop();
            System.out.println(o[0] + " " + o[1] + " " + o[2] + " " + o[3]);
        }
    }
}