package org.example.atm.service.impl;

import org.example.atm.entity.CashBundle;
import org.example.atm.entity.NoteType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.example.atm.service.CashBundleService;

import java.util.Map;

import static org.example.atm.entity.NoteType.*;
import static org.junit.jupiter.api.Assertions.*;

class AtmServiceImplTest {

    private AtmServiceImpl atmServiceImpl;
    private final CashBundleService defaultBundleService = new CashBundleServiceImpl();

    @BeforeEach
    void setUp() {
        CashBundle cashBundle = new CashBundle(Map.of(
                NOTE_100, 100,
                NOTE_500, 100,
                NOTE_1000, 100,
                NOTE_5000, 100));
        atmServiceImpl = new AtmServiceImpl(defaultBundleService, cashBundle);
    }

    @Test
    void acceptCashBundle_total_amount_increases_on_input_bundle_amount() {
        CashBundle cashBundle = new CashBundle(Map.of(
                NOTE_100, 1,
                NOTE_500, 1,
                NOTE_1000, 1,
                NOTE_5000, 1));
        int inputBundleAmount = 100 + 500 + 1000 + 5000;
        int beforeAtmAmount = atmServiceImpl.getTotalAmount();

        atmServiceImpl.acceptCashBundle(cashBundle);

        int resultAtmAmount = atmServiceImpl.getTotalAmount();

        assertEquals(beforeAtmAmount + inputBundleAmount, resultAtmAmount);
    }

    @Test
    void getTotalAmount_returns_exact_amount_of_initial_bundle() {
        CashBundle cashBundle = new CashBundle(Map.of(
                NOTE_100, 1,
                NOTE_500, 1,
                NOTE_1000, 1,
                NOTE_5000, 1));
        int inputBundleAmount = 100 + 500 + 1000 + 5000;
        AtmServiceImpl testAtmService = new AtmServiceImpl(defaultBundleService, cashBundle);

        int atmAmount = testAtmService.getTotalAmount();

        assertEquals(inputBundleAmount, atmAmount);
    }

    @Test
    void getCash_returns_proper_bundle_for_valid_requested_amount() {
        CashBundle bundle = atmServiceImpl.getCash(6600L);

        assertEquals(1, bundle.getNoteTypeCount(NOTE_5000));
        assertEquals(1, bundle.getNoteTypeCount(NOTE_1000));
        assertEquals(1, bundle.getNoteTypeCount(NOTE_500));
        assertEquals(1, bundle.getNoteTypeCount(NOTE_100));
    }

    @Test
    void getCash_throws_on_invalid_requested_amount() {
        assertThrows(IllegalArgumentException.class, () -> atmServiceImpl.getCash(5001));
    }

    @Test
    void getCash_throws_on_too_big_requested_amount() {
        CashBundle cashBundle = new CashBundle(Map.of(
                NOTE_100, 1,
                NOTE_500, 1,
                NOTE_1000, 1,
                NOTE_5000, 1));
        int inputBundleAmount = 100 + 500 + 1000 + 5000;
        AtmServiceImpl testAtmService = new AtmServiceImpl(defaultBundleService, cashBundle);

        assertThrows(IllegalArgumentException.class,
                () -> testAtmService.getCash(inputBundleAmount + 200));
    }
}