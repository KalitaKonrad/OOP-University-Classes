package com.gmail.konradkalita.lab6;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

    private static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    private static final String DEFAULT_DATE_FORMAT = "dd.MM.yyyy";
    private static final String DEFAULT_DATE_AND_TIME_FORMAT =
            DEFAULT_TIME_FORMAT + " " + DEFAULT_DATE_FORMAT;
    private static final String SPLIT_REGEX = "%s(?=([^\"]*\"[^\"]*\")*[^\"]*$)";

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

    public void close() throws IOException
    {
        reader.close();
    }

    public List<String> getColumnLabels() {
        return this.columnLabels;
    }

    public int getRecordLength() {
        return this.recordLength;
    }

    public boolean isMissing(int columnIndex) throws ColumnNotFoundException, EmptyColumnValueException
    {
        if(columnIndex >= columnLabels.size() || columnIndex < 0) {
            throw new ColumnNotFoundException(columnIndex);
        }
        if(current[columnIndex].equals("")) {
            throw new EmptyColumnValueException(columnIndex);
        }
        return current[columnIndex].isEmpty();
    }

    public boolean isMissing(String columnLabel) throws ColumnNotFoundException, EmptyColumnValueException
    {
        int columnIndex = columnLabelsToInt.get(columnLabel);
        if(columnIndex >= columnLabels.size() || columnIndex < 0) {
            throw new ColumnNotFoundException(columnLabel);
        }
        if(current[columnIndex].equals("")) {
            throw new EmptyColumnValueException(columnLabel);
        }
        return current[columnIndex].isEmpty();
    }

    String get(int columnIndex) throws ColumnNotFoundException, EmptyColumnValueException {
        return isMissing(columnIndex) ? "" : current[columnIndex];
    }

    String get(String columnLabel) throws ColumnNotFoundException, EmptyColumnValueException {
        return isMissing(columnLabel) ? "" : current[columnLabelsToInt.get(columnLabel)];
    }

    int getInt(int columnIndex) throws EmptyColumnValueException, ColumnNotFoundException {
        return Integer.parseInt(current[columnIndex]);
    }

    int getInt(String columnLabel) throws EmptyColumnValueException, ColumnNotFoundException  {
        return Integer.parseInt(current[columnLabelsToInt.get(columnLabel)]);
    }

    Long getLong(int columnIndex) throws EmptyColumnValueException, ColumnNotFoundException  {
        return Long.parseLong(get(columnIndex));
    }

    Long getLong(String columnLabel) throws EmptyColumnValueException, ColumnNotFoundException  {
        return Long.parseLong(get(columnLabelsToInt.get(columnLabel)));
    }

    Double getDouble(int columnIndex) throws EmptyColumnValueException, ColumnNotFoundException  {
        return Double.parseDouble(get(columnIndex));
    }

    Double getDouble(String columnLabel) throws EmptyColumnValueException, ColumnNotFoundException {
        return Double.parseDouble(get(columnLabelsToInt.get(columnLabel)));
    }
/*
    LocalTime getTime(int columnIndex, String format) {
        return LocalTime.parse(get(columnIndex), DateTimeFormatter.ofPattern("HH:mm"));
    }*/
}
