package com.sahaj;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
class InputHandler {
    public static Set<Integer> getAnnouncedNumbers(Scanner scanner) throws NumberFormatException {
        System.out.println("Enter announced numbers:");
        String[] announcedNumbersStr = scanner.nextLine().split(",");
        Set<Integer> announcedNumbers = new HashSet<>();
        for (String num : announcedNumbersStr) {
            if (!num.trim().isEmpty()) {
                announcedNumbers.add(Integer.parseInt(num.trim()));
            }
        }
        return announcedNumbers;
    }

    public static TambolaTicket getTicket(Scanner scanner) throws NumberFormatException {
        // Assuming the ticket contains 3 rows and 5 columns with a total of 15 numbers
        int[][] ticket = new int[3][5];
        System.out.println("Enter the ticket and use _ for empty");
        for (int i = 0; i < 3; i++) {
            String[] row = scanner.nextLine().split(",");
            for (int j = 0; j < 5; j++) {
                if (!row[j].trim().equals("_")) {
                    ticket[i][j] = Integer.parseInt(row[j].trim());
                } else {
                    ticket[i][j] = -1;
                }
            }
        }
        return new TambolaTicket(ticket);
    }

    public static String getClaim(Scanner scanner) {
        System.out.println("Enter the claim being made (Top Row, Middle Row, Bottom Row, Full House, Early Five):");
        return scanner.nextLine();
    }
}