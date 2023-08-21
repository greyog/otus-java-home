package org.example.decorator_pattern.service;

import org.example.decorator_pattern.entity.Size;

public interface Order {

    default String getName() {
        return getClass().getSimpleName();
    }

    default String describe() {
        return "I'm %s.".formatted(getName());
    }

    default boolean isOiled() {
        return false;
    }

    default Size getSize() {
        return Size.NORMAL;
    }

    default boolean isSalted() {
        return false;
    }

    default boolean hasSugar() {
        return false;
    }
}
