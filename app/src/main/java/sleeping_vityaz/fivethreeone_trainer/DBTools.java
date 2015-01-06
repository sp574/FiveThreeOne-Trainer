package sleeping_vityaz.fivethreeone_trainer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import sleeping_vityaz.fivethreeone_trainer.exercise.object.ExerciseObject;

public class DBTools extends SQLiteOpenHelper {

    private static DBTools sInstance;

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database name
    private static final String DATABASE_NAME = "workout_log";


    private static final String DEADLIFT = "deadlift";
    private static final String BENCH = "bench";
    private static final String SQUAT = "squat";
    private static final String PRESS = "press";

    // Table Names
    private static final String MAIN_E = "main_e_table";
    private static final String ASSISTANCE = "assistance_table";
    private static final String RM_LOG = "repmax_table";

    // Common column names
    private static final String KEY_ID = "col_exerciseID";
    private static final String WEEK = "col_week_num";
    private static final String CYCLE = "col_cycle_num";
    private static final String WEIGHT = "col_weight_num";
    private static final String DATE_CREATED = "col_date_created";
    private static final String NOTES = "col_notes";
    private static final String A_EXERCISE = "col_a_exercise";
    private static final String MAIN_EXERCISE = "col_m_exercise";


    public DBTools(Context applicationContext) {
        super(applicationContext, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DBTools getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new DBTools(context.getApplicationContext());
        }
        return sInstance;
    }


    @Override
    public void onCreate(SQLiteDatabase database) {

        database.execSQL(createTable(MAIN_E));
        database.execSQL(createTable(ASSISTANCE));
        database.execSQL(createTable(RM_LOG));


    }

    /* This will alter existing table without erasing any of the user's data
    *  Loops through each database version and  necessary columns*/
    @Override
    public void onUpgrade(final SQLiteDatabase database, final int oldVersion, final int newVersion) {

        int upgradeTo = oldVersion + 1;
        while (upgradeTo <= newVersion) {
            /*switch (upgradeTo)
            {
                case 2:
                    database.execSQL("ALTER TABLE " + DEADLIFT + " ADD COLUMN " + NEW_COLUMN_NAME + TYPE);
                    database.execSQL("ALTER TABLE " + BENCH + " ADD COLUMN " + NEW_COLUMN_NAME + TYPE);
                    database.execSQL("ALTER TABLE " + SQUAT + " ADD COLUMN " + NEW_COLUMN_NAME + TYPE);
                    database.execSQL("ALTER TABLE " + PRESS + " ADD COLUMN " + NEW_COLUMN_NAME + TYPE);
                    break;
            }*/
            upgradeTo++;
        }
    }

    /*
     * MAIN_E table: KEY_ID|WEEK|CYCLE|MAIN_EXERCISE|WEIGHT|DATE_CREATED|NOTES
     * ASSISTANCE table: KEY_ID|WEEK|CYCLE|MAIN_EXERCISE|A_EXERCISE|WEIGHT|DATE_CREATED
     */
    // Table Create Statement
    public String createTable(String TABLE) {
        String create = "";

        if (TABLE == ASSISTANCE) {
            create = "CREATE TABLE "
                    + ASSISTANCE + " ( " + KEY_ID + " INTEGER PRIMARY KEY, " + WEEK
                    + " INTEGER, " + CYCLE + " INTEGER, " + MAIN_EXERCISE + " TEXT, " + A_EXERCISE + " TEXT, " + WEIGHT + " TEXT, " + DATE_CREATED
                    + " DATETIME" + " )";
        } else if (TABLE == MAIN_E){
            create = "CREATE TABLE "
                    + MAIN_E + " ( " + KEY_ID + " INTEGER PRIMARY KEY, " + WEEK
                    + " INTEGER, " + CYCLE + " INTEGER, " + MAIN_EXERCISE + " TEXT, " + WEIGHT + " TEXT, " + DATE_CREATED
                    + " DATETIME, " + NOTES + " TEXT" + " )";
        } else if (TABLE == RM_LOG){
            System.out.println("IM HERE");
            create = "CREATE TABLE "
                    + RM_LOG + " ( " + KEY_ID + " INTEGER PRIMARY KEY, " + MAIN_EXERCISE + " TEXT, " + WEIGHT + " TEXT, " + DATE_CREATED
                    + " DATETIME" + " )";
        }
        return create;
    }

