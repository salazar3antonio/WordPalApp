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
            "X-Mashape-Key: VxKyScMn86mshAMSNaxk6IDI9bHQp1d13xnjsnPxFQYhKKEAMg",
            "Accept: application/json"
    })

    @GET("?area=&level=")
    Call<Quiz> getQuiz(@Query("area") String area, @Query("level") int level);




}
