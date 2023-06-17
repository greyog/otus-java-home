package service;

import entity.CashBundle;

public interface CashBundleService {
    CashBundle add(CashBundle bundle1, CashBundle bundle2);
    CashBundle subtract(CashBundle from, CashBundle bundle);
}
