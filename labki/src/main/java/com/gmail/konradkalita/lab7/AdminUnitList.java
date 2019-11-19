package com.gmail.konradkalita.lab7;

import com.gmail.konradkalita.lab6.CSVReader;
import com.gmail.konradkalita.lab6.ColumnNotFoundException;
import com.gmail.konradkalita.lab6.EmptyColumnValueException;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AdminUnitList {
    List<AdminUnit> units = new ArrayList<>();
    Map<Long, AdminUnit> IdToAdminUnit= new HashMap<>();
    Map<AdminUnit, Long> AdminUnitToId = new HashMap<>();

    private CSVReader reader;

    public void read(String filePath)
            throws IOException, EmptyColumnValueException, ColumnNotFoundException {
        reader = new CSVReader(filePath, ",", true);

        while(reader.next()) {
            AdminUnit unit = new AdminUnit();

            unit.name = reader.get("name");
            unit.adminLevel = reader.getInt("admin_level");
            unit.population = reader.getInt("population");
            unit.area = reader.getDouble("area");
            unit.density = reader.getDouble("density");

            BoundingBox bbox = new BoundingBox();
            bbox.xmin = reader.getDouble("x1");
            bbox.ymin = reader.getDouble("y1");
            bbox.xmax = reader.getDouble("x3");
            bbox.ymax = reader.getDouble("y3");
            unit.bbox = bbox;

            IdToAdminUnit.put(reader.getLong("id"), unit);
            AdminUnitToId.put(unit, reader.getLong("id"));
            units.add(unit);
        }
    }

    void list(PrintStream out) {
        units.forEach(out::println);
    }

    void list(PrintStream out,int offset, int limit ) {
        units.stream()
                .skip(offset)
                .limit(limit)
                .forEach(out::println);
    }

    AdminUnitList selectByName(String pattern, boolean regex) {
        AdminUnitList ret = new AdminUnitList();
        ret.units =
                units.stream()
                    .filter(unit -> regex ? unit.name.matches(pattern) :
                            unit.name.contains(pattern))
                    .collect(Collectors.toList());
        return ret;
    }
}
