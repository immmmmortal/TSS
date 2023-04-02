package edu.sumdu.tss.elephant.helper.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorHelperTest {

    @Test
    public void isValidPassword() {
        String testPassword = "Password@12";
        boolean expected = true;
        boolean actual = ValidatorHelper.isValidPassword(testPassword);
        assertEquals(expected,actual);
    }

    @Test
    public void TestIsValidMail() {
        String mailTest = "example@example.com";
        boolean expected = true;
        boolean actual = ValidatorHelper.isValidMail(mailTest);
        assertEquals(expected,actual);
    }
}