package com.pd;

import java.awt.GraphicsEnvironment;

public class FontList {
	public static void main(String args[]) {
        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
        for(String font:e.getAvailableFontFamilyNames()) {
            System.out.println(font);
        }
    }
}
