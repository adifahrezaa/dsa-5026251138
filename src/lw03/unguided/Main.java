package lw03.unguided;

import java.util.Scanner;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.HashMap;


public class Main {
    public static void main(String[] args) {
        System.out.println("===== Event Check-in Results =====");
    Scanner sc = new Scanner (Main.class.getResourceAsStream("registrations.txt"));

    int total = 0;
    int rejected = 0;
    int succsess = 0;

    Set <String> students = new LinkedHashSet<>();

    while (sc.hasNext()) {
        String id = sc.next();
        if (!students.contains(id)) {
            students.add(id);
            total++;
    }
    }

    sc.close();

    Scanner sc2 = new Scanner (Main.class.getResourceAsStream("checkins.txt"));

    Map<String, String> checkins = new HashMap<>();

    while (sc2.hasNext()) {
        String attendance = sc2.next();
        if (checkins.containsKey(attendance)) {
            checkins.put(attendance, "Already Checked in");
            rejected++;
            System.out.println(attendance + ": Rejected (Already Checked in)");
        } else {
            if (students.contains(attendance)) {
            checkins.put(attendance, "Checked in");
            System.out.println(attendance + ": Checked in");
            succsess++;
            } else {
                checkins.put(attendance, "Not Registered");
                rejected++;
                System.out.println(attendance + ": Rejected (Not Registered)");
            }
        }
    }
    sc2.close();
    System.out.println();
    System.out.println("===== Final Event Summary =====");
    System.out.println("Registered students: " + total);
    System.out.println("Successful check-ins: " + succsess);
    System.out.println("Absent students: " + (total - succsess));
    System.out.println("Rejected attempts: " + rejected);
}
}
