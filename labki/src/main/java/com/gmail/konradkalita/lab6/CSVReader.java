package com.gmail.konradkalita.lab6;

import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.*;
import java.util.stream.IntStream;

@Getter
@Setter
public class CSVReader
{
    private BufferedReader reader;
    private String delimiter;
    private boolean hasHeader;
    private List<String> columnLabels;
    private Map<String,Integer> columnLabelsToInt = new HashMap<>();
    private String[] current;

    /**
     *
     * @param filename - nazwa pliku
     * @param delimiter - separator pól
     * @param hasHeader - czy plik ma wiersz nagłówkowy
     */

    public CSVReader(String filename) throws FileNotFoundException
    {
        this(filename, ",", false);
    }

    public CSVReader(String filename, String delimeter) throws FileNotFoundException
    {
        this(filename, delimeter, false);
    }

    public CSVReader(String filename, String delimiter, boolean hasHeader) throws FileNotFoundException
    {
        this.reader = new BufferedReader(new FileReader(filename));
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if(hasHeader) {
            try
            {
                parseHeader();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void parseHeader() throws IOException
    {
        if(!next()) {
            return;
        }

        List<String> header = Arrays.asList(current);
        IntStream.range(0, header.size())
                .forEach(index -> columnLabelsToInt.put(header.get(index), index));
    }

    public boolean next() throws IOException
    {
        String line = reader.readLine();
        if (line == null) {
            return false;
        }

        this.current = line.split(delimiter);
        return true;
    }

    public List<String> getColumnLabels() {
        return this.columnLabels;
    }


}
