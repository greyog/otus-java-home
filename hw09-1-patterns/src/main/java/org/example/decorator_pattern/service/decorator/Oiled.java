package org.example.decorator_pattern.service.decorator;

import org.example.decorator_pattern.service.Order;

public class Oiled extends OrderDecorator {

    public Oiled(Order order) {
        super(order);
    }

    @Override
    public boolean isOiled() {
        return true;
    }
}
