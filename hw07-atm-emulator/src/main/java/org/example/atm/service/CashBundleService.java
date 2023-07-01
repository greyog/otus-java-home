package org.example.atm.service;

import org.example.atm.entity.CashBundle;

import java.math.BigDecimal;

public interface CashBundleService {

    CashBundle add(CashBundle bundle1, CashBundle bundle2);

    CashBundle subtract(CashBundle from, CashBundle bundle);

    BigDecimal getTotalAmount(CashBundle bundle);
}
