package org.example.decorator_pattern.service.decorator;

import org.example.decorator_pattern.service.Order;

public class Sugared extends OrderDecorator {

    public Sugared(Order order) {
        super(order);
    }

    @Override
    public boolean hasSugar() {
        return true;
    }
}
