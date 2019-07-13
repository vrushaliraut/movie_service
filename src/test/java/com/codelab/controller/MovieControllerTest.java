package com.codelab.controller;

import com.codelab.Service.MovieService;
import com.codelab.contract.request.MovieRequest;
import com.codelab.contract.request.response.MovieResponse;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import spark.Request;
import spark.Response;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MovieControllerTest {

    @Mock
    private Request request;

    @Mock
    private Response response;

    @Mock
    MovieService movieService;

    private MovieController movieController;
    private Gson gson;

    @Before
    public void setUp() {
        gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        movieController = new MovieController(gson, movieService);
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
        verify(movieService, never()).publish(any());
    }

    @Test
    public void shouldReturnErrorIfKafkaPublishFailed() {
        MovieResponse expectedResponse = new MovieResponse(true, "some-movie");
        when(request.body()).thenReturn(gson.toJson(expectedResponse));

        MovieResponse actualResponse = movieController.publishMovie(request, response);

        assertEquals("401", actualResponse.getError().getCode());
        assertEquals("failed to publish kafka", actualResponse.getError().getMessage());
        assertFalse(movieService.publish(any()));
        verify(response).type("application/json");
        verify(response).status(401);
        verify(movieService, atLeastOnce()).publish(any());
    }

    @Test
    public void testShouldReturnSuccess() {
        MovieResponse expectedResponse = new MovieResponse(true, "some-movie");
        when(request.body()).thenReturn(gson.toJson(expectedResponse));
        ArgumentCaptor<MovieRequest> argumentCaptor = ArgumentCaptor.forClass(MovieRequest.class);
        when(movieService.publish(argumentCaptor.capture())).thenReturn(true);

        MovieResponse actualResponse = movieController.publishMovie(request, response);

        assertTrue(actualResponse.isSuccess());
        assertTrue(movieService.publish(any()));
        verify(response).type("application/json");
        verify(response).status(200);
    }
}