package com.gmail.konradkalita.lab6;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class CSVReaderTest {
    private CSVReader with_header;

    @BeforeEach
    void setUp() {
        try {
            this.with_header = new CSVReader(
                    "./src/main/java/com/gmail/konradkalita/lab6/sample_csv_files/with-header.csv",
                    ";", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
        try {
            with_header.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void should_ParseHeader_When_GivenFileHasHeader() {
        // header file is already parsed and stored in 'current'
        assertArrayEquals(
                new String[]{"id", "imię", "nazwisko", "ulica", "nrdomu",
                        "nrmieszkania"},
                with_header.getCurrent());

    }

    @Test
    void should_ReturnEmptyString_When_GivenColumnIndexIsTooLarge() {
        int columnIndex = 100;
        assertEquals("", with_header.get(columnIndex));
    }

    @Test
    void should_ReturnEmptyString_When_ValueAtGivenColumnIndexIsEmpty() {
        with_header.setCurrent(
                new String[]{"", "imię", "nazwisko", "ulica", "nrdomu",
                        "nrmieszkania"}); // zeroth column is set empty
        int columnIndex = 0; // index of empty column
        assertEquals("", with_header.get(columnIndex));
    }

    @Test
    void should_ReturnEmptyString_When_GivenColumnNameIsNotPresent() {
        String columnName = "ColumnNonExisting";
        assertEquals("", with_header.get(columnName));
    }

    @Test
    void should_ConvertValueFromStringToInt_When_ValueAtGivenColumnIndexIsConvertibleToInt()
            throws ColumnNotFoundException, EmptyColumnValueException {
        with_header.next(); // read next line
        assertEquals(1, with_header.getInt(0));
    }

    @Test
    void should_ConvertValueFromStringToInt_When_ValueAtGivenColumnNameIsConvertibleToInt()
            throws ColumnNotFoundException, EmptyColumnValueException {
        with_header.next(); // read next line
        assertEquals(1, with_header.getInt("id"));
    }

    @Test
    void should_ReturnCorrectValues_When_GivenFileContainingDelimiterInsideValuesInColumn()
            throws EmptyColumnValueException,
            ColumnNotFoundException, IOException {
        CSVReader reader = new CSVReader(
                "./src/main/java/com/gmail/konradkalita/lab6/sample_csv_files/titanic-part.csv",
                ",", true);
        reader.next();
        assertEquals("\"Braund, Mr. Owen Harris\"", reader.get("Name"));

        reader.close();
    }

    @Test
    void should_ReadWholeFileProperly_When_GivenFileIsComplete()
            throws EmptyColumnValueException, ColumnNotFoundException {
        LinkedList<String> names = new LinkedList<>();
        String[] expected = {"Jan", "Anna", "Zofia", "Marek", "Katarzyna", "Zuzanna",
                "Sebastian", "Julia", "Antoni", "Dominik"};

        while(with_header.next()) {
            names.add(with_header.get("imię"));
        }

        assertEquals(Arrays.asList(expected),names);
    }

    @Test
    void should_ReadProperlyFromStringReader_When_GivenStringReader() throws ColumnNotFoundException, EmptyColumnValueException, IOException {
        String text = "a,b,c\n123.4,567.8,91011.12";
        CSVReader reader = new CSVReader(new StringReader(text),",",true);
        reader.next();

        assertThat(reader.getColumnLabels()).containsExactly("a", "b", "c");
        assertThat(reader.getDouble(0)).isEqualTo(123.4);

        reader.close();
    }
}