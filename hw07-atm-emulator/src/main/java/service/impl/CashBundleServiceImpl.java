package service.impl;

import entity.CashBundle;
import service.CashBundleService;

public class CashBundleServiceImpl implements CashBundleService {
    @Override
    public CashBundle add(CashBundle bundle1, CashBundle bundle2) {
        int notes100 = bundle1.getNotes100() + bundle2.getNotes100();
        int notes1000 = bundle1.getNotes1000() + bundle2.getNotes1000();
        int notes500 = bundle1.getNotes500() + bundle2.getNotes500();
        int notes5000 = bundle1.getNotes5000() + bundle2.getNotes5000();

        return new CashBundle(notes100, notes500, notes1000, notes5000);
    }

    @Override
    public CashBundle subtract(CashBundle from, CashBundle bundle) {
        int notes100 = from.getNotes100() - bundle.getNotes100();
        int notes1000 = from.getNotes1000() - bundle.getNotes1000();
        int notes500 = from.getNotes500() - bundle.getNotes500();
        int notes5000 = from.getNotes5000() - bundle.getNotes5000();

        if (notes100 < 0 || notes500 < 0 || notes1000 < 0 || notes5000 < 0)
            throw new IllegalArgumentException("Can't subtract greater from less.");

        return new CashBundle(notes100, notes500, notes1000, notes5000);
    }
}
