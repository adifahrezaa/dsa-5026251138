package lw02.unguided;
import java.util.Scanner;
import java.util.Stack;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {

        LinkedList<String[]> orders = new LinkedList<>();
        LinkedList<String[]> food = new LinkedList<>();
        LinkedList<String[]> drink = new LinkedList<>();
        LinkedList<String[]> successOrders = new LinkedList<>();

        Scanner sc2 = new Scanner(Main.class.getResourceAsStream("orders.txt"));

        while (sc2.hasNext()) {
            String name = sc2.next();
            String sideDish = sc2.next();
            String drinkOrder = sc2.next();
            int table = sc2.nextInt();
            orders.add(new String[]{name, sideDish, drinkOrder, String.valueOf(table)});
        }
    sc2.close();

    
    for (String[] foodStock : food) {
        String name = "Bakso";
        int amount = 5;
        food.add(new String[]{name, String.valueOf(amount)});

        String name2 = "Sate";
        int amount2 = 1;
        food.add(new String[]{name2, String.valueOf(amount2)});

        String  name3 = "Soto";
        int amount3 = 2;
        food.add(new String[]{name3, String.valueOf(amount3)});
    }

    for (String[] drinkStock : drink) {
        String name = "EsTeh";
        int amount = 4;
        drink.add(new String[]{name, String.valueOf(amount)});

        String name2 = "EsJeruk";
        int amount2 = 2;
        drink.add(new String[]{name2, String.valueOf(amount2)});
    }

    LinkedList<String[]> queue = new LinkedList<>();
    for (String[] order : orders) {
        queue.add(order);
    }

    while (!queue.isEmpty()) {
        String[] currentOrder = queue.poll();
        String name = currentOrder[0];
        String sideDish = currentOrder[1];
        String drinkOrder = currentOrder[2];
        String table = currentOrder[3];

        Stack <String[]> failedOrders = new Stack<>();
        for (String[] foodStock : food) {
            if (foodStock[0].equals("Bakso")) {
                if (Integer.parseInt(foodStock[1]) > 0) {
                    foodStock[1] = String.valueOf(Integer.parseInt(foodStock[1]) - 1);
                }
            } else if (foodStock[0].equals("Sate")) {
                if (Integer.parseInt(foodStock[1]) > 0) {
                    foodStock[1] = String.valueOf(Integer.parseInt(foodStock[1]) - 1);
                }
            } else if (foodStock[0].equals("Soto")) {
                if (Integer.parseInt(foodStock[1]) > 0) {
                    foodStock[1] = String.valueOf(Integer.parseInt(foodStock[1]) - 1);
                } else if (foodStock.equals("0")) {
                    failedOrders.push(currentOrder);
                } 
                    
            }
} 
        for (String[] drinkStock : drink) {
            if (drinkStock[0].equals("EsTeh")) {
                if (Integer.parseInt(drinkStock[1]) > 0) {
                    drinkStock[1] = String.valueOf(Integer.parseInt(drinkStock[1]) - 1);
                }
            } else if (drinkStock[0].equals("EsJeruk")) {
                if (Integer.parseInt(drinkStock[1]) > 0) {
                    drinkStock[1] = String.valueOf(Integer.parseInt(drinkStock[1]) - 1);
                }
                else if (drinkStock.equals("0")) {
                    failedOrders.push(currentOrder);
                }
            }
        }

        successOrders.add(new String[]{name, sideDish, drinkOrder, table});
    }

    System.out.println("=== Successful Orders ===");
    for (String[] order : successOrders) {
        System.out.println (order[0] + order[1] + order[2] + order[3]);
    }

    System.out.println("=== Remaining Food Stock ===");
    for (String[] foodStock : food) {
        System.out.println(foodStock[0] + foodStock[1]);
    }
    System.out.println("=== Remaining Drink Stock ===");
    for (String[] drinkStock : drink) {
        System.out.println(drinkStock[0] + drinkStock[1]);
    }
    System.out.println("=== Failed Orders ===");
    
    }
}