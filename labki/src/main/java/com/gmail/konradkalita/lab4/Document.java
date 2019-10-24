package com.gmail.konradkalita.lab4;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Document implements HTMLElement
{
    private String title;
    private Photo photo;
    List<Section> sections = new ArrayList<>();

    public Document(String title)
    {
        this.title = title;
    }

    public Document(String title, Photo photo)
    {
        this.title = title;
        this.photo = photo;
    }

    public Document(String title, Photo photo, ArrayList<Section> sections)
    {
        this.title = title;
        this.photo = photo;
        this.sections = sections;
    }

    public Document setTitle(String title)
    {
        this.title = title;
        return this;
    }

    public Document addPhoto(String photoUrl)
    {
        this.photo = new Photo(photoUrl);
        return this;
    }

    public Section addSection(String sectionTitle)
    {
        sections.add(new Section(sectionTitle));
        return sections.get(sections.size() - 1);
    }

    public Document addSection(Section section)
    {
        sections.add(section);
        return this;
    }

    @Override
    public void writeHTML(PrintStream out)
    {
        out.print("<!DOCTYPE html>");
        out.print("<html lang=\"en\">");
        out.print("<head><meta charset=\"UTF-8\">");
        out.printf("<title>%s</title></head>", title);
        out.print("<body>");
        out.printf("<h1>%s</h1>", title);
        photo.writeHTML(out);
        sections.forEach(section -> section.writeHTML(out));
        out.print("</body></html>");
    }
}
