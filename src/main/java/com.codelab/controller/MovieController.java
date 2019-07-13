package com.codelab.controller;

import com.codelab.contract.request.MovieRequest;
import com.codelab.contract.request.response.MovieResponse;
import com.codelab.model.Error;
import com.codelab.repository.KafkaRepository;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

public class MovieController {

    private static final String CONTENT_TYPE = "application/json";
    private static final int STATUS_CODE_OK = 200;
    private static final int BAD_REQUEST = 400;
    private static final String BAD_REQUEST_MSG = "bad request";
    private static final String BAD_REQUEST_CODE = "400";
    private final Gson gson;
    private KafkaRepository kafkaRepository;

    public MovieController(Gson gson, KafkaRepository kafkaRepository) {
        this.gson = gson;
        this.kafkaRepository = kafkaRepository;
    }

    public MovieResponse publishMovie(final Request request, final Response response) {
        response.type(CONTENT_TYPE);
        MovieRequest movieRequest = gson.fromJson(request.body(), MovieRequest.class);
        if (!movieRequest.isValid()) {
            response.status(BAD_REQUEST);
            return new MovieResponse(false, new Error(BAD_REQUEST_CODE, BAD_REQUEST_MSG));
        }
        response.status(STATUS_CODE_OK);
        try {
            System.out.println("start publishing to kafka");

            kafkaRepository.publish(movieRequest.getMovieName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new MovieResponse(true, movieRequest.getMovieName());
    }
}
