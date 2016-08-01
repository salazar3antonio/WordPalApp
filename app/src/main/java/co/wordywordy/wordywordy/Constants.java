package co.wordywordy.wordywordy;

/**
 * Created by salaz on 7/31/2016.
 */
public class Constants {

    // web connection constants
    public static final int CONNECTION_TIMEOUT = 100000;
    public static final int READ_TIMEOUT = 100000;
    public static final int STATUS_OK = 200;
    public static final int STATUS_BAD_REQUEST = 400;
    public static final int STATUS_UNAUTHORIZED = 401;
    public static final int STATUS_NOT_FOUND = 404;
    public static final int STATUS_TOO_MANY_REQUESTS = 429;
    public static final int STATUS_SERVER_ERROR = 500;

    //Mashape Key for WordyWordy App
    public static final String API_TESTING_KEY = "vW3qO4KKFzmshG18RMyOSxDElYFDp1uOXh2jsn3Dvf8Rv6jCqJ";
    public static final String API_PRODUCTION_KEY = "VxKyScMn86mshAMSNaxk6IDI9bHQp1d13xnjsnPxFQYhKKEAMg";

    // URLs to be used to access the API
    // END_POINT + {Requested Word} + GET
    public static final String END_POINT = "https://wordsapiv1.p.mashape.com/words/";
    public static final String GET_DEFINITIONS = END_POINT + "/definitions";
    public static final String GET_SYNONYMS = END_POINT + "/synonyms";

    //JSON body keys
    public static final String WORD = "word";
    public static final String SYNONYMS = "synonyms";
    public static final String DEFINITIONS = "definitions";
    public static final String DEFINITION = "definition";

}
