package org.example.atm.service.impl;

import org.example.atm.entity.CashBundle;
import org.example.atm.entity.NoteType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.example.atm.service.CashBundleService;

import java.util.Map;

import static org.example.atm.entity.NoteType.*;
import static org.example.atm.entity.NoteType.NOTE_5000;
import static org.junit.jupiter.api.Assertions.*;

class CashBundleServiceImplTest {

    private static CashBundleService bundleService;

    @BeforeAll
    static void setup() {
        bundleService = new CashBundleServiceImpl();
    }

    @Test
    void add() {
        CashBundle b1 = new CashBundle(Map.of(
                NOTE_100, 1,
                NOTE_500, 1,
                NOTE_1000, 1,
                NOTE_5000, 1));
        CashBundle b2 = new CashBundle(Map.of(
                NOTE_100, 1,
                NOTE_500, 1,
                NOTE_1000, 1,
                NOTE_5000, 1));

        CashBundle result = bundleService.add(b1, b2);

        for (NoteType noteType : values()) {
            int expected = b1.getNoteTypeCount(noteType) + b2.getNoteTypeCount(noteType);
            assertEquals(expected, result.getNoteTypeCount(noteType));
        }
    }

    @Test
    void subtract_valid_bundles_returns_proper_result() {
        CashBundle b1 = new CashBundle(Map.of(
                NOTE_100, 1,
                NOTE_500, 1,
                NOTE_1000, 1,
                NOTE_5000, 1));
        CashBundle b2 = new CashBundle(Map.of(
                NOTE_100, 1,
                NOTE_500, 1,
                NOTE_1000, 1,
                NOTE_5000, 1));

        CashBundle result = bundleService.subtract(b1, b2);

        for (NoteType noteType : values()) {
            int expected = b1.getNoteTypeCount(noteType) - b2.getNoteTypeCount(noteType);
            assertEquals(expected, result.getNoteTypeCount(noteType));
        }
    }

    @Test
    void subtract_greater_bundle_from_less_throws() {
        CashBundle b1 = new CashBundle(Map.of(
                NOTE_100, 1,
                NOTE_500, 1,
                NOTE_1000, 1,
                NOTE_5000, 1));
        CashBundle b2 = new CashBundle(Map.of(
                NOTE_100, 1,
                NOTE_500, 2,
                NOTE_1000, 1,
                NOTE_5000, 1));

        assertThrows(IllegalArgumentException.class, () -> bundleService.subtract(b1, b2));

    }
}