package lw04.prelab;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    Scanner sc = new Scanner (Main.class.getResourceAsStream("branches.txt"));

    int N = sc.nextInt();
    int[] SV = new int[N];
    
    for (int i = 0; i < N; i++) {
        SV[i] = sc.nextInt();
    }

    int pass = 3;

    for (int i = 1; i <= pass; i++) {
        int key = SV[i];
        int j = i - 1;
        while (j >= 0 && SV[j] < key) {
            SV[j + 1] = SV[j];
            j--;
        }
        SV[j + 1] = key;
}

    int M = sc.nextInt();

    int[] target = new int[M];
    for (int i = 0; i < M; i++) {
        target[i]  = sc.nextInt();
    }

    for (int i = 0; i < N; i++) {
    if (i > 0) {
        System.out.print(" S");
    }
    System.out.print(SV[i]);
    }
    System.out.println();

    for (int i = 0; i < M; i++) {
        int t = target[i];
        int hasil = linearSearch(SV, target[i]);
        if (hasil == -1) {
            System.out.println(t + " : NOT FOUND");
        } else {
            System.out.println(t + " : FOUND at index " + hasil);
        }
        sc.close();
    }
    }

        static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }
}