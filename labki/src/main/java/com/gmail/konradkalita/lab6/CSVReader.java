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
    private int recordLength;

    public CSVReader(String filename) throws IOException
    {
        this(filename, ",", false);
    }

    public CSVReader(String filename, String delimiter) throws IOException
    {
        this(filename, delimiter, false);
    }

    public CSVReader(Reader reader, String delimiter, boolean hasHeader) {
        this.reader = new BufferedReader(reader);
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;

        if(hasHeader) {
            parseHeader();
        }
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
            line.ifPresent(s -> this.recordLength = s.length());
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

    public int getRecordLength() {
        return this.recordLength;
    }

    public boolean isMissing(int columnIndex) throws IllegalArgumentException {
        if (columnIndex >= this.columnLabels.size() || columnIndex < 0) {
            throw new IllegalArgumentException("Column index out of bounds");
        }
        return current[columnIndex].equals("");
    }

    public boolean isMissing(String columnLabel) {
        return columnLabelsToInt.containsKey(columnLabel);
    }

    String get(int columnIndex) {
        return isMissing(columnIndex) ? "" : current[columnIndex];
    }

    String get(String columnLabel) {
        return isMissing(columnLabel) ? "" : current[columnLabelsToInt.get(columnLabel)];
    }

    int getInt(int columnIndex) throws EmptyColumnValueException {
        if(isMissing(columnIndex)) {
            throw new EmptyColumnValueException(columnIndex);
        }
        else {
            return Integer.parseInt(current[columnIndex]);
        }
    }

    int getInt(String columnLabel) throws EmptyColumnValueException {
        if(isMissing(columnLabel)) {
            throw new EmptyColumnValueException(columnLabel);
        }
        else {
            return Integer.parseInt(current[columnLabelsToInt.get(columnLabel)]);
        }
    }

    Long getLong(int columnIndex) throws EmptyColumnValueException {
        if(isMissing(columnIndex)) {
            throw new EmptyColumnValueException(columnIndex);
        }
        else {
            return Long.parseLong(current[columnIndex]);
        }
    }

    Long getLong(String columnLabel) throws EmptyColumnValueException {
        if(isMissing(columnLabel)) {
            throw new EmptyColumnValueException(columnLabel);
        }
        else {
            return Long.parseLong(current[columnLabelsToInt.get(columnLabel)]);
        }
    }

    Double getDouble(int columnIndex) throws EmptyColumnValueException {
        if(isMissing(columnIndex)) {
            throw new EmptyColumnValueException(columnIndex);
        }
        else {
            return Double.parseDouble(current[columnIndex]);
        }
    }

    Double getDouble(String columnLabel) throws EmptyColumnValueException {
        if(isMissing(columnLabel)) {
            throw new EmptyColumnValueException(columnLabel);
        }
        else {
            return Double.parseDouble(current[columnLabelsToInt.get(columnLabel)]);
        }
    }
}
