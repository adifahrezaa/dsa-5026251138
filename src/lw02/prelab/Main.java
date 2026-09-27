package lw02.prelab;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("transactions.txt"));
        
        LinkedList<String[]> transactions = new LinkedList<>();
        LinkedList<String[]> customers = new LinkedList<>();


        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split(" ");

            transactions.add(parts);
        }

        for (String[] transaction : transactions) {
            String name = transaction[0];
            boolean found = false;

            for (String[] customer : customers) {
                if (customer[0].equals(name)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                customers.add(new String[]{name, "0"});
            }
        }

        LinkedList<String[]> queue = new LinkedList<>();
        for (String[] transaction : transactions) {
            queue.add(transaction);
         } 
         
         Stack<String[]> failedTransaction = new Stack<>();
         while (!queue.isEmpty()) {
                String[] currentTransaction = queue.poll();
                String name = currentTransaction[0];
                String type = currentTransaction[1];
                int amount = Integer.parseInt(currentTransaction[2]);

                for (String[] customer : customers) {
                    if (customer[0].equals(name)) {
                        int currentAmount = Integer.parseInt(customer[1]);
                        if (type.equals("DEPOSIT")) {
                            currentAmount += amount;
                        } else if (type.equals("WITHDRAW")) {
                            if (currentAmount >= amount) {
                                currentAmount -= amount;
                            } else {
                                failedTransaction.push(currentTransaction);
                            }
                        }
                        customer[1] = String.valueOf(currentAmount);
                        break;
                    }
                }
            }
        System.out.println("=== Final Balances ===");
        for (String[] customer : customers) {
            System.out.println(customer[0] + " : " + customer[1]);
        }
        System.out.println("=== Failed Transactions ===");
        while (!failedTransaction.isEmpty()) {
            String[] transaction = failedTransaction.pop();
            System.out.println(transaction[0] + " " + transaction[1] + " " + transaction[2]);
        }
        sc.close();
    }
}