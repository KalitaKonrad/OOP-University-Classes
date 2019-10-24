package com.gmail.konradkalita.lab4;

import java.io.PrintStream;
import java.util.ArrayList;

public class UnorderedList implements HTMLElement
{
    private ArrayList<ListItem> list;

    public UnorderedList()
    {
        this.list = new ArrayList<>();
    }

    public UnorderedList(ArrayList<ListItem> list)
    {
        this.list = list;
    }

    public void addItem(ListItem item)
    {
        list.add(item);
    }

    public void addItem(String content)
    {
        list.add(new ListItem(content));
    }

    @Override
    public void writeHTML(PrintStream out)
    {
        out.print("<ul>");
        list.forEach(item -> item.writeHTML(out));
        out.print("</ul>");
    }
}
