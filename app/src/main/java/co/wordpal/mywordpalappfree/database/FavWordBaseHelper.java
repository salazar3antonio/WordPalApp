package co.wordpal.mywordpalappfree.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by salaz on 1/16/2017.
 */

public class FavWordBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    public static final String DATABASE_FILE_NAME = "favWordDB.dp";

    public FavWordBaseHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table " + FavWordDbSchema.FavWordsTable.FAV_WORDS_TABLE + "(" +
                " _id integer primary key autoincrement, " +
                FavWordDbSchema.FavWordsTable.Cols.FAV_WORD + ", " +
                FavWordDbSchema.FavWordsTable.Cols.NOUN_MEAN + ", " +
                FavWordDbSchema.FavWordsTable.Cols.VERB_MEAN + ", " +
                FavWordDbSchema.FavWordsTable.Cols.ADVERB_MEAN + ", " +
                FavWordDbSchema.FavWordsTable.Cols.ADJECTIVE_MEAN +
                ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
