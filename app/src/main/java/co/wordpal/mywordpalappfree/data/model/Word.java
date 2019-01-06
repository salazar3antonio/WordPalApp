
package co.wordpal.mywordpalappfree.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Word {

    @SerializedName("entry")
    @Expose
    private String entry;
    @SerializedName("request")
    @Expose
    private String request;
    @SerializedName("response")
    @Expose
    private String response;
    @SerializedName("meaning")
    @Expose
    private Meaning meaning;
    @SerializedName("ipa")
    @Expose
    private String ipa;
    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("result_code")
    @Expose
    private String resultCode;
    @SerializedName("result_msg")
    @Expose
    private String resultMsg;

    private String mWord;
    private String mNounMeaning;
    private String mVerbMeaning;
    private String mAdverbMeaning;
    private String mAdjectiveMeaning;
    private String mFavoriteWord;

    public Word() {
    }

    /**
     * 
     * @return
     *     The entry
     */
    public String getEntry() {
        return entry;
    }

    /**
     * 
     * @param entry
     *     The word to be defined.
     */
    public void setEntry(String entry) {
        this.entry = entry;
    }

    /**
     * 
     * @return
     *     The request
     */
    public String getRequest() {
        return request;
    }

    /**
     * 
     * @param request
     *     The request
     */
    public void setRequest(String request) {
        this.request = request;
    }

    /**
     * 
     * @return
     *     The response
     */
    public String getResponse() {
        return response;
    }

    /**
     * 
     * @param response
     *     The response
     */
    public void setResponse(String response) {
        this.response = response;
    }

    /**
     * 
     * @return
     *     The meanings of the word. Includes noun, verb, adverb and adjective.
     */
    public Meaning getMeaning() {
        return meaning;
    }

    /**
     * 
     * @param meaning
     *     The meaning of the word.
     */
    public void setMeaning(Meaning meaning) {
        this.meaning = meaning;
    }

    /**
     * 
     * @return
     *     The ipa
     */
    public String getIpa() {
        return ipa;
    }

    /**
     * 
     * @param ipa
     *     The ipa
     */
    public void setIpa(String ipa) {
        this.ipa = ipa;
    }

    /**
     * 
     * @return
     *     The version
     */
    public String getVersion() {
        return version;
    }

    /**
     * 
     * @param version
     *     The version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * 
     * @return
     *     The resultCode
     */
    public String getResultCode() {
        return resultCode;
    }

    /**
     * 
     * @param resultCode
     *     The result_code
     */
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * 
     * @return
     *     The resultMsg
     */
    public String getResultMsg() {
        return resultMsg;
    }

    /**
     * 
     * @param resultMsg
     *     The result_msg
     */
    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getFavoriteWord() {
        return mFavoriteWord;
    }

    public void setFavoriteWord(String favoriteWord) {
        mFavoriteWord = favoriteWord;
    }

    public String getNounMeaning() {
        return mNounMeaning;
    }

    public void setNounMeaning(String nounMeaning) {
        mNounMeaning = nounMeaning;
    }

    public String getVerbMeaning() {
        return mVerbMeaning;
    }

    public void setVerbMeaning(String verbMeaning) {
        mVerbMeaning = verbMeaning;
    }

    public String getAdverbMeaning() {
        return mAdverbMeaning;
    }

    public void setAdverbMeaning(String adverbMeaning) {
        mAdverbMeaning = adverbMeaning;
    }

    public String getAdjectiveMeaning() {
        return mAdjectiveMeaning;
    }

    public void setAdjectiveMeaning(String adjectiveMeaning) {
        mAdjectiveMeaning = adjectiveMeaning;
    }

    public String getWord() {
        return mWord;
    }

    public void setWord(String word) {
        mWord = word;
    }
}
