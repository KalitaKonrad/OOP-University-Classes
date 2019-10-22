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
    public void writeHTML(PrintStream out)
    {
        out.printf("<li>%s</li>", text);
    }
}
