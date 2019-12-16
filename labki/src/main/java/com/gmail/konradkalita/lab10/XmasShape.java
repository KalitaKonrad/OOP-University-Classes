package com.gmail.konradkalita.lab10;


import java.awt.*;
import java.awt.geom.AffineTransform;

public interface XmasShape {

    void transform(Graphics2D g2d);

    void render(Graphics2D g2d);

    default void draw(Graphics2D g2d){
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        AffineTransform saveAT = g2d.getTransform();
        transform(g2d);
        render(g2d);
        g2d.setTransform(saveAT);
    }
}