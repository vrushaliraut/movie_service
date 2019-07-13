package com.codelab.controller;

import com.codelab.Service.MovieService;
import com.codelab.contract.request.MovieRequest;
import com.codelab.contract.request.response.MovieResponse;
import com.codelab.model.Error;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

public class MovieController {

    private static final String CONTENT_TYPE = "application/json";
    private static final int STATUS_CODE_OK = 200;
    private static final int BAD_REQUEST = 400;
    private static final String BAD_REQUEST_MSG = "bad request";
    private static final String BAD_REQUEST_CODE = "400";
    private static final String PUBLISH_FAILED_MSG = "failed to publish kafka";
    private static final String PUBLISH_FAILED_CODE = "401";
    private static final int PUBLISH_FAILED_REQUEST = 401;
    private final Gson gson;
    private MovieService movieService;

    public MovieController(Gson gson, MovieService movieService) {
        this.gson = gson;
        this.movieService = movieService;
    }

    public MovieResponse publishMovie(final Request request, final Response response) {
        response.type(CONTENT_TYPE);
        MovieRequest movieRequest = gson.fromJson(request.body(), MovieRequest.class);
        if (!movieRequest.isValid()){
            response.status(BAD_REQUEST);
            return new MovieResponse(false, new Error(BAD_REQUEST_CODE, BAD_REQUEST_MSG));
        }
        final boolean isSuccessful = movieService.publish(movieRequest);
        if (!isSuccessful){
            response.status(PUBLISH_FAILED_REQUEST);
            return new MovieResponse(false, new Error(PUBLISH_FAILED_CODE, PUBLISH_FAILED_MSG));
        }
        response.status(STATUS_CODE_OK);
        return new MovieResponse(true, movieRequest.getMovieName());
    }
}
