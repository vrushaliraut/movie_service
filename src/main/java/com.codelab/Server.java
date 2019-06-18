package com.codelab;

import com.codelab.controller.PingController;
import com.google.gson.Gson;

import static spark.Spark.get;
import static spark.Spark.port;

public class Server {

    private PingController pingController;
    private Gson gson;

    public Server(PingController pingController, Gson gson) {
        this.pingController = pingController;
        this.gson = gson;
    }

    public void start(){
        port(8080);
        get("/ping", pingController::ping, gson::toJson);
    }
}
