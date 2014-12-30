package sleeping_vityaz.fivethreeone_trainer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import sleeping_vityaz.fivethreeone_trainer.exercise.object.ExerciseObject;

public class DBTools extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database name
    private static final String DATABASE_NAME = "workout_log";

    // Table Names
    private static final String DEADLIFT = "deadlift";
    private static final String BENCH = "bench";
    private static final String SQUAT = "squat";
    private static final String PRESS = "press";
    private static final String ASSISTANCE = "assistance";

    // Common column names
    private static final String KEY_ID = "exerciseID";
    private static final String WEEK = "week_num";
    private static final String CYCLE = "cycle_num";
    private static final String WEIGHT = "weight_num";
    private static final String DATE_CREATED = "date_created";
    private static final String NOTES = "notes";
    private static final String A_EXERCISE = "a_exercise";
    private static final String MAIN_EXERCISE = "m_exercise";

    public DBTools(Context applicationContext){
        super(applicationContext, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase database) {

        database.execSQL(createTable(DEADLIFT));
        database.execSQL(createTable(BENCH));
        database.execSQL(createTable(SQUAT));
        database.execSQL(createTable(PRESS));
        database.execSQL(createTable(ASSISTANCE));

    }

    /* This will alter existing table without erasing any of the user's data
    *  Loops through each database version and  necessary columns*/
    @Override
    public void onUpgrade(final SQLiteDatabase database, final int oldVersion, final int newVersion) {

        int upgradeTo = oldVersion + 1;
        while (upgradeTo <= newVersion)
        {
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

    // Table Create Statement
    public String createTable(String TABLE){
        String create;

        if (TABLE == ASSISTANCE) {
            create = "CREATE TABLE "
                    + TABLE + " ( " + KEY_ID + " INTEGER PRIMARY KEY, " + WEEK
                    + " INTEGER, " + CYCLE + " INTEGER, " + MAIN_EXERCISE + " TEXT, "+ A_EXERCISE + " TEXT, "+WEIGHT + " TEXT, " + DATE_CREATED
                    + " DATETIME" + " )";
        }else{
            create = "CREATE TABLE "
                    + TABLE + " ( " + KEY_ID + " INTEGER PRIMARY KEY, " + WEEK
                    + " INTEGER, " + CYCLE + " INTEGER, " + WEIGHT + " TEXT, " + DATE_CREATED
                    + " DATETIME, " + NOTES + " TEXT"+" )";
        }
            return create;
    }

    public void insertExercise(HashMap<String, String> queryValues){

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        String TABLE = queryValues.get("table");

        values.put(WEEK, queryValues.get(WEEK));
        values.put(CYCLE, queryValues.get(CYCLE));
        values.put(WEIGHT, queryValues.get(WEIGHT));
        values.put(DATE_CREATED, queryValues.get(DATE_CREATED));
        if (TABLE != ASSISTANCE){values.put(NOTES, queryValues.get(NOTES));}
        if (TABLE == ASSISTANCE){values.put(A_EXERCISE, queryValues.get(A_EXERCISE));}

        database.insert(TABLE, null, values);

        database.close();

    }

    // Updating/editing an existing exercise row: Week|Cycle|Weight|Date
    // maybe I don't even need this method. What is there to update?
    // This can only be useful after we support accessory exercises and notes
    public int updateExercise(HashMap<String, String> queryValues){

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        String TABLE = queryValues.get("table");

        values.put(WEEK, queryValues.get(WEEK));
        values.put(CYCLE, queryValues.get(CYCLE));
        values.put(WEIGHT, queryValues.get(WEIGHT));
        values.put(DATE_CREATED, queryValues.get(DATE_CREATED));
        if (TABLE != ASSISTANCE){ values.put(NOTES, queryValues.get(NOTES));}
        if (TABLE == ASSISTANCE){values.put(A_EXERCISE, queryValues.get(A_EXERCISE));}

        return database.update(TABLE, values,
                KEY_ID + " = ?", new String[] {queryValues.get(KEY_ID) });
    }

    public void deleteExercise(String id, String TABLE){

        SQLiteDatabase database = this.getWritableDatabase();

        String deleteQuery = "DELETE FROM " + TABLE +" WHERE "+KEY_ID + " = '" + id + "'";

        database.execSQL(deleteQuery);

    }

    void deleteAllData(String TABLE)
    {
        SQLiteDatabase sdb= this.getWritableDatabase();
        sdb.delete(TABLE, null, null);

    }


    // this is NOT needed since getExerciseInfo takes care of this function.
    // getExerciseInfo can output 1 exercise info as well as more than 1 exercise info
    public ArrayList<HashMap<String, String>> getAllExercises(String TABLE){

        ArrayList<HashMap<String, String>> exerciseArrayList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM "+TABLE+" ORDER BY "+DATE_CREATED;

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){

            do{

                HashMap<String, String> exerciseMap = new HashMap<String, String>();

                exerciseMap.put(KEY_ID, cursor.getString(0));
                exerciseMap.put(WEEK, cursor.getString(1));
                exerciseMap.put(CYCLE, cursor.getString(2));
                exerciseMap.put(WEIGHT, cursor.getString(3));
                exerciseMap.put(DATE_CREATED, cursor.getString(4));

                exerciseArrayList.add(exerciseMap);

            }while(cursor.moveToNext());

        }

        return exerciseArrayList;

    }


    // pre-condition: TABLE is ALWAYS one of the main 4 exercises
    public List<ExerciseObject> getExerciseInfo(String id, String TABLE){

        int week, cycle;
        String selectQuery;

        List<ExerciseObject> exerciseList = new ArrayList<ExerciseObject>();
        SQLiteDatabase database = this.getReadableDatabase();

        if (id!="") {
             selectQuery = "SELECT * FROM " + TABLE + " WHERE " + KEY_ID + " ='" + id + "'";
        }else{
            selectQuery = "SELECT * FROM " + TABLE + "ORDER BY "+DATE_CREATED;
        }
        Cursor cursor = database.rawQuery(selectQuery, null);
        week = cursor.getInt(cursor.getColumnIndex(WEEK));
        cycle = cursor.getInt(cursor.getColumnIndex(CYCLE));

        String a_selectQuery = "SELECT * FROM "+ASSISTANCE+" WHERE "+WEEK+" ='" + week + "'"
                                               +CYCLE+" ='" + cycle + "'"
                                               +MAIN_EXERCISE+" ='" + TABLE + "'";
        Cursor a_cursor = database.rawQuery(a_selectQuery, null);

        if(cursor.moveToFirst()){

            do{
                ExerciseObject eo = new ExerciseObject();
                eo.setID(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                eo.setWeek(week);
                eo.setCycle(cycle);
                eo.setExercise(TABLE);
                eo.setWeight(cursor.getString(cursor.getColumnIndex(WEIGHT)));
                eo.setDateCreated(cursor.getString(cursor.getColumnIndex(DATE_CREATED)));
                eo.setNotes(cursor.getString(cursor.getColumnIndex(NOTES)));

                if(a_cursor.moveToFirst()) {
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

            }while(cursor.moveToNext());
        }
        return exerciseList;
    }
}
