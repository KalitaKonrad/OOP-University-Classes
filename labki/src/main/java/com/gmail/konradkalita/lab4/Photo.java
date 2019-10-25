package com.gmail.konradkalita.lab4;

import java.io.PrintStream;

public class Photo implements HTMLElement
{
    private String url;

    public Photo(String url)
    {
        setUrl(url);
    }

    public void setUrl(String url) throws IllegalArgumentException
    {
        if (url == null)
        {
            throw new IllegalArgumentException("URL is null");
        }
        else
        {
            this.url = url;
        }
    }
    @Override
    public void writeHTML(PrintStream out) throws IllegalArgumentException
    {
        out.printf("<img src=\"%s\" alt=\"CV photo\" height=\"420\" width=\"640\"/>\n", url);
    }
}
