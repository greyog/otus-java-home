package org.example.bridge_pattern.service.impl.shape;

import org.example.bridge_pattern.service.Color;
import org.example.bridge_pattern.service.impl.ColoredShape;

public class Rectangle extends ColoredShape {
    public Rectangle(Color color) {
        super(color, """
                =================
                |               |
                |               |
                |               |
                |               |
                |_______________|
                """);
    }
}
