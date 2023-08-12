package org.example.bridge_pattern.service.impl.color;

import org.example.bridge_pattern.service.Color;
import org.example.bridge_pattern.util.AnsiColor;

public class GreenColor implements Color {
    @Override
    public String fillColor() {
        return AnsiColor.ANSI_GREEN;
    }
}
