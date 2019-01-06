package co.wordpal.mywordpalappfree.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import co.wordpal.mywordpalappfree.data.model.Word;
import co.wordpal.mywordpalappfree.database.FavWordDbSchema.FavWordsTable;

/**
 * Created by salaz on 1/16/2017.
 */

public class FavWordCursorWrapper extends CursorWrapper {

    public FavWordCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Word getWord() {

        //get values from columns
        String favoriteWord = getString(getColumnIndex(FavWordsTable.Cols.FAV_WORD));
        String noun_mean = getString(getColumnIndex(FavWordsTable.Cols.NOUN_MEAN));
        String verb_mean = getString(getColumnIndex(FavWordsTable.Cols.VERB_MEAN));
        String adverb_mean = getString(getColumnIndex(FavWordsTable.Cols.ADVERB_MEAN));
        String adjective_mean = getString(getColumnIndex(FavWordsTable.Cols.ADJECTIVE_MEAN));

        //instantiate new word object
        Word word = new Word();

        //set values from columns to object properties
        word.setFavoriteWord(favoriteWord);
        word.setNounMeaning(noun_mean);
        word.setVerbMeaning(verb_mean);
        word.setAdverbMeaning(adverb_mean);
        word.setAdjectiveMeaning(adjective_mean);

        return word;

    }

}
