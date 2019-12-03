package com.gmail.konradkalita.lab7;

import java.util.Comparator;

public class AdminUnitListComparator implements Comparator<AdminUnit> {
    @Override
    public int compare(AdminUnit a, AdminUnit b) {
        return a.name.compareTo(b.name);
    }
}
