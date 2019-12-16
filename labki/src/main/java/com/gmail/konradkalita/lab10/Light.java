package com.gmail.konradkalita.lab10;

import java.awt.*;

public class Light implements XmasShape {

    int x;
    int y;
    double scale;

    int alpha = 60;
    Color backgroundColor = new Color(235, 240, 200, alpha);
    Color color = new Color(240, 230, 40);

    public Light(int x, int y, double scale){
        this.x = x;
        this.y = y;
        this.scale = scale;
    }

    public Light(int x, int y, double scale, Color color){
        this(x,y,scale);
        this.color = color;
    }

    @Override
    public void render(Graphics2D g2d){
        g2d.setColor(backgroundColor);
        g2d.fillOval(0,0,100,100);
        g2d.setColor(backgroundColor);
        g2d.drawOval(0,0,100,100);

        g2d.setColor(color);
        g2d.fillOval(20,20,60,60);
        g2d.setColor(color);
        g2d.drawOval(20,20,60,60);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}