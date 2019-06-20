package com.codelab.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import spark.Request;
import spark.Response;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MovieControllerTest {

    @Mock
    Request request;

    @Mock
    Response response;

    @Test
    public void testShouldReturnSuccess() {
        MovieController movieController = new MovieController();

        HashMap<String, String> actualResult = movieController.publishMovie(request, response);

        assertEquals("publish successfully",actualResult.get("message"));
        verify(response).type("application/json");
        verify(response).status(200);
    }

    @Test
    public void testShouldReturnErrorIfMessageIsEmpty() {
        request.headers().add("MOVIE_NAME");

        MovieController movieController = new MovieController();

        HashMap<String, String> actualResult = movieController.publishMovie(request, response);

        assertEquals("publish successfully",actualResult.get("message"));
        verify(response).type("application/json");
        verify(response).status(200);
    }
}