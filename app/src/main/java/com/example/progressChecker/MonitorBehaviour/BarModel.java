package com.example.progressChecker.MonitorBehaviour;

public class BarModel {
    String ID;
    String FinalScore;
    String Category;

    public BarModel(String id, String category, String finalScore){
        this.ID = id;
        this.Category = category;
        this.FinalScore = finalScore;
    }

   public String getId(){
        return ID;
   }
   public String getCategory(){
        return Category;
   }

   public  String getFinalScore(){
        return FinalScore;
   }
}
