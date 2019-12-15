package com.gmail.konradkalita.lab10;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel {

    int width;
    int height;
    List<XmasShape> shapes;

    public DrawPanel(int width, int height) {
        this.width = width;
        this.height = height;
        this.shapes = new ArrayList<>();
        shapes.add(new Tree(4, width, height));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        setBackgroundColor(g2d);
        shapes.forEach(shape -> shape.draw(g2d));
    }

    private void setBackgroundColor(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();
        Color color1 = Color.PINK;
        Color color2 = Color.lightGray;
        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }
}
