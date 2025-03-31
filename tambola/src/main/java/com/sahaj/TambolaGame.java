package com.sahaj;
import java.util.*;

class TambolaGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Set<Integer> announcedNumbers = InputHandler.getAnnouncedNumbers(scanner);
        TambolaTicket ticket = InputHandler.getTicket(scanner);
        String claim = InputHandler.getClaim(scanner);

        boolean result = ClaimValidator.validate(claim, ticket, announcedNumbers);
        System.out.println(result ? "Accepted" : "Rejected");
    }
}