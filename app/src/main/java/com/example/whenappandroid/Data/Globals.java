package com.example.whenappandroid.Data;

public class Globals {
    public static String currentUser;
    private static String server = "10.0.2.2:5270";

    public static String getServerAndroid() {
        return server.replace("localhost", "10.0.2.2");
    }

    public static String getServerRegular() {
        return server;
    }

    public static void setServer(String server) {
        Globals.server = server.replace("10.0.2.2", "localhost");
    }

    public static String regularToAndroid(String server) {
        return server.replace("localhost", "10.0.2.2");
    }
}
