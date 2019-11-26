package com.gmail.konradkalita.lab6;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String DEFAULT_DATE_AND_TIME_FORMAT = DEFAULT_TIME_FORMAT + " " + DEFAULT_DATE_FORMAT;
    private static final String REGEX_FOR_SPLITTING = "%s(?=([^\"]*\"[^\"]*\")*[^\"]*$)"; //%s at beginning to insert provided delimiter there

    public CSVReader(String filePath) throws IOException
    {
        this(filePath, ",", false, DEFAULT_CHARSET);
    }

    public CSVReader(String filePath, String delimiter) throws IOException
    {
        this(filePath, delimiter, false, DEFAULT_CHARSET);
    }

    /**
     *
     * @param filePath - name of the file
     * @param delimiter - field separator
     * @param hasHeader - does the file have header
     */

    public CSVReader(String filePath, String delimiter, boolean hasHeader) throws IOException
    {
        this(filePath, delimiter, hasHeader, DEFAULT_CHARSET);
        this.reader = new BufferedReader(new FileReader(filePath));
        this.delimiter = createDelimiter(delimiter);
        this.hasHeader = hasHeader;
        if(hasHeader) {
            parseHeader();
        }
    }

    public CSVReader(String filePath, String delimiter, boolean hasHeader, String charset)
            throws FileNotFoundException {
        this(new BufferedReader(
                        new InputStreamReader(new FileInputStream(filePath), Charset.forName(charset))),
                delimiter,
                hasHeader);
    }

    public CSVReader(Reader reader, String delimiter, boolean hasHeader) {
        this.reader = new BufferedReader(reader);
        this.delimiter = createDelimiter(delimiter);
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

    private String createDelimiter(String delimiter) {
        return String.format(REGEX_FOR_SPLITTING, delimiter);
    }

    public List<String> getColumnLabels() {
        return this.columnLabels;
    }

    public int getRecordLength() {
        return this.recordLength;
    }

    private boolean isMissing(int columnIndex) {
        return columnIndex < 0 || columnIndex >= current.length || current[columnIndex].isEmpty();
    }

    private boolean isMissing(String columnLabel) throws ColumnNotFoundException, EmptyColumnValueException
    {
        return isMissing(columnLabelsToInt.get(columnLabel));
    }

    public String get(int columnIndex) throws ColumnNotFoundException, EmptyColumnValueException {
        return isMissing(columnIndex) ? "" : current[columnIndex];
    }

    public String get(String columnLabel) throws ColumnNotFoundException, EmptyColumnValueException {
        return isMissing(columnLabel) ? "" : current[columnLabelsToInt.get(columnLabel)];
    }

    public String get(int index, String defaultValue)
            throws ColumnNotFoundException, EmptyColumnValueException {
        return isMissing(index) ? defaultValue : get(index);
    }

    public int getInt(int columnIndex) throws EmptyColumnValueException, ColumnNotFoundException {
        return Integer.parseInt(get(columnIndex));
    }

    public int getInt(int index, int defaultValue)
            throws EmptyColumnValueException, ColumnNotFoundException {
        return isMissing(index) ? defaultValue : getInt(index);
    }

    public int getInt(String columnLabel) throws EmptyColumnValueException, ColumnNotFoundException  {
        return Integer.parseInt(get(columnLabelsToInt.get(columnLabel)));
    }

    public int getInt(String column, int defaultValue)
            throws ColumnNotFoundException, EmptyColumnValueException {
        return isMissing(column) ? defaultValue : getInt(column);
    }

    public Long getLong(int columnIndex) throws EmptyColumnValueException, ColumnNotFoundException  {
        return Long.parseLong(get(columnIndex));
    }

    public long getLong(int index, long defaultValue)
            throws EmptyColumnValueException,
            ColumnNotFoundException {
        return isMissing(index) ? defaultValue : getLong(index);
    }

    public Long getLong(String columnLabel) throws EmptyColumnValueException, ColumnNotFoundException  {
        return Long.parseLong(get(columnLabelsToInt.get(columnLabel)));
    }

    public long getLong(String column, long defaultValue)
            throws ColumnNotFoundException, EmptyColumnValueException {
        return isMissing(column) ? defaultValue : getLong(column);
    }

    public Double getDouble(int columnIndex) throws EmptyColumnValueException, ColumnNotFoundException  {
        return Double.parseDouble(get(columnIndex));
    }

    public double getDouble(int index, double defaultValue)
            throws EmptyColumnValueException, ColumnNotFoundException {
        return isMissing(index) ? defaultValue : getDouble(index);
    }

    public Double getDouble(String columnLabel) throws EmptyColumnValueException, ColumnNotFoundException {
        return Double.parseDouble(get(columnLabelsToInt.get(columnLabel)));
    }

    public double getDouble(String column, double defaultValue)
            throws ColumnNotFoundException, EmptyColumnValueException {
        return isMissing(column) ? defaultValue : getDouble(column);
    }

    public LocalTime getTime(int index) throws ColumnNotFoundException, EmptyColumnValueException {
        return getTime(index, DEFAULT_TIME_FORMAT);
    }

    public LocalTime getTime(int index, String format) throws EmptyColumnValueException, ColumnNotFoundException {
        return LocalTime.parse(get(index), DateTimeFormatter.ofPattern(format));
    }

    public LocalTime getTime(String column) throws ColumnNotFoundException, EmptyColumnValueException {
        return getTime(column, DEFAULT_TIME_FORMAT);
    }

    public LocalTime getTime(String column, String format) throws ColumnNotFoundException, EmptyColumnValueException {
        return LocalTime.parse(get(column), DateTimeFormatter.ofPattern(format));
    }

    public LocalDate getDate(int index) throws ColumnNotFoundException, EmptyColumnValueException {
        return getDate(index, DEFAULT_DATE_FORMAT);
    }

    public LocalDate getDate(int index, String format) throws EmptyColumnValueException, ColumnNotFoundException {
        return LocalDate.parse(get(index), DateTimeFormatter.ofPattern(format));
    }

    public LocalDate getDate(String column) throws ColumnNotFoundException, EmptyColumnValueException {
        return getDate(column, DEFAULT_DATE_FORMAT);
    }

    public LocalDate getDate(String column, String format) throws ColumnNotFoundException, EmptyColumnValueException {
        return LocalDate.parse(get(column), DateTimeFormatter.ofPattern(format));
    }

    public LocalDateTime getDateTime(int index) throws ColumnNotFoundException, EmptyColumnValueException {
        return getDateTime(index, DEFAULT_DATE_AND_TIME_FORMAT);
    }

    public LocalDateTime getDateTime(int index, String format) throws EmptyColumnValueException, ColumnNotFoundException {
        return LocalDateTime.parse(get(index), DateTimeFormatter.ofPattern(format));
    }

    public LocalDateTime getDateTime(String column) throws EmptyColumnValueException, ColumnNotFoundException {
        return getDateTime(column, DEFAULT_DATE_AND_TIME_FORMAT);
    }

    public LocalDateTime getDateTime(String column, String format) throws ColumnNotFoundException, EmptyColumnValueException {
        return LocalDateTime.parse(get(column), DateTimeFormatter.ofPattern(format));
    }
}
