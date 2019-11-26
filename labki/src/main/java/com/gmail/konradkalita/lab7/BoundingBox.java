package com.gmail.konradkalita.lab7;

import com.gmail.konradkalita.lab2_and_3.Matrix;
import jdk.internal.util.xml.impl.Pair;
import lombok.ToString;

@ToString
public class BoundingBox {
    double xMin;
    double yMin;
    double xMax;
    double yMax;

    void addPoint(double x, double y) {
        if(isEmpty()) {
            xMin = xMax = x;
            yMin = yMax = y;
            return;
        }
        else if(contains(x,y)) {
            return;
        }
        xMin = Math.min(xMin, x);
        xMax = Math.max(xMax, x);
        yMin = Math.min(yMin, y);
        yMax = Math.max(yMax, y);
    }

    boolean contains(double x, double y){
        return !isEmpty() && x >= xMin && x <= xMax && y >= yMin && y <= yMax;
    }

    boolean contains(BoundingBox box) {
        return box.isEmpty() || contains(box.xMin, box.yMin) && contains(box.xMax, box.yMax);
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
        return (xMax + xMin) / 2;
    }

    double getCenterY() throws GetCenterFromEmpyBoxException {
        if(isEmpty()) {
            throw new GetCenterFromEmpyBoxException();
        }
        return (yMax + yMin) / 2;
    }

    BoundingBox add(BoundingBox bb) {
        if(contains(bb)) {
            return this;
        } else if(bb.contains(this)) {
            return bb;
        }

        BoundingBox container = new BoundingBox();
        container.xMin = Math.min(xMin, bb.xMin);
        container.xMin = Math.min(xMax, bb.xMax);
        container.xMin = Math.max(yMin, bb.yMin);
        container.xMin = Math.max(yMax, bb.yMax);
        return container;
    }


}
