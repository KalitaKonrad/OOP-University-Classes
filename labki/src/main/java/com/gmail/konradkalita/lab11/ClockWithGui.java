package com.gmail.konradkalita.lab11;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.time.LocalTime;

public class ClockWithGui extends JPanel {

    LocalTime time = LocalTime.now();
    private static final int CLOCK_SIZE = 270;
    private ClockThread thread;

    public ClockWithGui() {
        super();
        this.thread = new ClockThread();
        thread.start();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Clock");
        frame.setContentPane(new ClockWithGui());
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(getWidth() / 2, getHeight() / 2);

        Point2D src = new Point2D.Float(0, -100);

        AffineTransform saveAT = g2d.getTransform();
        g2d.drawOval(-CLOCK_SIZE / 2, -CLOCK_SIZE / 2, CLOCK_SIZE, CLOCK_SIZE);

        // hour
        double minute = time.getHour() * 2 * Math.PI / 12 / 60;
        g2d.rotate(time.getHour() % 12 * 2 * Math.PI / 12 + minute);
        g2d.drawLine(0, 0, 0, -50);
        g2d.setTransform(saveAT);

        // minute
        g2d.rotate(time.getMinute() * 2 * Math.PI / 60);
        g2d.drawLine(0, 0, 0, -70);
        g2d.setTransform(saveAT);

        // second
        g2d.rotate(time.getSecond() * 2 * Math.PI / 60);
        g2d.drawLine(0, 0, 0, -90);
        g2d.setTransform(saveAT);

        // hour indicating numbers
        for (int i = 1; i < 13; i++) {
            AffineTransform at = new AffineTransform();
            at.rotate(2 * Math.PI / 12 * i);

            Point2D trg = new Point2D.Float();
            at.transform(src, trg);

            String text = Integer.toString(i);
            int x = (int) (trg.getX() - g2d.getFontMetrics().stringWidth(text) / 2);
            int y = (int) (trg.getY() - g2d.getFontMetrics().getHeight() / 2);

            g2d.drawString(text, x, y);
        }

        // minute/hour lines (including these above hour indicating numbers)
        for (int i = 1; i < 61; i++) {
            boolean hour = i % 5 == 0;
            g2d.rotate(2 * Math.PI / 60 * i);
            g2d.drawLine(0, -CLOCK_SIZE / 2, 0, -CLOCK_SIZE / 2 + (hour ? 10 : 5));
            g2d.setTransform(saveAT);
        }
    }
    class ClockThread extends Thread {
        @Override
        public void run() {
            while(true){
                time = LocalTime.now();
                System.out.printf("%02d:%02d:%02d\n",time.getHour(),time.getMinute(),time.getSecond());

                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repaint();
            }
        }
    }
}