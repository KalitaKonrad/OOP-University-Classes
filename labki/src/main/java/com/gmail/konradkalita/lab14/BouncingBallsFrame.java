package com.gmail.konradkalita.lab14;

import javax.swing.*;
import java.awt.*;

public class BouncingBallsFrame extends JFrame {

  BouncingBallsPanel bbPanel = new BouncingBallsPanel();

  BouncingBallsFrame() {
    super("Bouncing balls");
    buildGui();
  }

  void buildGui() {
    JPanel root = new JPanel();
    root.setLayout(new BorderLayout());
    JPanel northPanel = new JPanel();

    JButton start = new JButton("Start");
    start.addActionListener(p -> bbPanel.startThread());
    northPanel.add(start);

    JButton stop = new JButton("Stop");
    stop.addActionListener(p -> bbPanel.stopThread());
    stop.setEnabled(false);
    northPanel.add(stop);

    start.addActionListener(
        p -> {
          start.setEnabled(false);
          stop.setEnabled(true);
        });
    stop.addActionListener(
        p -> {
          stop.setEnabled(false);
          start.setEnabled(true);
        });

    JButton plus = new JButton("Plus");
    plus.addActionListener(p -> bbPanel.addBall());
    northPanel.add(plus);

    JButton minus = new JButton("Minus");
    minus.addActionListener(p -> bbPanel.removeBall());
    northPanel.add(minus);
    root.add(northPanel, BorderLayout.NORTH);

    root.add(bbPanel, BorderLayout.CENTER);
    setContentPane(root);
  }

  public static void main(String[] args) {
    BouncingBallsFrame frame = new BouncingBallsFrame();

    frame.setSize(700, 700);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(true);
    frame.setVisible(true);
  }
}
