package edu.sumdu.tss.elephant.helper.utils;

import edu.sumdu.tss.elephant.helper.exception.BackupException;
import org.junit.Test;

import static org.junit.Assert.*;

public class CmdUtilTest {

    @Test
    public void testExec() {
        try {
            CmdUtil.exec("ipconfig");
        } catch (BackupException e) {
            fail("Should not throw an exception");
        }

    }
}