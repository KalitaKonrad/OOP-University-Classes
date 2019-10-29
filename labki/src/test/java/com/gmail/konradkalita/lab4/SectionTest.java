package com.gmail.konradkalita.lab4;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

class SectionTest
{
    private ByteArrayOutputStream os;
    private PrintStream ps;
    @BeforeEach
    void openStreams()
    {
        os = new ByteArrayOutputStream();
        ps = new PrintStream(os);
    }

    @AfterEach
    void closeStreams()
    {
        try
        {
            os.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        ps.close();
    }

    @Test
    void Should_CheckIfSectionHasHtmlMarkups()
    {
        String sectionTitle = "Umiejętności";

        new Section(sectionTitle).writeHTML(ps);
        String result = null;

        try {
            result = os.toString("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        assertTrue(result.contains("<div>"));
        assertTrue(result.contains("</div>"));
        assertTrue(result.contains("<h1>"));
        assertTrue(result.contains("</h1>"));
        assertTrue(result.contains(sectionTitle));
    }

    @Test
    void Should_ThrowIllegalArgumentException_When_PassInNull()
    {
        String sectionTitle = null;
        IllegalArgumentException exception =
                Assertions.assertThrows(IllegalArgumentException.class,
                        () -> new Section(sectionTitle));
        assertEquals("Title is null", exception.getMessage());
    }
}
