package co.wordpal.mywordpalappfree.data.api;

import co.wordpal.mywordpalappfree.data.model.Word;
import co.wordpal.mywordpalappfree.helpers.Constants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by salaz on 11/26/2016.
 */

public interface WordAPI {

    @Headers({
            "X-Mashape-Key: " + Constants.X_MASHAPE_API_KEY,
            "Accept: application/json"
    })

    @GET("?entry=")
    Call<Word> getWord(@Query("entry") String entry);
}
