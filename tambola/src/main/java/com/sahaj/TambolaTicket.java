package com.sahaj;

import java.util.Set;

class TambolaTicket {
    private final int[][] ticket;

    public TambolaTicket(int[][] ticket) {
        this.ticket = ticket;
    }

    public boolean isRowComplete(int rowIndex, Set<Integer> announcedNumbers) {
        for (int num : ticket[rowIndex]) {
            if (num != -1 && !announcedNumbers.contains(num)) {
                return false;
            }
        }
        return true;
    }

    public boolean isFullHouse(Set<Integer> announcedNumbers) {
        for (int[] row : ticket) {
            for (int num : row) {
                if (num != -1 && !announcedNumbers.contains(num)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isEarlyFive(Set<Integer> announcedNumbers) {
        int count = 0;
        for (int[] row : ticket) {
            for (int num : row) {
                if (num != -1 && announcedNumbers.contains(num)) {
                    count++;
                    if (count == 5) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int[][] getTicket() {
        return ticket;
    }
}