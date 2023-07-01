package org.example.atm.service;

import org.example.atm.entity.CashBundle;

public interface AtmService {

    void acceptCashBundle(CashBundle cashBundle);

    int getTotalAmount();

    CashBundle getCash(int amount);

}
