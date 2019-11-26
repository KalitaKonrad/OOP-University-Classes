package com.gmail.konradkalita.lab7;

import com.gmail.konradkalita.lab2_and_3.Matrix;
import lombok.ToString;

@ToString
public class BoundingBox {
    double xmin;
    double ymin;
    double xmax;
    double ymax;
    boolean isEmpty = false;

    void addPoint(double x, double y) {

    }

    boolean contains(double x, double y){
        return x >= xmin && x <= xmax && y >= ymin && y <= ymax;
    }

    boolean contains(BoundingBox box) {
        return contains(box.xmin, box.ymin) && contains(box.xmax, box.ymax);
    }

    boolean intersects(BoundingBox box) {
        return contains(box.xmin, box.ymin)
                || contains(box.xmax, box.ymax)
                || contains(box.xmin, box.ymax)
                || contains(box.xmax, box.ymin);
    }


}
