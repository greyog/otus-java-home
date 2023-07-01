package org.example.atm.service.impl;

import org.example.atm.entity.CashBundle;
import org.example.atm.entity.CashType;
import org.example.atm.service.CashBundleService;

import java.util.HashMap;
import java.util.Map;

public class CashBundleServiceImpl implements CashBundleService {

    @Override
    public CashBundle add(CashBundle bundle1, CashBundle bundle2) {
        Map<CashType, Integer> resultNotes = new HashMap<>();
        for (CashType cashType : CashType.values()) {
            int val1 = bundle1.getNotes().getOrDefault(cashType, 0);
            int val2 = bundle2.getNotes().getOrDefault(cashType, 0);
            resultNotes.put(cashType, val1 + val2);
        }
        return new CashBundle(resultNotes);
    }

    @Override
    public CashBundle subtract(CashBundle from, CashBundle bundle) {
        Map<CashType, Integer> resultNotes = new HashMap<>();
        for (CashType cashType : CashType.values()) {
            int val1 = from.getNotes().getOrDefault(cashType, 0);
            int val2 = bundle.getNotes().getOrDefault(cashType, 0);
            int result = val1 - val2;
            if (result < 0) throw new IllegalArgumentException("Can't subtract greater from less.");
            resultNotes.put(cashType, result);
        }
        return new CashBundle(resultNotes);
    }
}
