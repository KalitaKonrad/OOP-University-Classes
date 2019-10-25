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

    public ParagraphWithList()
    {
        this.htmlList = new UnorderedList();
    }

    @Override
    public ParagraphWithList setContent(String content)
    {
        this.content = content;
        return this;
    }

    public ParagraphWithList addItemToList(ListItem item)
    {
        htmlList.addItem(item);
        return this;
    }

    public ParagraphWithList addItemToList(String content)
    {
        htmlList.addItem(new ListItem(content));
        return this;
    }

    @Override
    public void writeHTML(PrintStream out)
    {
        out.print("<p>");
        htmlList.writeHTML(out);
        out.print("</p>");
    }
}
