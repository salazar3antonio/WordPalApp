package co.wordpal.mywordpalappfree.database;

/**
 * Created by salaz on 10/23/2016.
 */

public class QuizDbSchema {

    // inner class to define our table. there can be multiple tables in a DB
    public static final class AreaTables {
        //define table names
        public static final String ELEMENTARY_TABLE = "elementary_quiz_table";
        public static final String MIDDLESCHOOL_TABLE = "middleschool_quiz_table";
        public static final String HIGHSCHOOL_TABLE = "highschool_quiz_table";
        public static final String SAT_TABLE = "sat_quiz_table";
        public static final String GMAT_TABLE = "gmat_quiz_table";
        public static final String OVERALL_TABLE = "overall_quiz_table";

        //define the column names
        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String DATE = "date";
            public static final String AREA = "area";
            public static final String LEVEL = "level";
            public static final String TOTAL_SCORE = "total_score";
            public static final String TOTAL_CORRECT = "total_correct";
            public static final String TOTAL_STARS = "total_stars";
            public static final String QUIZ_LIST_STRING = "quiz_list_string";
            public static final String COMPLETED_STATE = "completed_state";
            public static final String LOCKED_STATE = "locked_state";
        }

    }

}
