package co.wordpal.mywordpalappfree.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.wordpal.mywordpalappfree.data.model.Quiz;


/**
 * Created by salaz on 10/23/2016.
 */

public class QuizUserList {

    public static final String TAG = QuizUserList.class.getName();

    //singleton class that only has one instantiation
    private static QuizUserList sQuizListUser;

    private Context mContext;
    private SQLiteDatabase mQuizDataBase;

    // constructor
    private QuizUserList(Context context) {
        mContext = context.getApplicationContext();
        mQuizDataBase = new QuizBaseHelper(mContext).getWritableDatabase();
    }

    //get singleton class
    public static QuizUserList get(Context context) {
        if (sQuizListUser == null) {
            sQuizListUser = new QuizUserList(context);
        }
        return sQuizListUser;
    }

    public List<Quiz> getQuizzes(String areaTable) {
        List<Quiz> quizzes = new ArrayList<>();

        QuizCursorWrapper cursorWrapper = queryQuizzes(areaTable, null, null);

        try {
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()) {
                quizzes.add(cursorWrapper.getQuiz());
                cursorWrapper.moveToNext();
            }
        } finally {
            cursorWrapper.close();
        }

        return quizzes;
    }

    public Quiz getQuiz(String areaTable, UUID uuid) {

        QuizCursorWrapper cursorWrapper = queryQuizzes(areaTable, QuizDbSchema.AreaTables.Cols.UUID + " = ?", new String[]{uuid.toString()});

        try {
            if (cursorWrapper.getCount() == 0) {
                return null;
            }
            cursorWrapper.moveToFirst();
            return cursorWrapper.getQuiz();
        } finally {
            cursorWrapper.close();
        }
    }

    public static ContentValues getContentValues(Quiz quiz) {

        ContentValues values = new ContentValues();
        values.put(QuizDbSchema.AreaTables.Cols.UUID, quiz.getID().toString());
        values.put(QuizDbSchema.AreaTables.Cols.DATE, quiz.getDate().getTime());
        values.put(QuizDbSchema.AreaTables.Cols.AREA, quiz.getArea());
        values.put(QuizDbSchema.AreaTables.Cols.LEVEL, quiz.getLevel());
        values.put(QuizDbSchema.AreaTables.Cols.TOTAL_SCORE, quiz.getTotalUserScore());
        values.put(QuizDbSchema.AreaTables.Cols.TOTAL_CORRECT, quiz.getTotalCorrect());
        values.put(QuizDbSchema.AreaTables.Cols.TOTAL_STARS, quiz.getTotalStars());
        values.put(QuizDbSchema.AreaTables.Cols.QUIZ_LIST_STRING, quiz.getQuizListJSONString());
        values.put(QuizDbSchema.AreaTables.Cols.COMPLETED_STATE, quiz.getCompletedState());
        values.put(QuizDbSchema.AreaTables.Cols.LOCKED_STATE, quiz.getLockedState());

        return values;
    }

    public QuizCursorWrapper queryQuizzes(String areaTable, String whereClause, String[] whereArgs) {

        Cursor cursor = mQuizDataBase.query(
                areaTable,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new QuizCursorWrapper(cursor);

    }

    public void addQuiz(String areaTable, Quiz quiz) {
        ContentValues values = getContentValues(quiz);
        mQuizDataBase.insert(areaTable, null, values);
        Log.d(TAG, "Quiz Added to Database " + values.toString());
    }

    public void updateQuiz(String areaTable, Quiz quiz) {
        String uuidString = quiz.getID().toString();
        ContentValues values = getContentValues(quiz);
        mQuizDataBase.update(areaTable, values, QuizDbSchema.AreaTables.Cols.UUID + " = ?", new String[]{uuidString});
    }

    public void deleteQuiz(String areaTable, Quiz quiz) {
        String uuidString = quiz.getID().toString();
        mQuizDataBase.delete(areaTable, QuizDbSchema.AreaTables.Cols.UUID + " = ?", new String[]{uuidString});
    }

    public void clearTable(String TABLE_NAME) {
        String clearDBQuery = "DELETE FROM "+TABLE_NAME;
        mQuizDataBase.execSQL(clearDBQuery);
    }



}
