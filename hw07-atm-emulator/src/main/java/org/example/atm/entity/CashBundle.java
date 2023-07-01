package org.example.atm.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CashBundle {

    private final int notes100;
    private final int notes500;
    private final int notes1000;
    private final int notes5000;

}
