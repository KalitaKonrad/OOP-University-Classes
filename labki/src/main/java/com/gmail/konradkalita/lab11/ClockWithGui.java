package com.gmail.konradkalita.lab11;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.time.LocalTime;

public class ClockWithGui extends JPanel {

    LocalTime time = LocalTime.now();

    public ClockWithGui() {
        this.new ClockThread().start();
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

    public void paintComponent(Graphics g){
        Graphics2D g2d=(Graphics2D)g;
        g2d.translate(getWidth()/2,getHeight()/2);

        for(int i = 1; i < 13; i++) {
            AffineTransform at = new AffineTransform();
            at.rotate(2 * Math.PI / 12 * i);
            Point2D src = new Point2D.Float(0, -120);
            Point2D trg = new Point2D.Float();
            at.transform(src, trg);

            String text = Integer.toString(i);
            int x = (int) (trg.getX() - g2d.getFontMetrics().stringWidth(text) / 2);
            int y = (int) trg.getY();

            g2d.drawString(text, x, y);

            AffineTransform saveAT = g2d.getTransform();
            g2d.rotate(time.getHour() % 12 * 2 * Math.PI / 12);
            g2d.drawLine(0, 0, 0, -100);
            g2d.setTransform(saveAT);

            AffineTransform saveAT2 = g2d.getTransform();
            g2d.rotate(time.getMinute() * 2 * Math.PI / 60);
            g2d.drawLine(0, 0, 0, -100);
            g2d.setTransform(saveAT2);

            AffineTransform saveAT3 = g2d.getTransform();
            System.out.println(time.getSecond());
            g2d.rotate(time.getSecond() * 2 * Math.PI / 60);
            g2d.drawLine(0, 0, 0, -100);
            g2d.setTransform(saveAT3);

            // lines at hours ??????
            AffineTransform saveAT4 = g2d.getTransform();
            g2d.rotate(time.getHour() % 12 * 2 * Math.PI/12);
            g2d.drawLine(100,100,0,-100);
            g2d.setTransform(saveAT);
        }

    }

    class ClockThread extends Thread {
        @Override
        public void run() {
            while(true){
                time = LocalTime.now();
                System.out.printf("%02d:%02d:%02d\n",time.getHour(),time.getMinute(),time.getSecond());

                //sleep(1000);
                repaint();
            }
        }
    }
}