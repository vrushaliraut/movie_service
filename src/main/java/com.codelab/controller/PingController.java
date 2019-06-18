package com.codelab.controller;

import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class PingController {

    public Map<String,String> ping(Request request, Response response) {
        response.status(200);
        response.type("application/json");
        HashMap<String, String> result = new HashMap<>();
        result.put("message", "pong");
        return result;
    }
}
