package edu.sumdu.tss.elephant.helper.utils;

import edu.sumdu.tss.elephant.helper.Keys;
import io.javalin.http.Context;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;



public class ResponseUtilsTest {
    private final String OK_ANSWER = "Ok";
    private final String ERROR_ANSWER = "Error";
    private final String STATUS_KEY = "status";
    private final String MESSAGE_KEY = "message";
    private Object message(String state, String message) {
        HashMap<String, String> answer = new <String, String>HashMap<String, String>();
        answer.put(STATUS_KEY, state);
        answer.put(MESSAGE_KEY, message);
        return answer;
    }
    @Test
    public void TestSuccess() {
        Object expected = this.message(OK_ANSWER,"200");
        Object actual = ResponseUtils.success("200");
        assertEquals(expected,actual);
    }

    @Test
    public void TestError() {
        Object expected = this.message(ERROR_ANSWER,"404");
        Object actual = ResponseUtils.error("404");
        assertEquals(expected,actual);
    }

    @Test
    public void TestFlushFlash() {
        //void method
    }
}