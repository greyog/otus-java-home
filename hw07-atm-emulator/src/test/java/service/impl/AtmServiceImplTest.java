package service.impl;

import entity.CashBundle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.CashBundleService;

import static org.junit.jupiter.api.Assertions.*;

class AtmServiceImplTest {

    private AtmServiceImpl atmServiceImpl;
    private final CashBundleService defaultBundleService = new CashBundleServiceImpl();

    @BeforeEach
    void setUp() {
        atmServiceImpl = new AtmServiceImpl(defaultBundleService);
    }

    @Test
    void acceptCashBundle_total_amount_increases_on_input_bundle_amount() {
        CashBundle bundle = new CashBundle(1, 1, 1, 1);
        int inputBundleAmount = 100 + 500 + 1000 + 5000;
        int beforeAtmAmount = atmServiceImpl.getTotalAmount();

        atmServiceImpl.acceptCashBundle(bundle);

        int resultAtmAmount = atmServiceImpl.getTotalAmount();

        assertEquals(beforeAtmAmount + inputBundleAmount, resultAtmAmount);
    }

    @Test
    void getTotalAmount_returns_exact_amount_of_initial_bundle() {
        CashBundle bundle = new CashBundle(1, 1, 1, 1);
        int inputBundleAmount = 100 + 500 + 1000 + 5000;
        AtmServiceImpl testAtmService = new AtmServiceImpl(defaultBundleService, bundle);

        int atmAmount = testAtmService.getTotalAmount();

        assertEquals(inputBundleAmount, atmAmount);
    }

    @Test
    void getCash_returns_proper_bundle_for_valid_requested_amount() {
        CashBundle bundle = atmServiceImpl.getCash(6600);

        assertEquals(1, bundle.getNotes5000());
        assertEquals(1, bundle.getNotes1000());
        assertEquals(1, bundle.getNotes500());
        assertEquals(1, bundle.getNotes100());
    }

    @Test
    void getCash_throws_on_invalid_requested_amount() {
        assertThrows(IllegalArgumentException.class, () -> atmServiceImpl.getCash(5001));
    }

    @Test
    void getCash_throws_on_too_big_requested_amount() {
        CashBundle bundle = new CashBundle(1, 1, 1, 1);
        int inputBundleAmount = 100 + 500 + 1000 + 5000;
        AtmServiceImpl testAtmService = new AtmServiceImpl(defaultBundleService, bundle);

        assertThrows(IllegalArgumentException.class,
                () -> testAtmService.getCash(inputBundleAmount + 200));
    }
}