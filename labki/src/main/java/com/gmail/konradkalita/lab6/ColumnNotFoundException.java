package com.gmail.konradkalita.lab6;

public class ColumnNotFoundException extends Exception
{
    public ColumnNotFoundException(String column) {
        super(String.format("Column: %s does not exist", column));
    }
}
