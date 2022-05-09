package com.ironhack.TheDataLayerHomework3.utils;
import com.ironhack.TheDataLayerHomework3.models.Lead;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Utils {
    public static Scanner scanner = new Scanner(System.in);
    static String title = "CRM Program";
    private List<Lead> leads = new ArrayList<>();

    public static void printLikeError(String message) {
        System.out.println((char) 27 + "[31m" + message);
        System.out.print((char) 27 + "[39m");
    }

    public static void clearConsole() {
        for (int i = 0; i < 15; i++) {
            System.out.println();
        }
    }

    public static void printSeparator(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void printHeading(String title) {
        printSeparator(30);
        System.out.println((char) 27 + "[35m" + title + (char) 27+ "[39m" );
        printSeparator(30);
    }

    public static void anythingToContinue() {
        System.out.println((char) 27 + "[32m"  + "\nPlease Enter any command to continue..."  + (char) 27 +  "[39m" );
        scanner.next();
    }

    public static String shortUUID() {
        UUID uuid = UUID.randomUUID();
        int l = ByteBuffer.wrap(uuid.toString().getBytes()).getInt();
        return Integer.toString(l, Character.MAX_RADIX);
    }
}
