package com.github.greyog;


import com.google.common.base.Joiner;

public class HelloOtus {
    public static void main(String[] args) {
        Joiner joiner = Joiner.on(" ! ").skipNulls();
        String result = joiner.join("Let", "there", "be", null,  "jazz");
        System.out.println(result);
    }
}