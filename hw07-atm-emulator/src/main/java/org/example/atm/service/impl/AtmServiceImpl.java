package org.example.atm.service.impl;

import lombok.AllArgsConstructor;
import org.example.atm.entity.CashBundle;
import org.example.atm.entity.NoteType;
import org.example.atm.service.AtmService;
import org.example.atm.service.CashBundleService;

import java.util.HashMap;
import java.util.Map;


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
        return cashBundleService.getTotalAmount(cashBundle).intValue();
    }

    @Override
    public CashBundle getCash(long amount) {
        Map<NoteType, Integer> result = new HashMap<>();

        for (NoteType noteType : NoteType.getDescendingValues()) {
            int bundleNoteCount = cashBundle.getNoteTypeCount(noteType);
            int resultNoteCount = (int) Math.min(amount / noteType.getAmount().longValue(), bundleNoteCount);
            amount = amount - resultNoteCount * noteType.getAmount().longValue();
            result.put(noteType, resultNoteCount);
        }

        if (amount == 0) {
            CashBundle resultBundle = new CashBundle(result);
            cashBundle = cashBundleService.subtract(cashBundle, resultBundle);
            return resultBundle;
        }

        throw new IllegalArgumentException("Can't give proper bundle for requested amount.");
    }
}
