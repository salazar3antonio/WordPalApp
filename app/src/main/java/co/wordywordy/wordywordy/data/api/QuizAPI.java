package co.wordywordy.wordywordy.data.api;

import co.wordywordy.wordywordy.data.model.Quiz;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by salaz on 8/16/2016.
 */
public interface QuizAPI {

    @Headers({
            "X-Mashape-Key: vW3qO4KKFzmshG18RMyOSxDElYFDp1uOXh2jsn3Dvf8Rv6jCqJ",
            "Accept: application/json"
    })

    @GET("?area=&level=")
    Call<Quiz> getQuiz(@Query("area") String area, @Query("level") int level);




}
