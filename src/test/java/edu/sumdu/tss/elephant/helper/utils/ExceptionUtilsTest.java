package edu.sumdu.tss.elephant.helper.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExceptionUtilsTest {

    @Test
    public void testIsSQLUniqueException() {
        Exception ex = new Exception("duplicate key value violates unique constraint");
        assertTrue(ExceptionUtils.isSQLUniqueException(ex));
    }




}