
package co.wordywordy.wordywordy.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

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
    private List<Quizlist> quizlist = new ArrayList<Quizlist>();
    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("result_code")
    @Expose
    private String resultCode;
    @SerializedName("result_msg")
    @Expose
    private String resultMsg;

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
     * @return The quizlist
     */
    public List<Quizlist> getQuizlist() {
        return quizlist;
    }

    /**
     * @param quizlist The quizlist
     */
    public void setQuizlist(List<Quizlist> quizlist) {
        this.quizlist = quizlist;
    }

    /**
     * @return The version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version The version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return The author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author The author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return The email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email The email
     */
    public void setEmail(String email) {
        this.email = email;
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

}
