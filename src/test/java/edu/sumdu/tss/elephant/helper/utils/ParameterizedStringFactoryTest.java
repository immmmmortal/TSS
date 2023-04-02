package edu.sumdu.tss.elephant.helper.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParameterizedStringFactoryTest {
    @Test
    public void testToString() {
        ParameterizedStringFactory factory = new ParameterizedStringFactory("I'm :age years old");
        ParameterizedStringFactory.ParameterizedString res = factory.addParameter("age", "20");

        assertEquals("I'm 20 years old", res.toString());
    }



}