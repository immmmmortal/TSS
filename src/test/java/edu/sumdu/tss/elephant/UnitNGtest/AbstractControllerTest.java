package edu.sumdu.tss.elephant.UnitNGtest;


import io.javalin.Javalin;
import io.javalin.http.Context;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class AbstractControllerTest {
    private Javalin app;
    private Context context;

    @BeforeEach
    void setUp() {
        app = mock(Javalin.class);
        context = mock(Context.class);
    }

    @Test
    void testCurrentUser() {
        User expectedUser = new User("test_user", "test_password");
        Mockito.when(context.sessionAttribute(Keys.SESSION_CURRENT_USER_KEY)).thenReturn(expectedUser);

        User actualUser = AbstractController.currentUser(context);

        assertEquals(expectedUser, actualUser);
    }

    @Test
    void testCurrentDB() {
        Database expectedDB = new Database("test_database");
        Mockito.when(context.sessionAttribute(Keys.DB_KEY)).thenReturn(expectedDB);

        Database actualDB = AbstractController.currentDB(context);

        assertEquals(expectedDB, actualDB);
    }

    @Test
    void testCurrentMessages() {
        MessageBundle expectedMessageBundle = new MessageBundle("en_US");
        expectedMessageBundle.addMessage("test_message_key", "test_message_value");
        Mockito.when(context.sessionAttribute(Keys.MODEL_KEY)).thenReturn(Map.of("msg", expectedMessageBundle));

        MessageBundle actualMessageBundle = AbstractController.currentMessages(context);

        assertEquals(expectedMessageBundle, actualMessageBundle);
    }

    @Test
    void testCurrentModel() {
        Map<String, Object> expectedModel = Map.of("key1", "value1", "key2", "value2");
        Mockito.when(context.sessionAttribute(Keys.MODEL_KEY)).thenReturn(expectedModel);

        Map<String, Object> actualModel = AbstractController.currentModel(context);

        assertEquals(expectedModel, actualModel);
    }

    //TODO: Add more tests for register() method
}
