package com.gmail.konradkalita.lab7;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AdminUnit {
    String name;
    int adminLevel;
    double area;
    int population;
    double density;
    AdminUnit parent;
    BoundingBox bbox = new BoundingBox();
}
