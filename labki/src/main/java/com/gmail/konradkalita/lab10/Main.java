package com.gmail.konradkalita.lab10;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        int WIDTH = 1000, HEIGHT = 700;
        JFrame frame = new JFrame("Choinka");
        DrawPanel drawPanel = new DrawPanel();

        frame.setContentPane(drawPanel);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);
    }
}
