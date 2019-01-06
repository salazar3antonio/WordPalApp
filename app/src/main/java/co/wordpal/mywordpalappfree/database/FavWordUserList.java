package co.wordpal.mywordpalappfree.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import co.wordpal.mywordpalappfree.data.model.Word;
import co.wordpal.mywordpalappfree.database.FavWordDbSchema.FavWordsTable;


/**
 * Created by salaz on 1/16/2017.
 */

public class FavWordUserList {

    public static final String TAG = FavWordUserList.class.getName();

    private static FavWordUserList sFavWordUserList;

    private Context mContext;
    private SQLiteDatabase mFavWordDataBase;

    // constructor
    public FavWordUserList(Context context) {
        mContext = context.getApplicationContext();
        mFavWordDataBase = new FavWordBaseHelper(mContext).getWritableDatabase();
    }

    //get singleton
    public static FavWordUserList get(Context context) {
        if (sFavWordUserList == null) {
            sFavWordUserList = new FavWordUserList(context);
        }
        return sFavWordUserList;
    }

    public List<Word> getFavWords() {

        List<Word> words = new ArrayList<>();

        FavWordCursorWrapper cursorWrapper = queryWords(null, null);

        try {
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()) {
                words.add(cursorWrapper.getWord());
                cursorWrapper.moveToNext();
            }
        } finally {
            cursorWrapper.close();
        }

        return words;

    }

    public Word getFavWord(String favWord) {

        FavWordCursorWrapper cursorWrapper = queryWords(FavWordsTable.Cols.FAV_WORD + " = ?", new String[]{favWord});

        try {
            if (cursorWrapper.getCount() == 0) {
                return null;
            }
            cursorWrapper.moveToFirst();
            return cursorWrapper.getWord();
        } finally {
            cursorWrapper.close();
        }

    }

    public static ContentValues getContentValues(Word word) {

        ContentValues values = new ContentValues();

        values.put(FavWordsTable.Cols.FAV_WORD, word.getFavoriteWord());
        values.put(FavWordsTable.Cols.NOUN_MEAN, word.getNounMeaning());
        values.put(FavWordsTable.Cols.VERB_MEAN, word.getVerbMeaning());
        values.put(FavWordsTable.Cols.ADVERB_MEAN, word.getAdverbMeaning());
        values.put(FavWordsTable.Cols.ADJECTIVE_MEAN, word.getAdjectiveMeaning());

        return values;
    }

    public FavWordCursorWrapper queryWords(String whereClause, String[] whereArgs) {

        Cursor cursor = mFavWordDataBase.query(
                //check table name in query method sig
                FavWordsTable.FAV_WORDS_TABLE,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new FavWordCursorWrapper(cursor);
    }

    public void addFavWord(Word word) {
        ContentValues values = getContentValues(word);
        //check table name in insert method sig
        mFavWordDataBase.insert(FavWordsTable.FAV_WORDS_TABLE, null, values);
        Log.d(TAG, "- " + word.getFavoriteWord() + " -" + " ADDED to FavWordDb" + "\n" +
                "Noun: " + word.getNounMeaning() + "\n" +
                "Verb: " + word.getVerbMeaning() + "\n" +
                "Adverb: " + word.getAdverbMeaning() + "\n" +
                "Adjective: " + word.getAdjectiveMeaning()
        );
    }

//    public void updateFavWord(Word word) {
//        String uuidString = word.getID().toString();
//        ContentValues values = getContentValues(word);
//        mFavWordDataBase.update(FavWordsTable.FAV_WORDS_TABLE, values, FavWordsTable.Cols.UUID + " = ?", new String[]{uuidString});
//
//
//    }

    public void deleteFavWord(String favWord) {
        mFavWordDataBase.delete(FavWordsTable.FAV_WORDS_TABLE, FavWordsTable.Cols.FAV_WORD + " = ?", new String[]{favWord});
        Log.d(TAG, "- " + favWord + " -" + " DELETED from FavWordDB");
    }

}
