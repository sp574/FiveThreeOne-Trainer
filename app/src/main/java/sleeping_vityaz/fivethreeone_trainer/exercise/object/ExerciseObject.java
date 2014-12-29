package sleeping_vityaz.fivethreeone_trainer.exercise.object;

import java.lang.reflect.Array;
import java.sql.Date;
import java.util.List;

/**
 * Created by naja-ox on 12/27/14.
 */
public class ExerciseObject {

    public int exerciseID;
    public int week;
    public int cycle;
    public String exercise;
    public String weight;
    public String date_created;
    public String notes;
    public List<ExerciseObject> assistanceEList;

    public ExerciseObject(){
    }

    public ExerciseObject(int exerciseID, int week,
                          int cycle, String exercise, String weight,
                          String date_created, String notes,
                          List<ExerciseObject> assistanceEList){
        this.exerciseID = exerciseID;
        this.week = week;
        this.cycle = cycle;
        this.exercise = exercise;
        this.weight = weight;
        this.date_created = date_created;
        this.notes = notes;
        this.assistanceEList = assistanceEList;
    }
    public void setID(int id){
        this.exerciseID = id;
    }
    public void setWeek(int week){
        this.week = week;
    }
    public void setCycle(int cycle){
        this.cycle = cycle;
    }
    public void setExercise(String exercise){
        this.exercise = exercise;
    }
    public void setWeight(String weight){
        this.weight = weight;
    }
    public void setDateCreated(String date_created){
        this.date_created = date_created;
    }
    public void setNotes(String notes){
        this.notes = notes;
    }
    public void setAEList(List<ExerciseObject> assistanceEList){
        this.assistanceEList = assistanceEList;
    }

    public int getID(){
        return exerciseID;
    }
    public int getWeek(){
        return week;
    }
    public int getCycle(){
        return cycle;
    }
    public String getExercise(){
        return exercise;
    }
    public String getWeight(){
        return weight;
    }
    public String getDateCreated(){
        return date_created;
    }
    public String getNotes(){
        return notes;
    }
    public List<ExerciseObject> getAEList(){
        return assistanceEList;
    }
}
