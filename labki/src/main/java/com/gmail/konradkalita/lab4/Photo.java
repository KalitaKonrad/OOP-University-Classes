package com.gmail.konradkalita.lab4;

import java.io.PrintStream;

public class Photo implements HTMLElement
{
    private String url;

    public Photo(String url)
    {
        this.url = url;
    }

    @Override
    public void writeHTML(PrintStream out)
    {
        out.printf("<img src=\"%s\" alt=\"CV photo\" height=\"420\" width=\"640\"/>\n", url);
    }
}
