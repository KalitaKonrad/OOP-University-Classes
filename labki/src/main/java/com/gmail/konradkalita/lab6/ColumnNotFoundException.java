package com.gmail.konradkalita.lab6;

public class ColumnNotFoundException extends Exception
{
    public ColumnNotFoundException(int columnIndex) {
        super(String.format("Column with number: %d does not exist", columnIndex));
    }

    public ColumnNotFoundException(String column) {
        super(String.format("Column: %s does not exist", column));
    }
}
