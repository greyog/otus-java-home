package org.example.bridge_pattern.service.impl.shape;

import org.example.bridge_pattern.service.Color;
import org.example.bridge_pattern.service.impl.ColoredShape;

public class Circle extends ColoredShape {
    private static final StringBuilder shapeBuilder = new StringBuilder();
    private static final int RADIUS = 5;
    private static final int SCALE = 3;

    static {
        for (int i = 0; i <= RADIUS * 2; i++) {
            for (int j = 0; j <= RADIUS * 3 * SCALE; j++) {
                int condition = pow2(i - RADIUS) + pow2(j / SCALE - RADIUS) - pow2(RADIUS);
                if (condition > -7 && condition < 3) {
                    shapeBuilder.append("O");
                } else {
                    shapeBuilder.append(" ");
                }
            }
            shapeBuilder.append('\n');
        }
    }

    public Circle(Color color) {
        super(color, shapeBuilder.toString());
    }

    private static int pow2(int x) {
        return x * x;
    }
}
