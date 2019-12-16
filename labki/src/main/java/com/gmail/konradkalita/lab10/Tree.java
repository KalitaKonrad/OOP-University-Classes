package com.gmail.konradkalita.lab10;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Tree implements XmasShape {

    int n; // number of tree branches
    int x; // x pos to translate
    int y; // y pos to translate
    double scale; // scale to translate

    final double h; // distance between each branches in Y axis
    final double s; // helping constant for each tree geometry
    final double k; // scale helping constant

    List<Branch> branches = new ArrayList<>(); // list of n branches

    public Tree(int x, int y, double scale,int n){
        this.n = n ;
        this.x = x;
        this.y = y;
        this.scale = scale;

        this.h = (double)100 / n;
        this.k = 0.7 /n;

        if(this.scale == 1){
            this.s = 2;
        } else {
            this.s = Math.pow(2, 1 - this.scale);
        }
    }

    @Override
    public void render(Graphics2D g2d){
        for(int i = n; i > 0; i--){
            this.branches.add(new Branch(this.x + (int) (200 * this.k/(this.s * this.scale) * (n-i)),
                    (int)(this.y + h * this.scale * i),
                    this.scale - k * this.scale * (n-i),
                    0.7 - 0.5 * i /n));
        }
    }

    @Override
    public void transform(Graphics2D g2d){
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }

    @Override
    public void draw(Graphics2D g2d){
        XmasShape.super.draw(g2d);
        branches.forEach(b -> b.draw(g2d));
    }
}