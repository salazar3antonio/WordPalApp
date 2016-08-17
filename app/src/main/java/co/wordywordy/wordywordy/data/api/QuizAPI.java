package co.wordywordy.wordywordy.data.api;

import co.wordywordy.wordywordy.data.model.Quiz;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by salaz on 8/16/2016.
 */
public interface QuizAPI {

    @Headers({
            "X-Mashape-Key: VxKyScMn86mshAMSNaxk6IDI9bHQp1d13xnjsnPxFQYhKKEAMg",
            "Accept: application/json"
    })

    @GET("?area=es&level=10")
    Call<Quiz> getQuiz();




}
