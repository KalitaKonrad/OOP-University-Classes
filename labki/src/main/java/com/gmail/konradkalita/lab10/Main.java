package com.gmail.konradkalita.lab10;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        int WIDTH = 1400, HEIGHT = 900;
        JFrame frame = new JFrame("Choinka");
        frame.setContentPane(new DrawPanel(WIDTH, HEIGHT));
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);

    }
}
