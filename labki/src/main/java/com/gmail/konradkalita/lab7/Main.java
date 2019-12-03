package com.gmail.konradkalita.lab7;

import com.gmail.konradkalita.lab6.ColumnNotFoundException;
import com.gmail.konradkalita.lab6.EmptyColumnValueException;

import java.io.IOException;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        AdminUnitList list = new AdminUnitList();
        String filePath = getClass().getResource("/admin-units.csv").getPath();

        try {
            list.read(filePath);

            list.filter(unit -> unit.name.startsWith("K"));

        } catch (IOException | EmptyColumnValueException | ColumnNotFoundException e) {
            e.printStackTrace();
        }
    }
}
