package com.gmail.konradkalita.lab6;

public class EmptyColumnValueException extends Exception
{
    public EmptyColumnValueException(int column) {
        super(String.format("Value on column: %d does not exist", column));
    }

    public EmptyColumnValueException(String column) {
        super(String.format("Value on column with name: %s does not exist", column));
    }
}
