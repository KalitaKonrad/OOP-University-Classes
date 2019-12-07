package com.gmail.konradkalita.lab7;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AdminUnitQuery {
    AdminUnitList src;
    Predicate<AdminUnit> pred = a -> true;
    Comparator<AdminUnit> cmp;
    int limit = Integer.MAX_VALUE;
    int offset = 0;


    AdminUnitQuery selectFrom(AdminUnitList src){
        this.src = src;
        return this;
    }

    AdminUnitQuery where(Predicate<AdminUnit> pred){
        this.pred = pred;
        return this;
    }

    AdminUnitQuery and(Predicate<AdminUnit> pred){
        this.pred = this.pred.and(pred);
        return this;
    }

    AdminUnitQuery or(Predicate<AdminUnit> pred){
        this.pred = this.pred.or(pred);
        return this;
    }

    AdminUnitQuery sort(Comparator<AdminUnit> cmp){
        this.cmp = cmp;
        return this;
    }

    AdminUnitQuery limit(int limit){
        this.limit = limit;
        return this;
    }

    AdminUnitQuery offset(int offset){
        this.offset = offset;
        return this;
    }

    AdminUnitList execute(){
        List<AdminUnit> queryResult = src.units.stream()
                .skip(offset)
                .limit(limit)
                .filter(pred)
                .sorted(cmp)
                .collect(Collectors.toList());
        return src.buildSubUnitList(queryResult);
    }
}