package com.gmail.konradkalita.lab4;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Section implements HTMLElement
{
    private String sectionTitle;
    private List<Paragraph> paragraphList = new ArrayList<>()

    public Section(String sectionTitle)
    {
        this.sectionTitle = sectionTitle;
    }

    @Override
    public PrintWriter wirteHTML(PrintStream out)
    {

    }
}
