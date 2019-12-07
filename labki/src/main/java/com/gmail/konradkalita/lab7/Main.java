package com.gmail.konradkalita.lab7;

import com.gmail.konradkalita.lab6.ColumnNotFoundException;
import com.gmail.konradkalita.lab6.EmptyColumnValueException;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        AdminUnitList list = new AdminUnitList();
        String filePath = getClass().getResource("/admin-units.csv").getPath();

        try {
            list.read(filePath);
        } catch (IOException | EmptyColumnValueException | ColumnNotFoundException e) {
            e.printStackTrace();
        }

        AdminUnit warszawaUnit = list.selectByName("^Warszawa$", true).units.get(0);

        /* linear and RTree search */

        System.out.println("Linear search:");
        long start = System.nanoTime();
        list.getNeighboursLinear(warszawaUnit, 10);
        long finish = System.nanoTime();
        System.out.printf("Linear search time: %s ms\n",
                ((double)finish - (double)start) / 1000000);
        System.out.println("RTree search:");
        start = System.nanoTime();
        list.getNeighbours(warszawaUnit, 10);
        finish = System.nanoTime();
        System.out.printf("RTree search time: %s ms\n",
                ((double)finish - (double)start) / 1000000);

        /* AdminUnitQuery */

        AdminUnitQuery query = new AdminUnitQuery()
                .selectFrom(list)
                .where(a -> a.area > 1000)
                .or( a -> a.name.startsWith("Sz"))
                .sort((a,b)->Double.compare(a.area,b.area))
                .limit(100);
        query.execute().list(System.out);

        /* filtering */
        list.filter(a -> a.name.startsWith("Ż")).sortInplaceByArea().list(System.out, 0, 5);

    }
}
