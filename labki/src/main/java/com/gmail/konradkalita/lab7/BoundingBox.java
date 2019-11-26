package com.gmail.konradkalita.lab7;

import com.gmail.konradkalita.lab2_and_3.Matrix;
import lombok.ToString;

@ToString
public class BoundingBox {
    double xMin;
    double yMin;
    double xMax;
    double yMax;

    void addPoint(double x, double y) {

    }

    boolean contains(double x, double y){
        return x >= xMin && x <= xMax && y >= yMin && y <= yMax;
    }

    boolean contains(BoundingBox box) {
        return contains(box.xMin, box.yMin) && contains(box.xMax, box.yMax);
    }

    boolean intersects(BoundingBox box) {
        return (xMin <= box.xMax) && (xMax >= box.xMin) && (yMin <= box.yMax) && (yMax >= box.yMin);
    }

    double getWidth() {
        return Math.abs(xMax - xMin);
    }

    double getHeight() {
        return Math.abs(yMax - yMin);
    }
    boolean isEmpty() {
        return Double.isNaN(xMin) && Double.isNaN(xMax) && Double.isNaN(yMin) && Double.isNaN(yMax);
    }

    double getCenterX() throws GetCenterFromEmpyBoxException {
        if(isEmpty()) {
            throw new GetCenterFromEmpyBoxException();
        }
        return Math.abs((xMax - xMin) / 2);
    }
}
