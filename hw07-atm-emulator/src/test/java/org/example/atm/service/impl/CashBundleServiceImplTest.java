package org.example.atm.service.impl;

import org.example.atm.entity.CashBundle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.example.atm.service.CashBundleService;

import static org.junit.jupiter.api.Assertions.*;

class CashBundleServiceImplTest {

    private static CashBundleService bundleService;

    @BeforeAll
    static void setup() {
        bundleService = new CashBundleServiceImpl();
    }

    @Test
    void add() {
        CashBundle b1 = new CashBundle(1, 1, 1, 1);
        CashBundle b2 = new CashBundle(1, 1, 1, 1);

        CashBundle result = bundleService.add(b1, b2);

        assertEquals(b1.getNotes100() + b2.getNotes100(), result.getNotes100());
        assertEquals(b1.getNotes500() + b2.getNotes500(), result.getNotes500());
        assertEquals(b1.getNotes1000() + b2.getNotes1000(), result.getNotes1000());
        assertEquals(b1.getNotes5000() + b2.getNotes5000(), result.getNotes5000());
    }

    @Test
    void subtract_valid_bundles_returns_proper_result() {
        CashBundle b1 = new CashBundle(1, 1, 1, 1);
        CashBundle b2 = new CashBundle(1, 1, 1, 1);

        CashBundle result = bundleService.subtract(b1, b2);

        assertEquals(b1.getNotes100() - b2.getNotes100(), result.getNotes100());
        assertEquals(b1.getNotes500() - b2.getNotes500(), result.getNotes500());
        assertEquals(b1.getNotes1000() - b2.getNotes1000(), result.getNotes1000());
        assertEquals(b1.getNotes5000() - b2.getNotes5000(), result.getNotes5000());
    }

    @Test
    void subtract_greater_bundle_from_less_throws() {
        CashBundle b1 = new CashBundle(1, 1, 1, 1);
        CashBundle b2 = new CashBundle(1, 2, 1, 1);

        assertThrows(IllegalArgumentException.class, () -> bundleService.subtract(b1, b2));

    }
}