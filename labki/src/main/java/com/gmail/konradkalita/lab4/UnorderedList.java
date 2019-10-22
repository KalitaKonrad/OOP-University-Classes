package com.gmail.konradkalita.lab4;

import java.io.PrintStream;
import java.util.ArrayList;

public class UnorderedList implements HTMLElement
{
    private ArrayList<ListItem> list;

    public UnorderedList(ArrayList<ListItem> list)
    {
        this.list = list;
    }

    @Override
    public void writeHTML(PrintStream out)
    {
        list.forEach(item -> item.writeHTML(out));
    }
}
