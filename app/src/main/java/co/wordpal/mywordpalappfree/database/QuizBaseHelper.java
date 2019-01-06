package co.wordpal.mywordpalappfree.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by salaz on 10/23/2016.
 */

public class QuizBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    public static final String DATABASE_FILE_NAME = "quizDB.dp";

    public QuizBaseHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //overriding the onCreate method of this class in order to execute the creating of the DB file. points the Schema cols with key value pairs

        sqLiteDatabase.execSQL("create table " + QuizDbSchema.AreaTables.ELEMENTARY_TABLE + "(" +
                " _id integer primary key autoincrement, " +
                QuizDbSchema.AreaTables.Cols.UUID + ", " +
                QuizDbSchema.AreaTables.Cols.DATE + ", " +
                QuizDbSchema.AreaTables.Cols.AREA + ", " +
                QuizDbSchema.AreaTables.Cols.LEVEL + ", " +
                QuizDbSchema.AreaTables.Cols.TOTAL_SCORE + ", " +
                QuizDbSchema.AreaTables.Cols.TOTAL_CORRECT + ", " +
                QuizDbSchema.AreaTables.Cols.TOTAL_STARS + ", " +
                QuizDbSchema.AreaTables.Cols.QUIZ_LIST_STRING + ", " +
                QuizDbSchema.AreaTables.Cols.COMPLETED_STATE + ", " +
                QuizDbSchema.AreaTables.Cols.LOCKED_STATE +
                ")"
        );

        sqLiteDatabase.execSQL("create table " + QuizDbSchema.AreaTables.MIDDLESCHOOL_TABLE + "(" +
                " _id integer primary key autoincrement, " +
                QuizDbSchema.AreaTables.Cols.UUID + ", " +
                QuizDbSchema.AreaTables.Cols.DATE + ", " +
                QuizDbSchema.AreaTables.Cols.AREA + ", " +
                QuizDbSchema.AreaTables.Cols.LEVEL + ", " +
                QuizDbSchema.AreaTables.Cols.TOTAL_SCORE + ", " +
                QuizDbSchema.AreaTables.Cols.TOTAL_CORRECT + ", " +
                QuizDbSchema.AreaTables.Cols.TOTAL_STARS + ", " +
                QuizDbSchema.AreaTables.Cols.QUIZ_LIST_STRING + ", " +
                QuizDbSchema.AreaTables.Cols.COMPLETED_STATE + ", " +
                QuizDbSchema.AreaTables.Cols.LOCKED_STATE +
                ")"
        );

        sqLiteDatabase.execSQL("create table " + QuizDbSchema.AreaTables.HIGHSCHOOL_TABLE + "(" +
                " _id integer primary key autoincrement, " +
                QuizDbSchema.AreaTables.Cols.UUID + ", " +
                QuizDbSchema.AreaTables.Cols.DATE + ", " +
                QuizDbSchema.AreaTables.Cols.AREA + ", " +
                QuizDbSchema.AreaTables.Cols.LEVEL + ", " +
                QuizDbSchema.AreaTables.Cols.TOTAL_SCORE + ", " +
                QuizDbSchema.AreaTables.Cols.TOTAL_CORRECT + ", " +
                QuizDbSchema.AreaTables.Cols.TOTAL_STARS + ", " +
                QuizDbSchema.AreaTables.Cols.QUIZ_LIST_STRING + ", " +
                QuizDbSchema.AreaTables.Cols.COMPLETED_STATE + ", " +
                QuizDbSchema.AreaTables.Cols.LOCKED_STATE +
                ")"
        );

        sqLiteDatabase.execSQL("create table " + QuizDbSchema.AreaTables.SAT_TABLE + "(" +
                " _id integer primary key autoincrement, " +
                QuizDbSchema.AreaTables.Cols.UUID + ", " +
                QuizDbSchema.AreaTables.Cols.DATE + ", " +
                QuizDbSchema.AreaTables.Cols.AREA + ", " +
                QuizDbSchema.AreaTables.Cols.LEVEL + ", " +
                QuizDbSchema.AreaTables.Cols.TOTAL_SCORE + ", " +
                QuizDbSchema.AreaTables.Cols.TOTAL_CORRECT + ", " +
                QuizDbSchema.AreaTables.Cols.TOTAL_STARS + ", " +
                QuizDbSchema.AreaTables.Cols.QUIZ_LIST_STRING + ", " +
                QuizDbSchema.AreaTables.Cols.COMPLETED_STATE + ", " +
                QuizDbSchema.AreaTables.Cols.LOCKED_STATE +
                ")"
        );

        sqLiteDatabase.execSQL("create table " + QuizDbSchema.AreaTables.GMAT_TABLE + "(" +
                " _id integer primary key autoincrement, " +
                QuizDbSchema.AreaTables.Cols.UUID + ", " +
                QuizDbSchema.AreaTables.Cols.DATE + ", " +
                QuizDbSchema.AreaTables.Cols.AREA + ", " +
                QuizDbSchema.AreaTables.Cols.LEVEL + ", " +
                QuizDbSchema.AreaTables.Cols.TOTAL_SCORE + ", " +
                QuizDbSchema.AreaTables.Cols.TOTAL_CORRECT + ", " +
                QuizDbSchema.AreaTables.Cols.TOTAL_STARS + ", " +
                QuizDbSchema.AreaTables.Cols.QUIZ_LIST_STRING + ", " +
                QuizDbSchema.AreaTables.Cols.COMPLETED_STATE + ", " +
                QuizDbSchema.AreaTables.Cols.LOCKED_STATE +
                ")"
        );

        sqLiteDatabase.execSQL("create table " + QuizDbSchema.AreaTables.OVERALL_TABLE + "(" +
                " _id integer primary key autoincrement, " +
                QuizDbSchema.AreaTables.Cols.UUID + ", " +
                QuizDbSchema.AreaTables.Cols.DATE + ", " +
                QuizDbSchema.AreaTables.Cols.AREA + ", " +
                QuizDbSchema.AreaTables.Cols.LEVEL + ", " +
                QuizDbSchema.AreaTables.Cols.TOTAL_SCORE + ", " +
                QuizDbSchema.AreaTables.Cols.TOTAL_CORRECT + ", " +
                QuizDbSchema.AreaTables.Cols.TOTAL_STARS + ", " +
                QuizDbSchema.AreaTables.Cols.QUIZ_LIST_STRING + ", " +
                QuizDbSchema.AreaTables.Cols.COMPLETED_STATE + ", " +
                QuizDbSchema.AreaTables.Cols.LOCKED_STATE +
                ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}
