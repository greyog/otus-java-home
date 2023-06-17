package service;

import entity.CashBundle;

public interface AtmService {
    void acceptCashBundle(CashBundle cashBundle);
    int getTotalAmount();
    CashBundle getCash(int amount);
}
