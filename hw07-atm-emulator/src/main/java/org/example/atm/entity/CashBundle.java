package org.example.atm.entity;

public class CashBundle {
    private final int notes100;
    private final int notes500;
    private final int notes1000;
    private final int notes5000;

    public CashBundle(int notes100, int notes500, int notes1000, int notes5000) {
        this.notes100 = notes100;
        this.notes500 = notes500;
        this.notes1000 = notes1000;
        this.notes5000 = notes5000;
    }

    public int getNotes5000() {
        return notes5000;
    }

    public int getNotes1000() {
        return notes1000;
    }

    public int getNotes500() {
        return notes500;
    }

    public int getNotes100() {
        return notes100;
    }

}
