package org.example.decorator_pattern.service.decorator;

import org.example.decorator_pattern.entity.Size;
import org.example.decorator_pattern.service.Order;

public abstract class OrderDecorator implements Order {

    Order decoratedOrder;

    public OrderDecorator(Order order) {
        this.decoratedOrder = order;
    }

    @Override
    public String getName() {
        return decoratedOrder.getName();
    }

    @Override
    public boolean isOiled() {
        return decoratedOrder.isOiled();
    }

    @Override
    public Size getSize() {
        return decoratedOrder.getSize();
    }

    @Override
    public boolean isSalted() {
        return decoratedOrder.isSalted();
    }

    @Override
    public boolean hasSugar() {
        return decoratedOrder.hasSugar();
    }

    @Override
    public String describe() {
        return "I ordered %s. It is %s, %s, %s and it's %s size. Pretty good taste I hope."
                .formatted(getName(),
                        describeSalt(),
                        describeOil(),
                        describeSugar(),
                        describeSize());
    }

    private String describeSize() {
        return getSize().toString().toLowerCase();
    }

    private String describeSalt() {
        if (isSalted()) {
            return "salted";
        }
        return "not salted";
    }

    private String describeSugar() {
        if (hasSugar()) {
            return "has sugar";
        }
        return "has no sugar";
    }

    private String describeOil() {
        if (isOiled()) {
            return "oiled";
        }
        return "not oiled";
    }

}
