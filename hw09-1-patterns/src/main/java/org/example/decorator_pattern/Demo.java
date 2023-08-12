package org.example.decorator_pattern;

import org.example.decorator_pattern.service.decorator.*;
import org.example.decorator_pattern.service.impl.Donut;
import org.example.decorator_pattern.service.impl.Potato;
import org.example.decorator_pattern.service.impl.Salad;

public class Demo {
    public static void main(String[] args) {
        var regularDonut = new Regular(new Donut());
        var smallSaltedSalad = new Small(new Salted(new Salad()));
        var oiledDonutWithSugar = new Sugared(new Oiled(new Donut()));
        var allInOnePotato = new Salted(new Small(new Sugared(new Oiled(new Potato()))));

        System.out.println(new Potato().describe());
        System.out.println(regularDonut.describe());
        System.out.println(smallSaltedSalad.describe());
        System.out.println(oiledDonutWithSugar.describe());
        System.out.println(allInOnePotato.describe());
    }
}
