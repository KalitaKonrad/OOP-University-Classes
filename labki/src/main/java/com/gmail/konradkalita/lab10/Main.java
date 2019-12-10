package com.gmail.konradkalita.lab10;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        DrawPanel drawPanel = new DrawPanel();
        drawPanel.addComponent(new Branch(new int[] {500, 600, 700}, new int[] {500, 400, 500}));
        drawPanel

        JFrame frame = new JFrame("Choinka");
        frame.setContentPane(drawPanel);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);

    }
}
