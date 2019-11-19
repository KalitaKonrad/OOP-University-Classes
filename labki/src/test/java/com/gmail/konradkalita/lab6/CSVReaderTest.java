package com.gmail.konradkalita.lab6;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class CSVReaderTest
{
    private CSVReader with_header;

    @BeforeEach
    void setUp()
    {
        try
        {
            this.with_header = new CSVReader("./src/main/java/com/gmail/konradkalita/lab6/sample_csv_files/with-header.csv",
                    ";",true);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown()
    {
        try
        {
            with_header.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    void should_ParseHeader_When_GivenFileHasHeader()
    {
        // header file is already parsed and stored in 'current'
        assertArrayEquals(new String[]{"id", "imię", "nazwisko", "ulica", "nrdomu", "nrmieszkania"},
                with_header.getCurrent());

    }

    @Test
    void should_ThrowColumnNotFoundException_When_GivenColumnIndexIsTooLarge()
    {
        int columnIndex = 100; // There are only columns from zero to fifth (including)
        assertThatExceptionOfType(ColumnNotFoundException.class)
                .isThrownBy(() -> with_header.get(columnIndex))
                .withMessage(String.format("Column with number: %d does not exist", columnIndex));
    }

    @Test
    void should_ThrowColumnNotFoundException_When_GivenColumnIndexIsTooSmall()
    {
        int columnIndex = -1; // There are only columns from zero to fifth (including)
        assertThatExceptionOfType(ColumnNotFoundException.class)
                .isThrownBy(() -> with_header.get(columnIndex))
                .withMessage(String.format("Column with number: %d does not exist", columnIndex));
    }

    @Test
    void should_ThrowEmptyColumnValueException_When_ValueAtGivenColumnIndexIsEmpty()
    {
        with_header.setCurrent(new String[]{"", "imię", "nazwisko", "ulica", "nrdomu", "nrmieszkania"}); // zeroth column is set empty
        int columnIndex = 0; // index of empty column
        assertThatExceptionOfType(EmptyColumnValueException.class)
                .isThrownBy(() -> with_header.get(columnIndex))
                .withMessage(String.format("Value on column: %d does not exist", columnIndex));
    }

    @Test
    void should_ThrowColumnNotFoundException_When_GivenColumnNameIsNotPresent()
    {
        String columnName = "ColumnNonExisting";
        assertThatExceptionOfType(ColumnNotFoundException.class)
                .isThrownBy(() -> with_header.get(columnName))
                .withMessage(String.format("Column: %s does not exist", columnName));
    }

    @Test
    void should_ThrowEmptyColumnValueException_When_ValueAtGivenColumnNameIsEmpty()
    {
        with_header.next(); // read next line
        with_header.setCurrent(new String[]{"", "", "", "", "", ""});
        String columnName = "id"; // First column name (known from header)
        assertThatExceptionOfType(EmptyColumnValueException.class)
                .isThrownBy(() -> with_header.get(columnName))
                .withMessage(String.format("Value on column with name: %s does not exist", columnName));
    }

    @Test
    void should_ConvertValueFromStringToInt_When_ValueAtGivenColumnIndexIsConvertibleToInt()
    {
        with_header.next(); // read next line
        try
        {
            assertEquals(1, with_header.getInt(0));
        } catch (EmptyColumnValueException | ColumnNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    void should_ConvertValueFromStringToInt_When_ValueAtGivenColumnNameIsConvertibleToInt()
    {
        with_header.next(); // read next line
        try
        {
            assertEquals(1, with_header.getInt("id"));
        } catch (EmptyColumnValueException | ColumnNotFoundException e)
        {
            e.printStackTrace();
        }
    }

}