package com.gmail.konradkalita.lab4;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Section implements HTMLElement
{
    private String title;

    private List<Paragraph> paragraphList = new ArrayList<>();

    public Section(String title)
    {
        setTitle(title);
    }

    public Section(ArrayList<Paragraph> paragraphList)
    {
        this.paragraphList = paragraphList;
    }

    public Section(String title, ArrayList<Paragraph> paragraphList)
    {
        this.title = title;
        this.paragraphList = paragraphList;
    }

    public Section setTitle(String title) throws IllegalArgumentException
    {
        if (title == null)
        {
            throw new IllegalArgumentException("Title is null");
        }
        else
        {
            this.title = title;
            return this;
        }
    }

    public Section addParagraph(String paragraphText)
    {
        paragraphList.add(new Paragraph(paragraphText));
        return this;
    }

    public Section addParagraph(Paragraph p)
    {
        paragraphList.add(p);
        return this;
    }

    @Override
    public void writeHTML(PrintStream out)
    {
        out.printf("<div><h1>%s</h1>", title);
        paragraphList.forEach(paragraph -> paragraph.writeHTML(out));
        out.print("</div>");
    }
}
