package org.example.atm.service.impl;

import org.example.atm.entity.CashBundle;
import org.example.atm.entity.NoteType;
import org.example.atm.service.CashBundleService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CashBundleServiceImpl implements CashBundleService {

    @Override
    public CashBundle add(CashBundle bundle1, CashBundle bundle2) {
        Map<NoteType, Integer> resultNotes = new HashMap<>();
        for (NoteType noteType : NoteType.values()) {
            int val1 = bundle1.getNotes().getOrDefault(noteType, 0);
            int val2 = bundle2.getNotes().getOrDefault(noteType, 0);
            resultNotes.put(noteType, val1 + val2);
        }
        return new CashBundle(resultNotes);
    }

    @Override
    public CashBundle subtract(CashBundle from, CashBundle bundle) {
        Map<NoteType, Integer> resultNotes = new HashMap<>();
        for (NoteType noteType : NoteType.values()) {
            int val1 = from.getNotes().getOrDefault(noteType, 0);
            int val2 = bundle.getNotes().getOrDefault(noteType, 0);
            int result = val1 - val2;
            if (result < 0) throw new IllegalArgumentException("Can't subtract greater from less.");
            resultNotes.put(noteType, result);
        }
        return new CashBundle(resultNotes);
    }

    @Override
    public BigDecimal getTotalAmount(CashBundle bundle) {
        BigDecimal result = BigDecimal.ZERO;
        for (Map.Entry<NoteType, Integer> entry : bundle.getNotes().entrySet()) {
            NoteType noteType = entry.getKey();
            Integer noteCount = entry.getValue();
            result = result.add(noteType.getAmount().multiply(BigDecimal.valueOf(noteCount)));
        }
        return result;
    }
}
