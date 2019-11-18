package com.gmail.konradkalita.lab6;

public class EmptyColumnValueException extends Exception
{
    public EmptyColumnValueException(int columnIndex) {
        super(String.format("Value on column: %d does not exist", columnIndex));
    }

    public EmptyColumnValueException(String columnName) {
        super(String.format("Value on column with name: %s does not exist", columnName));
    }
}
