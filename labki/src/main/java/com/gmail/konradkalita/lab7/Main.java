package com.gmail.konradkalita.lab7;

public class Main {
    public static void main(String[] args) {
        AdminUnit adminUnit = new AdminUnit();
        System.out.println(adminUnit.toString());
        AdminUnitList unit = new AdminUnitList();
        unit.read();
    }
}
