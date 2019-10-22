package com.gmail.konradkalita.lab4;

import java.util.ArrayList;
import java.util.List;

public class Document
{
    private String title;
    private Photo photo;
    List<Section> sections = new ArrayList<>();

    Document(String title, Photo photo)
    {
        this.title = title;
        this.photo = photo;
    }

    Document setTitle(String title)
    {
        this.title = title;
        return this;
    }

    Document setPhoto(String photoUrl)
    {
        this.photo = new Photo(photoUrl);
        return this;
    }

    Section addSection(String sectionTitle)
    {
        sections.add(new Section(sectionTitle));

    }

    public void writeHTML()
    {

    }
}
