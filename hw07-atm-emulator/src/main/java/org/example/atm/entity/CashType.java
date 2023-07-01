package org.example.atm.entity;

import java.math.BigDecimal;

public enum CashType {
    NOTE_100, NOTE_500, NOTE_1000, NOTE_5000;

    public BigDecimal getAmount() {
        String strAmount = this.name().substring(5);
        return BigDecimal.valueOf(Long.parseLong(strAmount));
    }
}
