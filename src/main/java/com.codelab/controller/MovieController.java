package com.codelab.controller;

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
    private final Gson gson;

    public MovieController(Gson gson) {
        this.gson = gson;
    }

    public MovieResponse publishMovie(final Request request, final Response response) {
        response.type(CONTENT_TYPE);
        MovieRequest movieRequest = gson.fromJson(request.body(), MovieRequest.class);
        if (!movieRequest.isValid()) {
            response.status(BAD_REQUEST);
            return new MovieResponse(false, new Error("400", "bad request"));
        }
        response.status(STATUS_CODE_OK);
        return new MovieResponse(true, movieRequest.getMovieName());
    }
}
