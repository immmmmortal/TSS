package edu.sumdu.tss.elephant.helper;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class KeysTest {

    @Test
    public void loadParams() {
    }
    @Before
    public void beforeMethod() {
        Keys.loadParams(new File("src\\pgdb.conf"));
    }
    @Test
    public void get() {
        String key = "DB.NAME";
        String expected = "elephant_db";
        String actual = Keys.get(key);
        assertEquals(expected,actual);

    }

    @Test
    public void TestIsProduction() {
        boolean expected = true;
        boolean actual = Keys.isProduction();
        assertEquals(expected,actual);
    }
}