    /*
     * MAIN_E table: KEY_ID|WEEK|CYCLE|MAIN_EXERCISE|WEIGHT|DATE_CREATED|NOTES
     * ASSISTANCE table: KEY_ID|WEEK|CYCLE|MAIN_EXERCISE|A_EXERCISE|WEIGHT|DATE_CREATED
     */
    public void insertExercise(HashMap<String, String> queryValues) {

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        String TABLE = queryValues.get("table");

        values.put(WEEK, queryValues.get(WEEK));
        values.put(CYCLE, queryValues.get(CYCLE));
        values.put(MAIN_EXERCISE, queryValues.get(MAIN_EXERCISE));
        values.put(WEIGHT, queryValues.get(WEIGHT));
        values.put(DATE_CREATED, queryValues.get(DATE_CREATED));
        if (TABLE != ASSISTANCE) {
            values.put(NOTES, queryValues.get(NOTES));
        }
        if (TABLE == ASSISTANCE) {
            values.put(A_EXERCISE, queryValues.get(A_EXERCISE));
        }

        System.out.println("OUT. Adding to the DB: "+values.toString());

        database.insert(TABLE, null, values);

        database.close();

    }

    // Updating/editing an existing exercise row: Week|Cycle|Weight|Date
    // maybe I don't even need this method. What is there to update?
    // This can only be useful after we support accessory exercises and notes
    public int updateExercise(HashMap<String, String> queryValues) {

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        String TABLE = queryValues.get("table");

        values.put(WEEK, queryValues.get(WEEK));
        values.put(CYCLE, queryValues.get(CYCLE));
        values.put(MAIN_EXERCISE, queryValues.get(MAIN_EXERCISE));
        values.put(WEIGHT, queryValues.get(WEIGHT));
        values.put(DATE_CREATED, queryValues.get(DATE_CREATED));
        if (TABLE != ASSISTANCE) {
            values.put(NOTES, queryValues.get(NOTES));
        }
        if (TABLE == ASSISTANCE) {
            values.put(A_EXERCISE, queryValues.get(A_EXERCISE));
        }

        return database.update(TABLE, values,
                KEY_ID + " = ?", new String[]{queryValues.get(KEY_ID)});
    }

    public void deleteExercise(String id, String TABLE) {

        SQLiteDatabase database = this.getWritableDatabase();

        String deleteQuery = "DELETE FROM " + TABLE + " WHERE " + KEY_ID + " = '" + id + "'";

        database.execSQL(deleteQuery);

    }

    void deleteAllData(String TABLE) {
        SQLiteDatabase sdb = this.getWritableDatabase();
        sdb.delete(TABLE, null, null);

    }


