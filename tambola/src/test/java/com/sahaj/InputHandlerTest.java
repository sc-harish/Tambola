package com.sahaj;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import java.util.Scanner;
import java.util.Set;
import java.io.ByteArrayInputStream;
import java.util.HashSet;

class InputHandlerTest {

    @Test
    void testGetAnnouncedNumbers_ValidInput() {
        Scanner scanner = mock(Scanner.class);
        when(scanner.nextLine()).thenReturn("1, 2, 3, 4, 5");

        Set<Integer> expected = new HashSet<>(Set.of(1, 2, 3, 4, 5));
        Set<Integer> result = InputHandler.getAnnouncedNumbers(scanner);

        assertEquals(expected, result, "The announced numbers should match the input.");
    }

    @Test
    void testGetAnnouncedNumbers_EmptyInput() {
        Scanner scanner = mock(Scanner.class);
        when(scanner.nextLine()).thenReturn("");

        Set<Integer> expected = new HashSet<>();
        Set<Integer> result = InputHandler.getAnnouncedNumbers(scanner);

        assertEquals(expected, result, "Empty input should result in an empty set.");
    }

    @Test
    void testGetAnnouncedNumbers_WithWhitespace() {
        Scanner scanner = mock(Scanner.class);
        when(scanner.nextLine()).thenReturn(" 1 ,  2 , 3 , 4 , 5 ");

        Set<Integer> expected = new HashSet<>(Set.of(1, 2, 3, 4, 5));
        Set<Integer> result = InputHandler.getAnnouncedNumbers(scanner);

        assertEquals(expected, result, "Whitespace should be trimmed, and numbers should match.");
    }

    @Test
    void testGetAnnouncedNumbers_InvalidNumberFormat() {
        Scanner scanner = mock(Scanner.class);
        when(scanner.nextLine()).thenReturn("1, abc, 3, 4, 5");

        assertThrows(NumberFormatException.class, () -> InputHandler.getAnnouncedNumbers(scanner),
                "Invalid number format should throw NumberFormatException.");
    }

    @Test
    void testGetTicket_ValidInput() {
        Scanner scanner = mock(Scanner.class);
        when(scanner.nextLine())
                .thenReturn("1, 2, _, 4, 5")  // Row 1
                .thenReturn("6, _, 8, 9, _")  // Row 2
                .thenReturn("_, 11, 12, _, 15");  // Row 3

        int[][] expectedTicket = {
                {1, 2, -1, 4, 5},
                {6, -1, 8, 9, -1},
                {-1, 11, 12, -1, 15}
        };

        TambolaTicket ticket = InputHandler.getTicket(scanner);
        assertArrayEquals(expectedTicket, ticket.getTicket(), "The generated ticket should match the input.");
    }

    @Test
    void testGetTicket_WithWhitespaceAndEmptyCells() {
        Scanner scanner = mock(Scanner.class);
        when(scanner.nextLine())
                .thenReturn(" 1 , _, _, 4 , 5 ")  // Row 1
                .thenReturn(" 6 , _ , 8 , 9 , _ ")  // Row 2
                .thenReturn(" _ , 11 , 12 , _ , 15 ");  // Row 3

        int[][] expectedTicket = {
                {1, -1, -1, 4, 5},
                {6, -1, 8, 9, -1},
                {-1, 11, 12, -1, 15}
        };

        TambolaTicket ticket = InputHandler.getTicket(scanner);
        assertArrayEquals(expectedTicket, ticket.getTicket(), "The generated ticket should correctly handle whitespace and empty cells.");
    }

    @Test
    void testGetClaim_ValidClaim() {
        Scanner scanner = mock(Scanner.class);
        when(scanner.nextLine()).thenReturn("Top Row");

        String result = InputHandler.getClaim(scanner);
        assertEquals("Top Row", result, "The returned claim should match the input.");
    }

    @Test
    void testGetClaim_WithWhitespace() {
        Scanner scanner = mock(Scanner.class);
        when(scanner.nextLine()).thenReturn("  Full House ");

        String result = InputHandler.getClaim(scanner);
        assertEquals("  Full House ", result, "The returned claim should include leading and trailing spaces.");
    }

    @Test
    void testGetClaim_EmptyClaim() {
        Scanner scanner = mock(Scanner.class);
        when(scanner.nextLine()).thenReturn("");

        String result = InputHandler.getClaim(scanner);
        assertEquals("", result, "An empty claim input should return an empty string.");
    }

    @Test
    void testGetAnnouncedNumbersWithInvalidNumberFormat() {
        String input = "1, a, 3, 4, 5";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        assertThrows(NumberFormatException.class, () -> InputHandler.getAnnouncedNumbers(scanner));
    }

    @Test
    void testGetTicketWithInvalidNumberFormat() {
        String input = "1,2,3,4,5\n6,7,x,9,10\n11,12,13,14,15";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        assertThrows(NumberFormatException.class, () -> InputHandler.getTicket(scanner));
    }

    @Test
    void testGetTicketWithIncompleteInput() {
        String input = "1,2,3,4\n6,7,_,9,10\n11,12,13,14,15";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> InputHandler.getTicket(scanner));
    }

    @Test
    void testGetTicketWithEmptyCells() {
        String input = "_,_,_,_,_\n_,_,_,_,_\n_,_,_,_,_";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        TambolaTicket ticket = InputHandler.getTicket(scanner);

        int[][] expected = {
                {-1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1}
        };
        assertArrayEquals(expected, ticket.getTicket());
    }
}
