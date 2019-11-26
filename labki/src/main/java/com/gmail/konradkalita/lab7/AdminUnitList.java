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
    Map<Long, AdminUnit> idToAdminUnit= new HashMap<>();
    Map<AdminUnit, Long> adminUnitToParentId = new HashMap<>();
    Map<Long, List<AdminUnit>> parentIdToChildren = new HashMap<>();

    private CSVReader reader;

    public void read(String filePath)
            throws IOException, EmptyColumnValueException, ColumnNotFoundException {
        reader = new CSVReader(filePath, ",", true);

        while(reader.next()) {
            AdminUnit unit = new AdminUnit();

            unit.name = reader.get("name");
            unit.adminLevel = reader.getInt("admin_level", 0);
            unit.population = reader.getInt("population", -1);
            unit.area = reader.getDouble("area", 0);
            unit.density = reader.getDouble("density", -1);

            BoundingBox bbox = new BoundingBox();
            bbox.xmin = reader.getDouble("x1", 0);
            bbox.ymin = reader.getDouble("y1", 0);
            bbox.xmax = reader.getDouble("x3", 0);
            bbox.ymax = reader.getDouble("y2", 0);
            unit.bbox = bbox;

            units.add(unit);
            idToAdminUnit.put(reader.getLong("id"), unit);
            long parentId = reader.getLong("parent", -1);
            adminUnitToParentId.put(unit, parentId);

            if (parentId != -1) {
                if (parentIdToChildren.containsKey(parentId)) {
                    parentIdToChildren.get(parentId).add(unit);
                }
                else {
                    List<AdminUnit> children = new ArrayList<>();
                    children.add(unit);
                    parentIdToChildren.put(parentId, children);
                }
            }
        }

        setChildrenHierarchy();
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

    public AdminUnit get(int index) {
        return units.get(index);
    }

    private void fixMissingValues() {
        units.forEach(AdminUnit::fixMissingValues);
    }

    private void setChildrenHierarchy() {
        units.forEach(
                unit -> {
                    long parentId = adminUnitToParentId.get(unit);
                    unit.parent = idToAdminUnit.getOrDefault(parentId, null);

                    if (unit.parent != null) {
                        unit.parent.children = parentIdToChildren.getOrDefault(parentId, new ArrayList<>());
                    }
                });
    }
}
