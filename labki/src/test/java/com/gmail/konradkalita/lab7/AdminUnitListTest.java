package com.gmail.konradkalita.lab7;

import com.gmail.konradkalita.lab6.CSVReader;
import com.gmail.konradkalita.lab6.ColumnNotFoundException;
import com.gmail.konradkalita.lab6.EmptyColumnValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AdminUnitListTest {

    private AdminUnitList list = new AdminUnitList();

    @BeforeEach
    void setUp() throws EmptyColumnValueException, ColumnNotFoundException, IOException {
        list = new AdminUnitList();
        String filePath = getClass().getResource("/admin-units.csv").getPath();

        list.read(filePath);

    }

    @Test
    void list() {
    }

    @Test
    void selectByName() {
        String expected = "województwo podkarpackie";
        assertEquals(expected, list.selectByName("województwo podkarpackie", true)
        .units.get(0).getName());
    }

    @Test
    void getNeighbours() {
    }
}