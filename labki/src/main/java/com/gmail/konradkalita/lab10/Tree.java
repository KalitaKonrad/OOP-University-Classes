package com.gmail.konradkalita.lab10;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Tree implements XmasShape {

    int numberOfBranches;
    int x;
    int y;
    double scale;
    double distanceBetweenBranchesY;
    double constant;
    double scaleConstant;
    List<Branch> branches = new ArrayList<>();

    public Tree(int x, int y, double scale,int numberOfBranches){
        this.numberOfBranches = numberOfBranches;
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.distanceBetweenBranchesY = (double)100 / numberOfBranches;
        this.scaleConstant = 0.7 / numberOfBranches;

        if(this.scale == 1){
            this.constant = 2;
        } else {
            this.constant = Math.pow(2, 1 - this.scale);
        }
    }

    @Override
    public void render(Graphics2D g2d){
        for(int i = numberOfBranches; i > 0; i--){
            this.branches.add(new Branch(this.x + (int) (200 * this.scaleConstant / (this.constant * this.scale) * (
                    numberOfBranches - i)),
                    (int)(this.y + distanceBetweenBranchesY * this.scale * i),
                    this.scale - scaleConstant * this.scale * (numberOfBranches - i),
                    0.7 - 0.5 * i / numberOfBranches));
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