package com.gmail.konradkalita.lab4;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

public class PhotoTest
{
    @Test
    void Should_CreateValidPhoto_When_ValidImageUrlIsProvided()
    {
        String imageUrl = "jan-kowalski.png"; // valid image URL
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);

        new Photo(imageUrl).writeHTML(ps);
        String result = null;

        try {
            // decoding with ISO charset
            result = os.toString("ISO-8859-2");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        assertTrue(result.contains("<img"));
        assertTrue(result.contains("/>"));
        assertTrue(result.contains("src="));
        assertTrue(result.contains(imageUrl));
    }

    @Test
}
