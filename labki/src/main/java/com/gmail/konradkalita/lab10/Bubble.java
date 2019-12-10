package com.gmail.konradkalita.lab10;

import lombok.AllArgsConstructor;

import java.awt.*;

@AllArgsConstructor
public class Bubble implements XmasShape {
    int x;
    int y;
    double scale;
    Color lineColor;
    Color fillColor;

    @Override
    public void render(Graphics2D g2d) {
        // set fill color
        g2d.fillOval(0,0,100,100);
        // set line color
        g2d.drawOval(0,0,100,100);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x, y);
        g2d.scale(scale, scale);
    }
}