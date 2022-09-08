package com.example.progressChecker.ViewResults;

public class PieModel {
    String Category;
    String FinalScore;

    public PieModel(String category, String finalScore){
        this.Category = category;
        this.FinalScore = finalScore;
    }

   public String getCategory(){
        return Category;
   }

   public  String getFinalScore(){
        return FinalScore;
   }
}
