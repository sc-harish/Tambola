package com.sahaj;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class ClaimValidatorTest {
    Set<Integer> announcedNumbers = new HashSet<>(Set.of(1, 2, 3, 4, 5));

    @Mock
    TambolaTicket ticket;

    @Test
    void testTopRowClaim() {
        when(ticket.isRowComplete(anyInt(), anySet())).thenReturn(true);
        assertTrue(ClaimValidator.validate("Top Row", ticket, announcedNumbers), "Top Row claim should be valid");
    }

    @Test
    void testMiddleRowClaim() {
        when(ticket.isRowComplete(anyInt(), anySet())).thenReturn(true);
        assertTrue(ClaimValidator.validate("Middle Row", ticket, announcedNumbers), "Middle Row claim should be valid");
    }

    @Test
    void testBottomRowClaim() {
        when(ticket.isRowComplete(anyInt(), anySet())).thenReturn(true);
        assertTrue(ClaimValidator.validate("Bottom Row", ticket, announcedNumbers), "Bottom Row claim should be valid");
    }

    @Test
    void testFullHouseClaim() {
        when(ticket.isFullHouse(anySet())).thenReturn(true);
        assertTrue(ClaimValidator.validate("Full House", ticket, announcedNumbers), "Full House claim should be valid");
    }

    @Test
    void testEarlyFiveClaim() {
        when(ticket.isEarlyFive(anySet())).thenReturn(true);
        assertTrue(ClaimValidator.validate("Early Five", ticket, announcedNumbers), "Early Five claim should be valid");
    }

    @Test
    void testInvalidClaim() {
        assertFalse(ClaimValidator.validate("Invalid Claim", ticket, announcedNumbers), "Invalid claim should be false");
    }
}
