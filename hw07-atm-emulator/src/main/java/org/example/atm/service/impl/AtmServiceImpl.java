package org.example.atm.service.impl;

import lombok.AllArgsConstructor;
import org.example.atm.entity.CashBundle;
import org.example.atm.service.AtmService;
import org.example.atm.service.CashBundleService;


@AllArgsConstructor
public class AtmServiceImpl implements AtmService {
    private final CashBundleService cashBundleService;
    private CashBundle cashBundle;

    @Override
    public void acceptCashBundle(CashBundle inputBundle) {
        this.cashBundle = cashBundleService.add(this.cashBundle, inputBundle);
    }

    @Override
    public int getTotalAmount() {
        return cashBundle.getNotes100() * 100 + cashBundle.getNotes500() * 500 + cashBundle.getNotes1000() * 1000 + cashBundle.getNotes5000() * 5000;
    }

    @Override
    public CashBundle getCash(int amount) {
        int notes5000 = Math.min(amount / 5000, this.cashBundle.getNotes5000());
        amount = amount - notes5000 * 5000;

        int notes1000 = Math.min(amount / 1000, this.cashBundle.getNotes1000());
        amount = amount - notes1000 * 1000;

        int notes500 = Math.min(amount / 500, this.cashBundle.getNotes500());
        amount = amount - notes500 * 500;

        int notes100 = Math.min(amount / 100, this.cashBundle.getNotes100());
        amount = amount - notes100 * 100;

        if (amount == 0) {
            CashBundle resultBundle = new CashBundle(notes100, notes500, notes1000, notes5000);
            this.cashBundle = cashBundleService.subtract(this.cashBundle, resultBundle);
            return resultBundle;
        }

        throw new IllegalArgumentException("Can't give proper bundle for requested amount.");
    }
}
