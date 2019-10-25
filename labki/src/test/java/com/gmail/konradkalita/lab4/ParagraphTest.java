package com.gmail.konradkalita.lab4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

class ParagraphTest
{

    @Test
    void Should_CheckIfParagraphHasHtmlMarkups()
    {
        String paragraphContent = "Java";
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);

        new Paragraph(paragraphContent).writeHTML(ps);
        String result = null;

        try {
            result = os.toString("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        assertTrue(result.contains("<p>"));
        assertTrue(result.contains("</p>"));
        assertTrue(result.contains(paragraphContent));
    }

    @Test
    void Should_ThrowIllegalArgumentException_When_PassInNull()
    {
        String paragraphContent = null;
        IllegalArgumentException exception =
                Assertions.assertThrows(IllegalArgumentException.class,
                        () -> new Paragraph(paragraphContent));
        assertEquals("Paragraph content is null", exception.getMessage());
    }
}