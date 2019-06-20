package com.codelab.controller;

import spark.Request;
import spark.Response;

import java.util.HashMap;

public class MovieController {

    public HashMap<String,String> publishMovie(Request request, Response response) {
        response.type("application/json");
        response.status(200);
        HashMap<String, String> result = new HashMap<>();
        result.put("message","publish successfully");
        return result;
    }
}
