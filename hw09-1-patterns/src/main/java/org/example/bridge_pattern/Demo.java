package org.example.bridge_pattern;

import org.example.bridge_pattern.service.impl.color.BlueColor;
import org.example.bridge_pattern.service.impl.color.GreenColor;
import org.example.bridge_pattern.service.impl.color.RedColor;
import org.example.bridge_pattern.service.impl.shape.Circle;
import org.example.bridge_pattern.service.impl.shape.Rectangle;
import org.example.bridge_pattern.service.impl.shape.Triangle;

public class Demo {
    public static void main(String[] args) {
        new Triangle(new RedColor()).draw();
        new Rectangle(new GreenColor()).draw();
        new Circle(new BlueColor()).draw();
    }
}
