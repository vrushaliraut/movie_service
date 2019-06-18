package com.codelab.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import spark.Request;
import spark.Response;

import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PingControllerTest {

    @Mock
    Request request;
    
    @Mock
    Response response;
    
    @Test
    public void testShouldReturnResponse() {
        PingController pingController = new PingController();

        Map<String, String> actualResult = pingController.ping(request, response);

        assertEquals("pong",actualResult.get("message"));
        verify(response).status(200);
        verify(response).type("application/json");
    }
}