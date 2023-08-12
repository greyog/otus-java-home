package org.example.decorator_pattern.service.decorator;

import org.example.decorator_pattern.entity.Size;
import org.example.decorator_pattern.service.Order;

public class Small extends OrderDecorator {

    public Small(Order order) {
        super(order);
    }

    @Override
    public Size getSize() {
        return Size.HALF;
    }

}
