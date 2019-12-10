package com.gmail.konradkalita.lab10;

import java.awt.*;

public class Branch implements XmasShape {

    int[] xCords;
    int[] yCords;
    int lengthOfCords;

    public Branch(int[] xCords, int[] yCords) {
        this.xCords = xCords;
        this.yCords = yCords;
        this.lengthOfCords = xCords.length;
    }

    @Override
    public void transform(Graphics2D g2d) {

    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.fillPolygon(xCords, yCords , lengthOfCords);
    }
}
