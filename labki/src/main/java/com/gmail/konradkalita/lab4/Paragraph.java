package com.gmail.konradkalita.lab4;

import java.io.PrintStream;

public class Paragraph implements HTMLElement
{
    private String content;

    public Paragraph setContent(String content)
    {
        this.content = content;
    }

    @Override
    public PrintStream wirteHTML(PrintStream out)
    {
        out.printf("<p>%s</p>", content);
    }
}
