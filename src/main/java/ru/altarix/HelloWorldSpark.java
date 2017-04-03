package ru.altarix;

import static spark.Spark.*;

public class HelloWorldSpark {

    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello World");
    }

}
