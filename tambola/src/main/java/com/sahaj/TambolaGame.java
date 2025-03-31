package com.sahaj;
import java.util.*;

class TambolaGame {
    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)) {
            Set<Integer> announcedNumbers = InputHandler.getAnnouncedNumbers(scanner);
            TambolaTicket ticket = InputHandler.getTicket(scanner);
            String claim = InputHandler.getClaim(scanner);
            boolean result = ClaimValidator.validate(claim, ticket, announcedNumbers);
            System.out.println(result ? "Accepted" : "Rejected");
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}