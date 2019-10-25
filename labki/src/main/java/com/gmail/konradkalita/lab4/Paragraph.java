package com.gmail.konradkalita.lab4;

import java.io.PrintStream;

public class Paragraph implements HTMLElement
{
    protected String content;

    public Paragraph(String content)
    {
        setContent(content);
    }

    public Paragraph()
    {
        this.content = "";
    }

    public Paragraph setContent(String content) throws IllegalArgumentException
    {
        if (content == null)
        {
            throw new IllegalArgumentException("Paragraph content is null");
        }
        else
        {
            this.content = content;
            return this;
        }
    }

    @Override
    public void writeHTML(PrintStream out)
    {
        out.printf("<p>%s</p>", content);
    }
}
