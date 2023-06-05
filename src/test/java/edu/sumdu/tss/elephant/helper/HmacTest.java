package edu.sumdu.tss.elephant.helper;

import junit.framework.TestCase;
import org.junit.Test;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;

public class HmacTest extends TestCase {

    @Test
    public void testCalculate() throws NoSuchAlgorithmException, InvalidKeyException {
        String data = "test data ";
        String key = "test key";
        String actual = Hmac.calculate(data,key);
        String expected = "d64d20c82a800a767751bf529c11d7b494b0b269584f825db833a73cfa236e74c77a1067b9d5094e18921c428e44863b";
        assertEquals(expected,actual);
    }

}