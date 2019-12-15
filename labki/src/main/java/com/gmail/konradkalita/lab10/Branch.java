package com.gmail.konradkalita.lab10;

import java.awt.*;

public class Branch implements XmasShape {

    int maxWidth;
    int scale;
    Color color;

    static final int WIDTH = 200;
    static final int HEIGHT = 140;

    private static final int[] X_COORDS = new int[] {0, WIDTH / 2, WIDTH};
    private static final int[] Y_COORDS = new int[] {HEIGHT, 0, HEIGHT};

    public Branch(Color color, int scale, int maxScale) {
        this.color = color;
        this.scale = scale;
        this.maxWidth = WIDTH * maxScale;
    }

    @Override
    public void transform(Graphics2D g2d) {
        double currentWidth = WIDTH * scale;
        double currentHeight = HEIGHT * scale;

        double xTranslation = (maxWidth - currentWidth) / 2;
        double yTranslation = (currentHeight / 2) - (HEIGHT / 2D);

        g2d.translate(xTranslation, yTranslation);
        g2d.scale(scale, scale);
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillPolygon(X_COORDS, Y_COORDS, X_COORDS.length);
    }
}
