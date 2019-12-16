package com.gmail.konradkalita.lab10;

import java.awt.*;

public class Bubble implements XmasShape {
    int x;
    int y;
    double scale;
    Color lineColor = Color.BLUE;
    Color fillColor = Color.BLUE;

    public Bubble(int x, int y, double scale){
        this.x = x;
        this.y = y;
        this.scale = scale;
    }

    public Bubble(int x, int y, double scale, Color color){
        this(x,y,scale);
        this.lineColor = color;
        this.fillColor = color;
    }

    public Bubble(int x, int y, double scale, Color lineColor, Color fillColor){
        this(x,y,scale);
        this.lineColor = lineColor;
        this.fillColor = fillColor;
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(this.lineColor);
        g2d.fillOval(0,0,100,100);
        g2d.setColor(this.fillColor);
        g2d.drawOval(0,0,100,100);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }

}