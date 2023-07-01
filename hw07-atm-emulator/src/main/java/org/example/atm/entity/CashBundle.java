package org.example.atm.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter
public class CashBundle {

    private final Map<CashType, Integer> notes;

}
