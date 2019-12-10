package com.gmail.konradkalita.lab10;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class Tree implements XmasShape {
    List<Branch> branches = new ArrayList<>();
    List<Bubble> bubbles = new ArrayList<>();

    int x1CordOfTheLowestBranch = 300;
    int x2CordOfTheLowestBranch = 600;
    int numberOfBranches = 5;

    public void draw(Graphics g) {
        branches.forEach(branch -> branch.draw((Graphics2D) g));
    }

    public void addBranch(Branch branch) {
        branches.add(branch);
    }

    @Override
    public void transform(Graphics2D g2d) {

    }

    @Override
    public void render(Graphics2D g2d) {
        branches.forEach(b -> b.render(g2d));
        bubbles.forEach(b -> b.render(g2d));
    }
}
