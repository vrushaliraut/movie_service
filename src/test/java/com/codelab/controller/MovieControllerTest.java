package com.codelab.controller;

import com.codelab.contract.request.response.MovieResponse;
import com.codelab.model.Error;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import spark.Request;
import spark.Response;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MovieControllerTest {

    @Mock
    private Request request;

    @Mock
    private Response response;

    private MovieController movieController;
    private Gson gson;

    @Before
    public void setUp() {
        gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        movieController = new MovieController(gson);
    }

    @Test
    public void testShouldReturnSuccess() {
        MovieResponse expectedResponse = new MovieResponse(true, "some-movie");
        when(request.body()).thenReturn(gson.toJson(expectedResponse));

        MovieResponse actualResponse = movieController.publishMovie(request, response);

        assertEquals(expectedResponse.getMovieName(), actualResponse.getMovieName());
        verify(response).type("application/json");
        verify(response).status(200);
    }

    @Test
    public void testShouldReturnErrorIfMovieNameIsEmpty() {
        MovieResponse expectedResponse = new MovieResponse(false, "");
        when(request.body()).thenReturn(gson.toJson(expectedResponse));

        MovieResponse actualResponse = movieController.publishMovie(request, response);

        assertEquals("400", actualResponse.getError().getCode());
        assertEquals("bad request", actualResponse.getError().getMessage());
        verify(response).type("application/json");
        verify(response).status(400);
    }
}