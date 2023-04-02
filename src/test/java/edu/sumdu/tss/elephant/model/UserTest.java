package edu.sumdu.tss.elephant.model;

import edu.sumdu.tss.elephant.helper.UserRole;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    @Test
    public void testPassword() {
        User user = new User();

        user.setLogin("test_user");
        user.password("test_password");

        assertNotSame("test_password", user.getPassword());
        assertEquals(user.getPassword(), user.crypt("test_password"));
    }

    @Test
    public void testRole() {
        User user = new User();

        user.setRole(UserRole.ADMIN.getValue());

        assertEquals(UserRole.ADMIN, user.role());
    }

    @Test
    public void testResetToken() {
        User user = new User();

        user.setToken("abc123");

        user.resetToken();

        assertNotSame("abc123", user.getToken());
    }


    @Test
    public void testCrypt() {
        User user = new User();

        user.setLogin("test_user");

        assertEquals("731eaa84d5e842aa372e874dcae0ed9faacaa94" +
                "9d2d7aedd9f6410fe247f4287b02d7d7a855b1cbf8d902fe45daad40a", user.crypt("test_password"));
    }
}