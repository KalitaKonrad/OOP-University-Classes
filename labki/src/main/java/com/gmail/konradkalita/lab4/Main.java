package com.gmail.konradkalita.lab4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class Main
{
    public static void main(String[] args)
    {
        Document cv = new Document("Jan Kowalski - CV");
        cv.addPhoto("https://d-art.ppstatic.pl/kadry/k/r/1/12/44/5cebbfcd64a02_o_large.jpg");
        cv.addSection("Wykształcenie")
                .addParagraph("2000-2005 Przedszkole im. Królewny Snieżki w Krakowie")
                .addParagraph("2006-2012 SP7 im Ronalda Regana w Krakowie")
                .addParagraph("2013-2016 V Liceum Ogólnokształcące im Augusta Witkowskiego w Krakowie");
        cv.addSection("Umiejętności")
                .addParagraph(
                        new ParagraphWithList().setContent("Umiejetności")
                                .addItemToList("C")
                                .addItemToList("C++")
                                .addItemToList("Java")
                );
        // outputting to file
        try
        {
            cv.writeHTML(new PrintStream(new File("./cv.html")));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
