package co.wordpal.mywordpalappfree.data.api;

import co.wordpal.mywordpalappfree.data.model.Quiz;
import co.wordpal.mywordpalappfree.helpers.Constants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by salaz on 8/16/2016.
 */
public interface QuizAPI {

    @Headers({
            "X-Mashape-Key: " + Constants.X_MASHAPE_API_KEY,
            "Accept: application/json"
    })

    @GET("?area=&level=")
    Call<Quiz> getQuiz(@Query("area") String area, @Query("level") int level);




}
