package com.gmail.konradkalita.lab10;

import java.awt.*;

public class Branch implements XmasShape {

    int[] pointsX = new int[]{0, 100, 200, 166, 133, 100, 66, 33};
    int[] pointsY = new int[]{70, 0, 70, 85, 70, 85, 70, 85};

    int x;
    int y;
    double scale;
    double gradient = 0.5;

    Color gradColor1 = new Color(0,240, 0);
    Color gradColor2 =  new Color(0,(int)(200 * this.gradient) ,0);

    public Branch(int x, int y, double scale){
        this.x = x;
        this.y = y;
        this.scale = scale;
    }

    public Branch(int x, int y, double scale, double gradient){
        this(x,y,scale);
        this.gradient = gradient;
        gradColor2 =  new Color(0,(int)(200 * this.gradient) ,0);
    }

    @Override
    public void render(Graphics2D g2d) {
        GradientPaint grad = new GradientPaint(0,0,gradColor1,0,100, gradColor2);
        g2d.setPaint(grad);
        g2d.fillPolygon(pointsX, pointsY, pointsX.length);
        g2d.drawPolygon(pointsX, pointsY, pointsX.length);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}