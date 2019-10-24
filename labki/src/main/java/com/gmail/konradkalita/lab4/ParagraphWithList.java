package com.gmail.konradkalita.lab4;

import java.io.PrintStream;

public class ParagraphWithList extends Paragraph
{
    private UnorderedList htmlList;

    public ParagraphWithList(String content)
    {
        super(content);
        this.htmlList = new UnorderedList();
    }

    public void addItemToList(ListItem item)
    {
        htmlList.addItem(item);
    }

    public void addItemToList(String content)
    {
        htmlList.addItem(new ListItem(content));
    }

    @Override
    public void writeHTML(PrintStream out)
    {
        super.writeHTML(out);
    }
}