    // this is NOT needed since getExerciseInfo takes care of this function.
    // getExerciseInfo can output 1 exercise info as well as more than 1 exercise info
    public ArrayList<HashMap<String, String>> getAllExercises(String TABLE) {

        ArrayList<HashMap<String, String>> exerciseArrayList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM " + TABLE + " ORDER BY " + DATE_CREATED;

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                HashMap<String, String> exerciseMap = new HashMap<String, String>();

                exerciseMap.put(KEY_ID, cursor.getString(0));
                exerciseMap.put(WEEK, cursor.getString(1));
                exerciseMap.put(CYCLE, cursor.getString(2));
                exerciseMap.put(WEIGHT, cursor.getString(3));
                exerciseMap.put(DATE_CREATED, cursor.getString(4));

                exerciseArrayList.add(exerciseMap);

            } while (cursor.moveToNext());

        }

        return exerciseArrayList;

    }


    // pre-condition: TABLE is ALWAYS one of the main 4 exercises
    public List<ExerciseObject> getExerciseInfo(String id, String TABLE) {

        int week, cycle;
        String selectQuery;

        List<ExerciseObject> exerciseList = new ArrayList<ExerciseObject>();
        SQLiteDatabase database = this.getReadableDatabase();

        if (id != "") {
            selectQuery = "SELECT * FROM " + TABLE + " "+
                          "WHERE " + KEY_ID + " = '" + id + "'";
        } else {
            selectQuery = "SELECT * FROM " + TABLE +" "+
                          "WHERE "+WEIGHT+" <> '0' "+
                          "ORDER BY " +CYCLE+" DESC, "+WEEK+" DESC, "+ DATE_CREATED + " DESC";
        }

        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                week = cursor.getInt(cursor.getColumnIndex(WEEK));
                cycle = cursor.getInt(cursor.getColumnIndex(CYCLE));

                ExerciseObject eo = new ExerciseObject();
                eo.setID(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                eo.setWeek(week);
                eo.setCycle(cycle);
                eo.setExercise(cursor.getString(cursor.getColumnIndex(MAIN_EXERCISE)));
                eo.setWeight(cursor.getString(cursor.getColumnIndex(WEIGHT)));
                eo.setDateCreated(cursor.getString(cursor.getColumnIndex(DATE_CREATED)));
                eo.setNotes(cursor.getString(cursor.getColumnIndex(NOTES)));

                String a_selectQuery = "SELECT * FROM " + ASSISTANCE + " WHERE " + WEEK + " = '" + week + "' AND "
                        + CYCLE + " = '" + cycle + "' AND "
                        + MAIN_EXERCISE + " = '" + TABLE + "' ";
                Cursor a_cursor = database.rawQuery(a_selectQuery, null);

                if (a_cursor.moveToFirst()) {
                    do {
                        ExerciseObject ae = new ExerciseObject();

                        ae.setID(a_cursor.getInt(a_cursor.getColumnIndex(KEY_ID)));
                        ae.setWeek(a_cursor.getInt(a_cursor.getColumnIndex(WEEK)));
                        ae.setCycle(a_cursor.getInt(a_cursor.getColumnIndex(CYCLE)));
                        ae.setExercise(a_cursor.getString(a_cursor.getColumnIndex(A_EXERCISE)));
                        ae.setWeight(cursor.getString(a_cursor.getColumnIndex(WEIGHT)));
                        ae.setDateCreated(cursor.getString(a_cursor.getColumnIndex(DATE_CREATED)));
                        ae.setNotes("");

                        eo.assistanceEList.add(ae);
                    } while (a_cursor.moveToNext());
                }
                exerciseList.add(eo);

            } while (cursor.moveToNext());
        }

        return exerciseList;
    }

    public void insertExerciseRepMax(HashMap<String, String> queryValues) {

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String TABLE = queryValues.get("table");

        values.put(MAIN_EXERCISE, queryValues.get(MAIN_EXERCISE));
        values.put(WEIGHT, queryValues.get(WEIGHT));
        values.put(DATE_CREATED, queryValues.get(DATE_CREATED));

        System.out.println("OUT. Adding to the REPMAX DB: "+values.toString());

        database.insert(TABLE, null, values);

        database.close();

    }

    public double getExerciseRepMax(String workout_type) {

        String selectQuery;

        double exercise_repmax=0.0;

        SQLiteDatabase database = this.getReadableDatabase();

        selectQuery = "SELECT * FROM " + RM_LOG + " WHERE " + MAIN_EXERCISE + " = '" + workout_type + "'";

        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
           exercise_repmax = Double.parseDouble(cursor.getString(cursor.getColumnIndex(WEIGHT)));
        }
        return exercise_repmax;
    }

    public int updateExerciseRepMax(HashMap<String, String> queryValues) {

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        String workout_type = queryValues.get(MAIN_EXERCISE);
        String weight = queryValues.get(WEIGHT);
        String date = queryValues.get(DATE_CREATED);

        values.put(MAIN_EXERCISE, workout_type);
        values.put(WEIGHT, weight);
        values.put(DATE_CREATED, date);


        return database.update(RM_LOG, values,
                MAIN_EXERCISE + " = ?", new String[]{workout_type});
    }


    public int[] getLastCycleWeek(String workout_type) {
        String query = "SELECT "+CYCLE+", "+WEEK+
                       " FROM "+MAIN_E+
                       " WHERE "+CYCLE+" = (SELECT MAX("+CYCLE+") "+
                                           "FROM "+MAIN_E+" " +
                                           "WHERE "+MAIN_EXERCISE+" = '"+workout_type+"')"+
                       " AND "+MAIN_EXERCISE+" = '"+workout_type+"'"+
                       " ORDER BY "+WEEK+" DESC LIMIT 1";

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);
        int cycle=0, week=0;
        if (cursor.moveToFirst()) {
            cycle = cursor.getInt(0);
            week = cursor.getInt(1);
        }
        System.out.println("cycle: "+cycle+" week: "+week);
        return new int[]{cycle, week};
    }


}
