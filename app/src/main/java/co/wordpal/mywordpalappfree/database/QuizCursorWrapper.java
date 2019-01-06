package co.wordpal.mywordpalappfree.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;
import java.util.UUID;

import co.wordpal.mywordpalappfree.data.model.Quiz;


/**
 * Created by salaz on 10/23/2016.
 */

public class QuizCursorWrapper extends CursorWrapper {

    // we pass in a cursor and the wrapper does a lot of heavy lifting
    public QuizCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Quiz getQuiz() {
        String uuidString = getString(getColumnIndex(QuizDbSchema.AreaTables.Cols.UUID));
        long date = getLong(getColumnIndex(QuizDbSchema.AreaTables.Cols.DATE));
        String area = getString(getColumnIndex(QuizDbSchema.AreaTables.Cols.AREA));
        int level = getInt(getColumnIndex(QuizDbSchema.AreaTables.Cols.LEVEL));
        int total_score = getInt(getColumnIndex(QuizDbSchema.AreaTables.Cols.TOTAL_SCORE));
        int total_correct = getInt(getColumnIndex(QuizDbSchema.AreaTables.Cols.TOTAL_CORRECT));
        int total_stars = getInt(getColumnIndex(QuizDbSchema.AreaTables.Cols.TOTAL_STARS));
        String quiz_list = getString(getColumnIndex(QuizDbSchema.AreaTables.Cols.QUIZ_LIST_STRING));
        int completed_state = getInt(getColumnIndex(QuizDbSchema.AreaTables.Cols.COMPLETED_STATE));
        int locked_state = getInt(getColumnIndex(QuizDbSchema.AreaTables.Cols.LOCKED_STATE));

        Quiz quiz = new Quiz(UUID.fromString(uuidString));

        quiz.setDate(new Date(date));
        quiz.setArea(area);
        quiz.setLevel(level);
        quiz.setTotalUserScore(total_score);
        quiz.setTotalCorrect(total_correct);
        quiz.setTotalStars(total_stars);
        quiz.setQuizListJSONString(quiz_list);
        quiz.setCompletedState(completed_state);
        quiz.setLockedState(locked_state);

        return quiz;
    }

}
