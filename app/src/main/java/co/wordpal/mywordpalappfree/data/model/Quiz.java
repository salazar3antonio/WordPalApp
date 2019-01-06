
package co.wordpal.mywordpalappfree.data.model;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Quiz {

    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("level")
    @Expose
    private int level;
    @SerializedName("quizlist")
    @Expose
    private List<QuizList> quizList = new ArrayList<QuizList>();
    @SerializedName("result_code")
    @Expose
    private String resultCode;
    @SerializedName("result_msg")
    @Expose
    private String resultMsg;

    private UUID mID;
    private Date mDate;

    private int totalUserScore;
    private int totalCorrect;
    private int totalStars;
    private int completedState;
    private int lockedState;

    private String quizListJSONString;

    public Quiz() {
        this(UUID.randomUUID());
    }

    public Quiz(UUID id) {
        mID = id;
        mDate = new Date();
    }

    /**
     * @return The area
     */
    public String getArea() {
        return area;
    }

    /**
     * @param area The area
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * @return The level
     */
    public int getLevel() {
        return level;
    }

    /**
     * @param level The level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * @return The list of Quizzes
     */
    public List<QuizList> getQuizList() {
        return quizList;
    }

    /**
     * @param quizLists The list of Quizzes
     */
    public void setQuizList(List<QuizList> quizLists) {
        this.quizList = quizLists;
    }

    /**
     * @return The resultCode
     */
    public String getResultCode() {
        return resultCode;
    }

    /**
     * @param resultCode The result_code
     */
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * @return The resultMsg
     */
    public String getResultMsg() {
        return resultMsg;
    }

    /**
     * @param resultMsg The result_msg
     */
    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public int getTotalUserScore() {
        return totalUserScore;
    }

    public void setTotalUserScore(int totalUserScore) {
        this.totalUserScore = totalUserScore;
    }

    public int getTotalCorrect() {
        return totalCorrect;
    }

    public void setTotalCorrect(int totalCorrect) {
        this.totalCorrect = totalCorrect;
    }

    public UUID getID() {
        return mID;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public int getTotalStars() {
        return totalStars;
    }

    public void setTotalStars(int totalStars) {
        this.totalStars = totalStars;
    }

    public int getCompletedState() {
        return completedState;
    }

    public void setCompletedState(int completedState) {
        this.completedState = completedState;
    }

    public int getLockedState() {
        return lockedState;
    }

    public void setLockedState(int lockedState) {
        this.lockedState = lockedState;
    }

    public String getQuizListJSONString() {
        return quizListJSONString;
    }

    public void setQuizListJSONString(String quizListJSONString) {
        this.quizListJSONString = quizListJSONString;
    }

    public String quizListToJSONString(List<QuizList> quizLists) {

        Gson gson = new Gson();
        String inputString = gson.toJson(quizLists);
        return inputString;

    }

    public List<QuizList> quizListFROMjsonString(String string) {

        Gson gson = new Gson();
        Type type = new TypeToken<List<QuizList>>() {}.getType();
        List<QuizList> output = gson.fromJson(string, type);
        return output;

    }

}
