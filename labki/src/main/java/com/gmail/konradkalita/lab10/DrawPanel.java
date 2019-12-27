package com.gmail.konradkalita.lab10;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel {

    List<XmasShape> shapes = new ArrayList<>();

    public DrawPanel(){
        setBackground(new Color(100, 0, 100));
        shapes.add(new TreeTrunk(450,550,2));
        shapes.add(new Tree(200,0,3,5));
        shapes.add(new Star(480,60,0.7,7,new Color(240, 200, 0)));

        shapes.add(new Star(350,350,0.3,6,Color.PINK,Color.MAGENTA));
        shapes.add(new Star(600,350,0.25,4,Color.PINK,Color.ORANGE, Math.PI / -2));
        shapes.add(new Star(450,480,0.3,6,Color.BLUE,Color.MAGENTA));
        shapes.add(new Star(500,180,0.25,7,Color.MAGENTA,Color.ORANGE));
        shapes.add(new Star(500,480,0.25,5,Color.PINK,Color.MAGENTA));
        shapes.add(new Star(600,300,0.3,9,Color.RED,Color.MAGENTA));
        shapes.add(new Star(440,300,0.3,8,Color.PINK,Color.MAGENTA));
        shapes.add(new Star(700,500,0.30,6,Color.RED));
        shapes.add(new Star(650,480,0.30,5,Color.MAGENTA,Color.ORANGE,Math.PI /2));

        shapes.add(new Bubble(400,200,0.3));
        shapes.add(new Bubble(550,480,0.4,new Color(235, 40, 20)));
        shapes.add(new Bubble(300,450,0.4,new Color(80, 40, 235)));
        shapes.add(new Bubble(400,350,0.4,new Color(240, 10, 130)));
        shapes.add(new Bubble(530,230,0.3,new Color(230, 235, 35)));
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        shapes.forEach(s -> s.draw((Graphics2D) g));
    }
}