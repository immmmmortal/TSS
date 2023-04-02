package edu.sumdu.tss.elephant.helper.utils;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class StringUtilsTest {

    @Test
    public void TestRandomAlphaString() {
        String expected = "00000";
        String actual = StringUtils.randomAlphaString(5);
        assertEquals(
                expected.length(),
                actual.length()
        );
    }

    @Test
    public void TestUuid() {
        String expected = "a46f4593-7a8d-45d2-a745-16074d03e9ae";
        String actual = UUID.randomUUID().toString();
        assertEquals(
                expected.length(),
                actual.length()
        );
        assertNotSame(expected,actual);
    }

    @Test
    public void TestReplaceLast() {
        String expected = "Boris Britva";
        String actual = StringUtils.replaceLast("Boris secondName","secondName","Britva");
        assertEquals(expected,actual);
    }
}