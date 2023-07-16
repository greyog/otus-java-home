package org.example.atm.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoteTypeTest {

    @Test
    void get_amount_returns_proper_amount_for_note_type_test() {
        assertEquals(100L, NoteType.NOTE_100.getAmount().longValue());
        assertEquals(1000L, NoteType.NOTE_1000.getAmount().longValue());
        assertEquals(500L, NoteType.NOTE_500.getAmount().longValue());
        assertEquals(5000L, NoteType.NOTE_5000.getAmount().longValue());
    }

    @Test
    void get_descending_values_test() {
        NoteType[] descendingValues = NoteType.getDescendingValues();
        assertTrue(descendingValues[0].getAmount().longValue() > descendingValues[1].getAmount().longValue());
    }
}