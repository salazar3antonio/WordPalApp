
package co.wordywordy.wordywordy.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Quizlist {

    @SerializedName("quiz")
    @Expose
    private List<String> quiz = new ArrayList<String>();

    @SerializedName("option")
    @Expose
    private List<String> option = new ArrayList<String>();

    @SerializedName("correct")
    @Expose
    private int correct;

    /**
     * 
     * @return
     *     The quiz
     */
    public List<String> getQuiz() {
        return quiz;
    }

    /**
     * 
     * @param quiz
     *     The quiz
     */
    public void setQuiz(List<String> quiz) {
        this.quiz = quiz;
    }

    /**
     * 
     * @return
     *     The option
     */
    public List<String> getOption() {
        return option;
    }

    /**
     * 
     * @param option
     *     The option
     */
    public void setOption(List<String> option) {
        this.option = option;
    }

    /**
     * 
     * @return
     *     The correct
     */
    public int getCorrect() {
        return correct;
    }

    /**
     * 
     * @param correct
     *     The correct
     */
    public void setCorrect(int correct) {
        this.correct = correct;
    }

}
