package org.example.atm.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoteTypeTest {

    @Test
    void getAmountReturnsProperAmountForNoteTypeTest() {
        assertEquals(100L, NoteType.NOTE_100.getAmount().longValue());
        assertEquals(1000L, NoteType.NOTE_1000.getAmount().longValue());
        assertEquals(500L, NoteType.NOTE_500.getAmount().longValue());
        assertEquals(5000L, NoteType.NOTE_5000.getAmount().longValue());
    }

    @Test
    void getDescendingValuesTest() {
        NoteType[] descendingValues = NoteType.getDescendingValues();
        assertTrue(descendingValues[0].getAmount().longValue() > descendingValues[1].getAmount().longValue());
    }
}