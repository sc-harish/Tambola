package com.sahaj;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

class TambolaTicketTest {

    int[][] static_number_ticket = {
            {4, 16, -1, -1, 48, -1, 63, 76, -1},
            {7, -1, 23, 38, -1, 52, -1, -1, 80},
            {9, -1, 25, -1, -1, 56, 64, -1, 83}
    };

    @Test
    void testIsRowComplete_CompleteRow() {
        TambolaTicket ticket = new TambolaTicket(static_number_ticket);
        Set<Integer> announcedNumbers = new HashSet<>(Set.of(4, 16, 48, 63, 76));
        assertTrue(ticket.isRowComplete(0, announcedNumbers), "Top row should be complete");
    }

    @Test
    void testIsRowComplete_IncompleteRow() {
        TambolaTicket ticket = new TambolaTicket(static_number_ticket);
        Set<Integer> announcedNumbers = new HashSet<>(Set.of(4, 16, 48, 63));  // Missing 76
        assertFalse(ticket.isRowComplete(0, announcedNumbers), "Top row should be incomplete");
    }

    @Test
    void testIsRowComplete_EmptyAnnouncedNumbers() {
        TambolaTicket ticket = new TambolaTicket(static_number_ticket);
        Set<Integer> announcedNumbers = new HashSet<>();
        assertFalse(ticket.isRowComplete(0, announcedNumbers), "Top row should be incomplete with no announced numbers");
    }

    @Test
    void testIsFullHouse_Complete() {
        TambolaTicket ticket = new TambolaTicket(static_number_ticket);
        Set<Integer> announcedNumbers = new HashSet<>(Set.of(
                4, 16, 48, 63, 76, 7, 23, 38, 52, 80, 9, 25, 56, 64, 83
        ));
        assertTrue(ticket.isFullHouse(announcedNumbers), "Full house should be complete");
    }

    @Test
    void testIsFullHouse_Incomplete() {
        TambolaTicket ticket = new TambolaTicket(static_number_ticket);
        Set<Integer> announcedNumbers = new HashSet<>(Set.of(
                4, 16, 48, 63, 76, 7, 23, 38, 52, 80, 9, 25, 56, 64  // Missing 83
        ));
        assertFalse(ticket.isFullHouse(announcedNumbers), "Full house should be incomplete");
    }

    @Test
    void testIsEarlyFive_Complete() {
        TambolaTicket ticket = new TambolaTicket(static_number_ticket);
        Set<Integer> announcedNumbers = new HashSet<>(Set.of(4, 16, 48, 63, 76));
        assertTrue(ticket.isEarlyFive(announcedNumbers), "Early five should be complete");
    }

    @Test
    void testIsEarlyFive_Incomplete() {
        TambolaTicket ticket = new TambolaTicket(static_number_ticket);
        Set<Integer> announcedNumbers = new HashSet<>(Set.of(4, 16, 48));  // Only 3 numbers announced
        assertFalse(ticket.isEarlyFive(announcedNumbers), "Early five should be incomplete");
    }

    @Test
    void testIsEarlyFive_ExactFive() {
        TambolaTicket ticket = new TambolaTicket(static_number_ticket);
        Set<Integer> announcedNumbers = new HashSet<>(Set.of(4, 16, 48, 63, 7));
        assertTrue(ticket.isEarlyFive(announcedNumbers), "Early five should be valid with exactly 5 numbers");
    }
}