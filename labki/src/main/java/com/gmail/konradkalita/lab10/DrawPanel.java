package com.gmail.konradkalita.lab10;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel {

    List<XmasShape> shapes = new ArrayList<>();

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        shapes.forEach(s -> s.draw((Graphics2D) g));
    }

    public void addComponent(XmasShape component) {
        shapes.add(component);
    }
}
