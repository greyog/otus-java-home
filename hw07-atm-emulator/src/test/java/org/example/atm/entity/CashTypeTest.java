package org.example.atm.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CashTypeTest {

    @Test
    void getAmountReturnsProperAmountForNoteTypeTest() {
        assertEquals(100L, CashType.NOTE_100.getAmount().longValue());
        assertEquals(1000L, CashType.NOTE_1000.getAmount().longValue());
        assertEquals(500L, CashType.NOTE_500.getAmount().longValue());
        assertEquals(5000L, CashType.NOTE_5000.getAmount().longValue());
    }
}