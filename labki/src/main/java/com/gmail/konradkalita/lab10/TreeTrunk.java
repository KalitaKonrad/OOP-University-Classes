package com.gmail.konradkalita.lab10;

import java.awt.*;

public class TreeTrunk implements XmasShape {

    int[] pointsX = new int[]{0,50,50,0}; //  verticles points of the polygon
    int[] pointsY = new int[]{0,0,100,100};

    int x; // x pos to translate
    int y; // y pos to translate
    double scale; // scale to translate

    Color gradColor1 = new Color(61, 29, 7);
    Color gradColor2 = new Color(140, 57, 20);

    public TreeTrunk(int x, int y, double scale){
        this.x = x;
        this.y = y;
        this.scale = scale;
    }

    public TreeTrunk(int x, int y, double scale, Color color){
        this(x,y,scale);
        this.gradColor1 = color;
        this.gradColor2 = color;
    }

    public TreeTrunk(int x, int y, double scale, Color gradColor1, Color gradColor2){
        this(x,y,scale);
        this.gradColor1 = gradColor1;
        this.gradColor2 = gradColor2;
    }

    @Override
    public void render(Graphics2D g2d) {

        GradientPaint grad = new GradientPaint(0,0,gradColor1,0,100, gradColor2);
        g2d.setPaint(grad);
        g2d.fillPolygon(pointsX,pointsY,pointsX.length);
        g2d.drawPolygon(pointsX,pointsY,pointsX.length);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}
