package com.gmail.konradkalita.lab6;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.*;
import java.util.Optional;

@Getter
@Setter
public class CSVReader
{
    private BufferedReader reader;
    private String delimiter;
    private boolean hasHeader;
    private List<String> columnLabels = new ArrayList<>();
    private Map<String,Integer> columnLabelsToInt = new HashMap<>();
    private String[] current;

    public CSVReader(String filename) throws IOException
    {
        this(filename, ",", false);
    }

    public CSVReader(String filename, String delimiter) throws IOException
    {
        this(filename, delimiter, false);
    }

    /**
     *
     * @param filename - nazwa pliku
     * @param delimiter - separator pól
     * @param hasHeader - czy plik ma wiersz nagłówkowy
     */

    public CSVReader(String filename, String delimiter, boolean hasHeader) throws IOException
    {
        this.reader = new BufferedReader(new FileReader(filename));
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
        if(hasHeader) {
            parseHeader();
        }
    }

    private void parseHeader()
    {
        if(!next()) {
            return;
        }

        for (int i = 0; i < current.length; i++) {
            columnLabelsToInt.put(current[i], i);
            columnLabels.add(current[i]);  // ?
        }
    }

    public boolean next()
    {
        try {
            Optional<String> line = Optional.ofNullable(reader.readLine());
            line.ifPresent(s -> this.current = s.split(delimiter));
            return line.isPresent();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    public List<String> getColumnLabels() {
        return this.columnLabels;
    }


}
