package com.gmail.konradkalita.lab7;

import com.gmail.konradkalita.lab6.CSVReader;
import com.gmail.konradkalita.lab6.ColumnNotFoundException;
import com.gmail.konradkalita.lab6.EmptyColumnValueException;

import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AdminUnitList {
    List<AdminUnit> units = new ArrayList<>();
    AdminUnit root = new AdminUnit();
    Map<Long, AdminUnit> idToAdminUnit = new HashMap<>();
    Map<AdminUnit, Long> adminUnitToParentId = new HashMap<>();
    Map<Long, List<AdminUnit>> parentIdToChildren = new HashMap<>();

    private CSVReader reader;

    public void read(String filePath)
            throws IOException, EmptyColumnValueException, ColumnNotFoundException {
        reader = new CSVReader(filePath, ",", true);

        readAllAdminUnits();
        setChildrenHierarchy();
        fixMissingValues();
    }

    void list(PrintStream out) {
        units.forEach(out::println);
    }

    void list(PrintStream out, int offset, int limit) {
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

    private void readAllAdminUnits() throws ColumnNotFoundException {
        idToAdminUnit.put(-1L, root); // adding root
        while (reader.next()) { // reading all units from file and setting their hierarchy
            AdminUnit unit = readUnit();

            units.add(unit);
            idToAdminUnit.put(reader.getLong("id"), unit);
            long parentId = reader.getLong("parent", -1);
            adminUnitToParentId.put(unit, parentId);

            if (parentId != -1) {
                if (parentIdToChildren.containsKey(parentId)) {
                    parentIdToChildren.get(parentId).add(unit);
                } else {
                    List<AdminUnit> children = new ArrayList<>();
                    children.add(unit);
                    parentIdToChildren.put(parentId, children);
                }
            }
        }
    }

    private void setChildrenHierarchy() {
        units.forEach(
                unit -> {
                    long parentId = adminUnitToParentId.get(unit);
                    unit.parent = idToAdminUnit.getOrDefault(parentId, null);

                    if (unit.parent != null) {
                        unit.parent.children = parentIdToChildren
                                .getOrDefault(parentId, new ArrayList<>());
                    }
                });
    }

    private AdminUnit readUnit() throws ColumnNotFoundException {
        AdminUnit unit = new AdminUnit();

        unit.name = reader.get("name");
        unit.adminLevel = reader.getInt("admin_level", 0);
        unit.population = reader.getInt("population", -1);
        unit.area = reader.getDouble("area", 0);
        unit.density = reader.getDouble("density", -1);

        BoundingBox bbox = new BoundingBox();
        bbox.xMin = reader.getDouble("x1", Double.NaN);
        bbox.yMin = reader.getDouble("y1", Double.NaN);
        bbox.xMax = reader.getDouble("x3", Double.NaN);
        bbox.yMax = reader.getDouble("y3", Double.NaN);
        unit.bbox = bbox;

        return unit;
    }

    /*
    AdminUnitList getNeighbours(AdminUnit givenUnit, double maxDistance) {
        int adminLevel = givenUnit.adminLevel;
        boolean isCity = adminLevel == 8; // condition for unit being city

        return isCity ?
                getNeighboursForCity(givenUnit, maxDistance) :
                getNeighboursForOtherThanCityUnit(givenUnit);
    }

    private AdminUnitList getNeighboursForCity(AdminUnit givenUnit, double maxDistance) {
        AdminUnitList result = new AdminUnitList();
        int adminLevel = givenUnit.adminLevel;
        String name = givenUnit.name;

        result.units = units.stream()
                .filter(unit -> (unit.adminLevel == adminLevel && !unit.name.equals(name)))
                .filter(unit -> {
                    try {
                        return (unit.bbox.distanceTo(givenUnit.bbox) <= maxDistance);
                    } catch (GetCenterFromEmpyBoxException e) {
                        return false; // if they cannot be compared then simply reject unit
                    }
                })
                .collect(Collectors.toList());

        return result;
    }

    private AdminUnitList getNeighboursForOtherThanCityUnit(AdminUnit givenUnit) {
        AdminUnitList result = new AdminUnitList();
        int adminLevel = givenUnit.adminLevel;
        String name = givenUnit.name;

        result.units = units.stream()
                .filter(unit -> (unit.adminLevel == adminLevel && !unit.name.equals(name)))
                .filter(unit -> unit.bbox.intersects(givenUnit.bbox))
                .collect(Collectors.toList());

        return result;
    }
    */

    /* RTree implementation of gettingNeighbours*/
    AdminUnitList getNeighbours(AdminUnit unit, double maxDistance) {
        AdminUnit current;
        List<AdminUnit> potentialNeighbours = new ArrayList<>();

        potentialNeighbours.add(root);

        while (true) {
            current = potentialNeighbours.remove(0);
            if (current.adminLevel < unit.adminLevel) {
                potentialNeighbours.addAll(current.children);
            }

            if (potentialNeighbours.isEmpty()) {
                break;
            }

            if (potentialNeighbours.get(0).adminLevel >= unit.adminLevel) {
                break;
            }
        }

        return buildSubUnitList(filterNeighbours(potentialNeighbours, unit, maxDistance));
    }

    AdminUnitList getNeighboursLinear(AdminUnit needle, double maxDistance) {
        return buildSubUnitList(filterNeighbours(units, needle, maxDistance));
    }

    private List<AdminUnit> filterNeighbours(List<AdminUnit> list, AdminUnit unitForNeighbours, double maxDist) {
        return list.stream()
                .filter(
                        unit -> {
                            try {
                                return !unit.equals(unitForNeighbours)
                                        && ((unit.adminLevel >= 8 && unit.bbox.distanceTo(unitForNeighbours.bbox) < maxDist)
                                        || (unit.adminLevel < 8 && unit.bbox.intersects(unitForNeighbours.bbox)));
                            } catch (GetCenterFromEmpyBoxException e) {
                                e.printStackTrace();
                            }
                            return false; // if exception is thrown, return false
                        })
                .collect(Collectors.toList());
    }

    /* function for copying units */
    AdminUnitList buildSubUnitList(List<AdminUnit> list) {
        AdminUnitList unitList = new AdminUnitList();

        unitList.units = list;
        unitList.idToAdminUnit =
                idToAdminUnit.entrySet().stream()
                        .filter(x -> list.contains(x.getValue()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        unitList.adminUnitToParentId =
                adminUnitToParentId.entrySet().stream()
                        .filter(x -> list.contains(x.getKey()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        unitList.adminUnitToParentId.put(root, -1L);
        unitList.idToAdminUnit.put(-1L, root);
        unitList.setChildrenHierarchy();

        return unitList;
    }

    /* lab 9  Sorting and filtering */

    private AdminUnitList copy() {
        return buildSubUnitList(units);
    }

    AdminUnitList sortInplaceByName() {
        units.sort(new AdminUnitListComparator());
        return this;
    }

    AdminUnitList sortInplaceByArea() {
        units.sort(new Comparator<AdminUnit>() {
            @Override
            public int compare(AdminUnit o1, AdminUnit o2) {
                return Double.compare(o1.area, o2.area);
            }
        });
        return this;
    }

    AdminUnitList sortInplaceByPopulation() {
        units.sort(Comparator.comparingDouble(unit -> unit.population));
        return this;
    }

    AdminUnitList sortInplace(Comparator<AdminUnit> cmp){
        units.sort(cmp);
        return this;
    }

    AdminUnitList sort(Comparator<AdminUnit> cmp){
        AdminUnitList copyList = buildSubUnitList(units);
        copyList.sortInplace(cmp);
        return copyList;
    }

    AdminUnitList filter(Predicate<AdminUnit> pred) {
        List<AdminUnit> copyList = units.stream()
                .filter(pred)
                .collect(Collectors.toList());
        return buildSubUnitList(copyList);
    }

    AdminUnitList filter(Predicate<AdminUnit> pred, int limit){
        List<AdminUnit> copyList = units.stream()
                .filter(pred)
                .limit(limit)
                .collect(Collectors.toList());
        return buildSubUnitList(copyList);
    }

    AdminUnitList filter(Predicate<AdminUnit> pred, int offset, int limit){
        List<AdminUnit> copyList = units.stream()
                .filter(pred)
                .skip(offset)
                .limit(limit)
                .collect(Collectors.toList());
        return buildSubUnitList(copyList);
    }

}