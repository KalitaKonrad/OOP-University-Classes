package com.gmail.konradkalita.lab4;

import java.io.PrintStream;

public class ListItem implements HTMLElement
{
    private String text;

    public ListItem(String text)
    {
        this.text = text;
    }

    @Override
    public PrintStream wirteHTML(PrintStream out)
    {
        out.printf("<li>%s</li>", text);
        return out;
    }
}
