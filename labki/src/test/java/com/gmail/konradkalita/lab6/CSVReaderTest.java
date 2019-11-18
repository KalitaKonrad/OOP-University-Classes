package com.gmail.konradkalita.lab6;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

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
    void get()
    {
    }

    @Test
    void get1()
    {
        
    }

    @Test
    void getInt()
    {
    }


    @Test
    void getInt1()
    {
    }

    @Test
    void getLong()
    {
    }

    @Test
    void getLong1()
    {
    }

    @Test
    void getDouble()
    {
    }

    @Test
    void getDouble1()
    {
    }
}