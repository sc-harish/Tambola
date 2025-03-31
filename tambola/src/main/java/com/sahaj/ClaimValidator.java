package com.sahaj;

import java.util.Set;

class ClaimValidator {
    public static boolean validate(String claim, TambolaTicket ticket, Set<Integer> announcedNumbers) {
        switch (claim) {
            case "Top Row":
                return ticket.isRowComplete(0, announcedNumbers);
            case "Middle Row":
                return ticket.isRowComplete(1, announcedNumbers);
            case "Bottom Row":
                return ticket.isRowComplete(2, announcedNumbers);
            case "Full House":
                return ticket.isFullHouse(announcedNumbers);
            case "Early Five":
                return ticket.isEarlyFive(announcedNumbers);
            default:
                return false;
        }
    }
}
