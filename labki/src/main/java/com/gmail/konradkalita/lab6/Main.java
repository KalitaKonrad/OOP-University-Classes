package com.gmail.konradkalita.lab6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.Locale;

public class Main
{
    public static void main(String[] args)
    {
        /*
        CSVReader reader;
        try
        {
            reader = new CSVReader("./src/main/java/com/gmail/konradkalita/lab6/sample_csv_files/with-header.csv",
                    ";",true);
        } catch (IOException e)
        {
            e.printStackTrace();
            return;
        }
        while(reader.next()) {
            for (int i = 0; i < reader.getCurrent().length; i++) {
                System.out.print(reader.getCurrent()[i] + ",");
            }
            System.out.println();
        }
        */
        testReadingFromString();
    }

    public static void testReadingFromString() {
        String text = "a,b,c\n123.4,567.9,91011.12";
        CSVReader reader = new CSVReader(new StringReader(text),",", false);
        while(reader.next()) {
            for(String item : reader.getCurrent()) {
                System.out.println(item);
            }
            try
            {
                reader.get(6);
            } catch (ColumnNotFoundException | EmptyColumnValueException e)
            {
                e.printStackTrace();
            }
        }

    }
}
