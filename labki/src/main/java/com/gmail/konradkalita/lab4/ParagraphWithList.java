package com.gmail.konradkalita.lab4;

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

    }
}
