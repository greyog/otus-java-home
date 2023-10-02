package org.example.bridge_pattern.service.impl;

import org.example.bridge_pattern.service.Color;
import org.example.bridge_pattern.service.Shape;
import org.example.bridge_pattern.util.AnsiColor;

public abstract class ColoredShape implements Shape {
    private final String shapeStr;
    private final Color color;

    protected ColoredShape(Color color, String shapeStr) {
        this.color = color;
        this.shapeStr = shapeStr;
    }

    @Override
    public void draw() {
        System.out.println(color.fillColor() + shapeStr + AnsiColor.ANSI_RESET);
    }
}
