
package co.wordpal.mywordpalappfree.data.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Meaning {

    @SerializedName("noun")
    @Expose
    private String noun;
    @SerializedName("verb")
    @Expose
    private String verb;
    @SerializedName("adverb")
    @Expose
    private String adverb;
    @SerializedName("adjective")
    @Expose
    private String adjective;

    /**
     * 
     * @return
     *     The noun
     */
    public String getNoun() {
        return noun;
    }

    /**
     * 
     * @param noun
     *     The noun
     */
    public void setNoun(String noun) {
        this.noun = noun;
    }

    /**
     * 
     * @return
     *     The verb
     */
    public String getVerb() {
        return verb;
    }

    /**
     * 
     * @param verb
     *     The verb
     */
    public void setVerb(String verb) {
        this.verb = verb;
    }

    /**
     * 
     * @return
     *     The adverb
     */
    public String getAdverb() {
        return adverb;
    }

    /**
     * 
     * @param adverb
     *     The adverb
     */
    public void setAdverb(String adverb) {
        this.adverb = adverb;
    }

    /**
     * 
     * @return
     *     The adjective
     */
    public String getAdjective() {
        return adjective;
    }

    /**
     * 
     * @param adjective
     *     The adjective
     */
    public void setAdjective(String adjective) {
        this.adjective = adjective;
    }

}
