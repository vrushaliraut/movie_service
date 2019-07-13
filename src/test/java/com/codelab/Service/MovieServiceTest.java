package com.codelab.Service;

import com.codelab.contract.request.MovieRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceTest {

    private MovieService movieService;

    @Before
    public void setUp() {
        movieService = new MovieService();
    }

    @Test
    public void shouldNotPublishIfRequestIsValid() {
        MovieRequest movieRequest = new MovieRequest("movie_name");

        boolean isSuccess = movieService.publish(movieRequest);

        assertTrue(isSuccess);
    }
}