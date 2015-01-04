package sleeping_vityaz.fivethreeone_trainer;

public class Calculations {
    private static double getWorkingWeight (double percentage, double repmax){
        return percentage*repmax;
    }

    public static double calculateRepMax(double weight, int reps){
        return (weight*reps*0.0333+weight);
    }

    public static double getWeightWarmUpSetOne (double repmax){
        return Calculations.getWorkingWeight(0.40, repmax);
    }
    public static double getWeightWarmUpSetTwo (double repmax){
        return Calculations.getWorkingWeight(0.50, repmax);
    }
    public static double getWeightWarmUpSetThree (double repmax){
        return Calculations.getWorkingWeight(0.60, repmax);
    }
    public static double getWeightWeekOneSetOne (double repmax){
        return Calculations.getWorkingWeight(0.65, repmax);
    }
    public static double getWeightWeekOneSetTwo (double repmax){
        return Calculations.getWorkingWeight(0.75, repmax);
    }
    public static double getWeightWeekOneSetThree (double repmax){
        return Calculations.getWorkingWeight(0.85, repmax);
    }
    public static double getWeightWeekTwoSetOne (double repmax){
        return Calculations.getWorkingWeight(0.70, repmax);
    }
    public static double getWeightWeekTwoSetTwo (double repmax){
        return Calculations.getWorkingWeight(0.80, repmax);
    }
    public static double getWeightWeekTwoSetThree (double repmax){
        return Calculations.getWorkingWeight(0.90, repmax);
    }
    public static double getWeightWeekThreeSetOne (double repmax){
        return Calculations.getWorkingWeight(0.75, repmax);
    }
    public static double getWeightWeekThreeSetTwo (double repmax){
        return Calculations.getWorkingWeight(0.85, repmax);
    }
    public static double getWeightWeekThreeSetThree (double repmax){
        return Calculations.getWorkingWeight(0.95, repmax);
    }
    public static double getWeightWeekFourSetOne (double repmax){
        return Calculations.getWorkingWeight(0.40, repmax);
    }
    public static double getWeightWeekFourSetTwo (double repmax){
        return Calculations.getWorkingWeight(0.50, repmax);
    }
    public static double getWeightWeekFourSetThree (double repmax){
        return Calculations.getWorkingWeight(0.60, repmax);
    }
}
