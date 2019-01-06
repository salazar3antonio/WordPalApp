
package co.wordpal.mywordpalappfree.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class QuizList {

    @SerializedName("quiz")
    @Expose
    private List<String> quiz = new ArrayList<String>();

    @SerializedName("option")
    @Expose
    private List<String> option = new ArrayList<String>();

    @SerializedName("correct")
    @Expose
    private int correct;

    @SerializedName("user choice")
    @Expose
    private int userChoice;

    @SerializedName("bonus points")
    @Expose
    private int bonusPoints;

    /**
     * @return The quiz. The three words to associate an option with.
     */
    public List<String> getQuiz() {
        return quiz;
    }

    /**
     * @param quiz The quiz. The three words to associate an option with.
     */
    public void setQuiz(List<String> quiz) {
        this.quiz = quiz;
    }

    /**
     * @return The options to choose from
     */
    public List<String> getOption() {
        return option;
    }

    /**
     * @param option The options to choose from
     */
    public void setOption(List<String> option) {
        this.option = option;
    }

    /**
     * @return The correct answer
     */
    public int getCorrect() {
        return correct;
    }

    /**
     * @param correct The correct answer
     */
    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getUserChoice() {
        return userChoice;
    }

    public void setUserChoice(int userChoice) {
        this.userChoice = userChoice;
    }

    public int getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(int bonusPoints) {
        this.bonusPoints = bonusPoints;
    }


}
