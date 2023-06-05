package edu.sumdu.tss.elephant.helper.sql;

import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import static org.junit.Assert.*;

public class ScriptReaderTest {




    @Test
    public void TestReadStatement() {
        ScriptReader testObj = new ScriptReader(new StringReader(
                "SELECT * FROM user;" +
                   "SELECT * FROM admins;"
        ));
        String actual =  testObj.readStatement();
        String actual2 = testObj.readStatement();
        String actual3 = testObj.readStatement();
        String expected = "SELECT * FROM user";
        assertEquals(expected,actual);
        assertEquals(
                "SELECT * FROM admins",
                actual2
        );
        assertEquals(
                null,
                actual3
        );
    }

    @Test
    public void TestIsInsideRemark() {
        ScriptReader testObj = new ScriptReader(new StringReader(
                        "SELECT * FROM admins; " +
                           "//commented text"
        ));
        testObj.readStatement();
        testObj.readStatement();

        boolean expected = true;
        boolean actual = testObj.isInsideRemark();
        assertEquals(expected,actual);

    }

    @Test
    public void TestIsBlockRemark() {
        ScriptReader testObj = new ScriptReader(new StringReader(
                "SELECT * FROM admins; " +
                        "/* commented text */"
        ));
        testObj.readStatement();
        testObj.readStatement();

        boolean expected = true;
        boolean actual = testObj.isBlockRemark();
        assertEquals(expected,actual);


    }

    @Test
    public void setSkipRemarks() {
        // untested method because getter is epson 

    }
}