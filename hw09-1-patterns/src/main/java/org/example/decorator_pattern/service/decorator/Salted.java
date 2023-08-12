package org.example.decorator_pattern.service.decorator;

import org.example.decorator_pattern.service.Order;

public class Salted extends OrderDecorator {

    public Salted(Order order) {
        super(order);
    }

    @Override
    public boolean isSalted() {
        return true;
    }
}
