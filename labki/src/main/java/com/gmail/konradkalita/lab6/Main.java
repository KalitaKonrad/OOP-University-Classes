package com.gmail.konradkalita.lab6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;

public class Main
{
    public static void main(String[] args)
    {
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
                System.out.println(reader.getCurrent()[i]);
            }
        }
    }
}
