package com.gmail.konradkalita.lab14;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BouncingBallsPanel extends JPanel {
  List<Ball> balls = new ArrayList<>();
  AnimationThread animationEngine = new AnimationThread();
  boolean stopFlag = true;
  int width, height;

  static class Ball {
    int x = 10;
    int y = 10;
    double vx = 10;
    double vy = 10;
    Color color = Color.green;
    int size = 20;

    public Ball() {}

    public Ball(int x, int y, int size, Color color, double vx, double vy) {
      this.x = x;
      this.y = y;
      this.size = size;
      this.color = color;
      this.vx = vx;
      this.vy = vy;
    }

    public void move(long t) {
      double time = t / 100.0;
      x += vx * time;
      y += vy * time;
    }

    public void bounce(int w, int h) {
      int halfsize = size / 2;
      if (x < halfsize) {
        vx = -vx;
        x = halfsize;
      }
      if (x > w - halfsize) {
        vx = -vx;
        x = w - halfsize;
      }
      if (y < halfsize) {
        vy = -vy;
        y = halfsize;
      }
      if (y > h - halfsize) {
        vy = -vy;
        y = h - halfsize;
      }
    }

    public void impulse(Ball other) {
      if (this == other) return;
      double dx = x - other.x, dy = y - other.y;
      if (Math.pow(dx, 2) + Math.pow(dy, 2) > Math.pow(size, 2.1)) return;
      double dst = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2)),
          nx = dx / dst,
          ny = dy / dst,
          dvx = vx - other.vx,
          dvy = vy - other.vy,
          strength = dvx * nx + dvy * ny;
      double[] impulse = {nx * strength, ny * strength};
      vx -= impulse[0];
      other.vx += impulse[0];
      vy -= impulse[1];
      other.vy += impulse[1];
    }

    public void paint(Graphics2D g2d) {
      int halfsize = size / 2;
      g2d.setColor(color);
      g2d.fillOval(x - halfsize, y - halfsize, size, size);
    }
  }

  class AnimationThread extends Thread {
    static final long timeStep = 50;

    @Override
    public void run() {
      while (true) {
        if (stopFlag) break;
        try {
          balls.forEach(
              ball -> {
                ball.move(timeStep);
                ball.bounce(width, height);
                balls.forEach(
                    ball1 -> {
                      ball1.impulse(ball1);
                    });
              });
          repaint();
          sleep(timeStep);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  BouncingBallsPanel() {
    setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3.0f)));
    setBackground(new Color(255, 255, 255));
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    balls.forEach(ball -> ball.paint(g2d));
  }

  void startThread() {
    width = getWidth();
    height = getHeight();
    stopFlag = false;
    if (!animationEngine.isAlive()) {
      System.out.println("Start or resume animation thread");
      animationEngine = new AnimationThread();
      animationEngine.start();
    }
  }

  void stopThread() {
    System.out.println("Suspend animation thread");
    stopFlag = true;
  }

  void addBall() {
    System.out.println("Add a ball");
    Ball b = new Ball();
    balls.add(b);
  }

  void removeBall() {
    System.out.println("Remove a ball");
    if (balls.size() > 0) balls.remove(0);
  }
}
