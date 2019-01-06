package co.wordpal.mywordpalappfree.database;

/**
 * Created by salaz on 1/16/2017.
 */

public class FavWordDbSchema {

    public static final class FavWordsTable {

        public static final String FAV_WORDS_TABLE = "favorite_words_table";

        public static final class Cols {
            public static final String FAV_WORD = "favorite_word";
            public static final String NOUN_MEAN = "noun_meaning";
            public static final String VERB_MEAN = "verb_meaning";
            public static final String ADVERB_MEAN = "adverb_meaning";
            public static final String ADJECTIVE_MEAN = "adjective_meaning";
        }
    }

}
