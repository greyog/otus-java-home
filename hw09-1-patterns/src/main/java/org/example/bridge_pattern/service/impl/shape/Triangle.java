package org.example.bridge_pattern.service.impl.shape;

import org.example.bridge_pattern.service.Color;
import org.example.bridge_pattern.service.impl.ColoredShape;

public class Triangle extends ColoredShape {

    public Triangle(Color color) {
        super(color, """
                *
                * *
                *  *
                *   *
                *    *
                *     *
                ********""");
    }
}
