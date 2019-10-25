package com.gmail.konradkalita.lab4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

class SectionTest
{
    @Test
    void Should_CheckIfSectionHasHtmlMarkups()
    {
        String sectionTitle = "Umiejętności";
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);

        new Section(sectionTitle).writeHTML(ps);
        String result = null;

        try {
            result = os.toString("ISO-8859-2");
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
