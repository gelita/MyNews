package com.fanikiosoftware.mynews.controllers.utility;


public final class Constants {

    public static final String BASE_URL = "https://api.nytimes.com/svc/";

    public static final String[] titles = {
            "Top Stories", "Business", "Tech", "Fashion", "Real Estate", "Health"};

//    public static final String API_KEY = "nHg4SGAl3zIrn5oT8ik9PQnhKXNsnjh6";

    //Prevent objects from being constructed from
    //this class, by declaring this private constructor.
    private Constants() {
        //this prevents even the native class from
        //calling this constructor as well
        throw new AssertionError();
    }
}