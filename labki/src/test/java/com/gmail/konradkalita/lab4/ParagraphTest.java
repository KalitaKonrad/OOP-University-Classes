package com.gmail.konradkalita.lab4;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class ParagraphTest
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
    void Should_CheckIfParagraphHasHtmlMarkups()
    {
        String paragraphContent = "Java";

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