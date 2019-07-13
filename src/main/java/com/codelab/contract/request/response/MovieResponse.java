package com.codelab.contract.request.response;

import com.codelab.model.Error;

public class MovieResponse {

    private final boolean success;
    private Error error;
    private String movieName;

    public MovieResponse(boolean success,String movieName) {
        this.movieName = movieName;
        this.success = success;
    }

    public MovieResponse(boolean success,Error error) {
        this.success = success;
        this.error = error;
    }

    public String getMovieName() {
        return movieName;
    }

    public boolean isSuccess() {
        return success;
    }

    public Error getError() {
        return error;
    }
}


