package com.gmail.konradkalita.lab10;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class Tree implements XmasShape {
    int xTranslation;
    int yTranslation;
    List<Branch> branches;

    public Tree(int height, int windowWidth, int windowHeight) {
        this.branches = new ArrayList<>();
        Color color = Color.green;

        for (int i = height - 1; i >= 0; i--) {
            branches.add(new Branch(color, i + 1, height));
        }

        this.xTranslation = (windowWidth - Branch.WIDTH * height) / 2;
        this.yTranslation = (windowHeight - Branch.HEIGHT * height) / 2;
    }

    @Override
    public void transform(Graphics2D g2d) {
        System.out.println(xTranslation);
        g2d.translate(xTranslation, yTranslation);
    }

    @Override
    public void render(Graphics2D g2d) {}

    @Override
    public void draw(Graphics2D g2d) {
        AffineTransform saveAT = g2d.getTransform();
        transform(g2d);
        branches.forEach(branch -> branch.draw(g2d));
        g2d.setTransform(saveAT);
    }
}
