package com.codelab.contract.request;

import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class MovieRequest {

    private String movieName;

    public MovieRequest(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieName() {
        return movieName;
    }

    public boolean isValid() {
        return !isEmpty(movieName);
    }
}
