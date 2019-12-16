package com.gmail.konradkalita.lab10;

import java.awt.*;
/*
    This class can be used as Light on the XMas Tree as well.
 */
public class Star implements XmasShape {

    int[] pointsX; // points with vertices of the polygon
    int[] pointsY;

    int x; // x pos to translate
    int y; // y pos to translate
    double scale; // scale to translate

    int n; // number of vertices
    final double Radius = 100; // Radius of the star
    final double radius = 35; // help constant
    double angle; // angle between each vert in radians
    double rotation = 0; // rotation angle in radians

    Color gradColor1 = Color.orange;
    Color gradColor2 = Color.orange;

    public Star(int x, int y, double scale, int n){
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.n = n;

        this.pointsX = new int[2 * n];
        this.pointsY = new int[2 * n];

        this.angle = 2 * Math.PI / this.n;
    }

    public Star(int x, int y, double scale, int n, Color color){
        this(x,y,scale,n);
        this.gradColor1 = color;
        this.gradColor2 = color;
    }

    public Star(int x, int y, double scale, int n, Color gradColor1, Color gradColor2){
        this(x,y,scale,n);
        this.gradColor1 = gradColor1;
        this.gradColor2 = gradColor2;
    }

    public Star(int x, int y, double scale, int n, Color gradColor1, Color gradColor2,double rotation){
        this(x,y,scale,n,gradColor1,gradColor2);
        this.rotation = rotation;
    }

    @Override
    public void render(Graphics2D g2d) {
        for(int i=0; i< n; i++){
            this.pointsX[2*i] = (int)(this.Radius * Math.cos(this.angle * i - Math.PI /2 + this.rotation));
            this.pointsX[2*i + 1] = (int)(this.radius * Math.cos(this.angle * i + this.angle/2 - Math.PI /2 + this.rotation));
            this.pointsY[2*i] = (int)(this.Radius * Math.sin(this.angle * i - Math.PI /2 + this.rotation));
            this.pointsY[2*i+1] = (int)(this.radius * Math.sin(this.angle * i + this.angle/2 - Math.PI /2 + this.rotation));
        }
        GradientPaint grad = new GradientPaint(0,0,gradColor1,0,100,gradColor2);

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
