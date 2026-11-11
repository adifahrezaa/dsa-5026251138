package lw04.unguided;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    Scanner sc = new Scanner (Main.class.getResourceAsStream("parcels.txt"));

    int N = sc.nextInt();
    int [] shelf = new int[N];

    for (int i = 0; i < N; i++) {
        shelf[i] = sc.nextInt();
    }

    int M = sc.nextInt();
    int [] targets = new int[M];
    for (int i = 0; i < M; i++) {
        targets[i] = sc.nextInt();
    }
    sc.close();
    int pass = 2;
    int[] copy = new int[N];

    for (int i = 0; i < N - 1; i++) {
        for (int j = 0; j < N - 1 - i; j++) {
            if (shelf[j] < shelf[j + 1]) {
                int temp = shelf[j];
                shelf[j] = shelf[j + 1];
                shelf[j + 1] = temp;
            }
        }
        if (i == pass - 1) {
            for (int k = 0; k < N; k++) {
                copy[k] = shelf[k];
            }
        }
    }
    System.out.println("==== After " + pass + " Passes ====");

for (int i = 0; i < N; i++) {
    System.out.print(copy[i]);
    if (i < N - 1) {
        System.out.print(" ");
    }
}
    System.out.println();

    System.out.println("==== Fully Sorted ====");
    for (int i = 0; i < N; i++) {
        System.out.print(shelf[i]);
        if (i < N - 1) {
            System.out.print(" ");
        }
    }
    System.out.println();

    System.out.println("==== Search Results ====");
    for (int t = 0; t < M; t++) {
        int target = targets[t];
        int low = 0;
        int high = N - 1;
        int found = -1;
        int step = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            step++;
            if (shelf[mid] == target) {
                found = mid;
                break;
            } else if (shelf[mid] < target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        if (found != -1) {
            System.out.println(target + ": FOUND at index " + found + " (steps: " + step + ")");
        } else {
            System.out.println(target + ": NOT FOUND (steps: " + step + ")");
        }
    }
}
}