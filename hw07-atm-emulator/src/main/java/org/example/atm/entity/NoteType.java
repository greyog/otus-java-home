package org.example.atm.entity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;

public enum NoteType {
    NOTE_100, NOTE_500, NOTE_1000, NOTE_5000;

    public BigDecimal getAmount() {
        String strAmount = this.name().substring(5);
        return BigDecimal.valueOf(Long.parseLong(strAmount));
    }

    public static NoteType[] getDescendingValues() {
        NoteType[] result = new NoteType[values().length];
        System.arraycopy(values(), 0, result, 0, values().length);
        Arrays.sort(result, Comparator.comparing(NoteType::getAmount).reversed());
        return result;
    }
}
