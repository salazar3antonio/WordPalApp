package co.wordywordy.wordywordy.data;

/**
 * Created by Desktop on 8/1/2016.
 */
public class Word {

    private String definition;
    private String main_word;
    private String synonym;

    public Word(String main_word) {
        this.main_word = main_word;
    }


    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getMain_word() {
        return main_word;
    }

    public void setMain_word(String main_word) {
        this.main_word = main_word;
    }

    public String getSynonym() {
        return synonym;
    }

    public void setSynonym(String synonym) {
        this.synonym = synonym;
    }
}
