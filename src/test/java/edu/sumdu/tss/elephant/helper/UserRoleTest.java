package edu.sumdu.tss.elephant.helper;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class UserRoleTest {
    @Test
    public void byValueTest() {
        assertEquals(UserRole.ANYONE, UserRole.byValue(0));
        assertEquals(UserRole.UNCHEKED, UserRole.byValue(1));
        assertEquals(UserRole.BASIC_USER, UserRole.byValue(2));
        assertEquals(UserRole.PROMOTED_USER, UserRole.byValue(3));
        assertEquals(UserRole.ADMIN, UserRole.byValue(4));
    }
    @Test
    public void testMaxConnections() {
        assertEquals(0, UserRole.ANYONE.maxConnections());
        assertEquals(0, UserRole.UNCHEKED.maxConnections());
        assertEquals(5, UserRole.BASIC_USER.maxConnections());
        assertEquals(5, UserRole.PROMOTED_USER.maxConnections());
        assertEquals(5, UserRole.ADMIN.maxConnections());
    }

    @Test
    public void testMaxDb() {
        assertEquals(0, UserRole.ANYONE.maxDB());
        assertEquals(0, UserRole.UNCHEKED.maxDB());
        assertEquals(2, UserRole.BASIC_USER.maxDB());
        assertEquals(3, UserRole.PROMOTED_USER.maxDB());
        assertEquals(100, UserRole.ADMIN.maxDB());
    }

    @Test
    public void testMaxStorage() {
        assertEquals(0, UserRole.ANYONE.maxStorage());
        assertEquals(0, UserRole.UNCHEKED.maxStorage());
        assertEquals(20 * FileUtils.ONE_MB, UserRole.BASIC_USER.maxStorage());
        assertEquals(50 * FileUtils.ONE_MB, UserRole.PROMOTED_USER.maxStorage());
        assertEquals(50 * FileUtils.ONE_MB, UserRole.ADMIN.maxStorage());
    }

    @Test
    public void testMaxBackupsPerDB() {
        assertEquals(0, UserRole.ANYONE.maxBackupsPerDB());
        assertEquals(0, UserRole.UNCHEKED.maxBackupsPerDB());
        assertEquals(1, UserRole.BASIC_USER.maxBackupsPerDB());
        assertEquals(5, UserRole.PROMOTED_USER.maxBackupsPerDB());
        assertEquals(10, UserRole.ADMIN.maxBackupsPerDB());
    }

    @Test
    public void testMaxScriptsPerDB() {
        assertEquals(0, UserRole.ANYONE.maxScriptsPerDB());
        assertEquals(0, UserRole.UNCHEKED.maxScriptsPerDB());
        assertEquals(2, UserRole.BASIC_USER.maxScriptsPerDB());
        assertEquals(5, UserRole.PROMOTED_USER.maxScriptsPerDB());
        assertEquals(10, UserRole.ADMIN.maxScriptsPerDB());
    }
}
