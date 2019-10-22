package com.gmail.konradkalita.lab4;

import java.io.PrintStream;

public class Paragraph implements HTMLElement
{
    private String content;

    public Paragraph(String content)
    {
        this.content = content;
    }

    public Paragraph setContent(String content)
    {
        this.content = content;
        return this;
    }

    @Override
    public void writeHTML(PrintStream out)
    {
        out.printf("<p>%s</p>", content);
    }
}
