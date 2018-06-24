package com.example.patryk.myapplication;

import java.util.List;


public class GradeModel {
    private String name;    //nazwa oceny
    private int value;  //wartoćś oceny

    public GradeModel(String name){
        this.name=name;
    }   //metody dostępowe

    public void setGrade(int value){
        this.value=value;
    }

    public String getName(){
        return this.name;
    }

    public int getGrade(){
        return this.value;
    }

    public static boolean allChecked(List<GradeModel> grades){  //sprawdzenie czy wszystkie oceny mają przypisaną wartość
        int amount=grades.size();
        for(int i=0;i<amount;i++){
            GradeModel currentGrade=(GradeModel) grades.get(i);
            if(currentGrade.getGrade()==0){
                return false;
            }
        }
        return true;
    }

    public static float average(List<GradeModel> grades){   //obliczanie średniej
        int amount=grades.size();
        int sum=0;
        for(int i=0;i<amount;i++){
            GradeModel currentGrade=(GradeModel)grades.get(i);
            sum=sum+currentGrade.getGrade();
        }
        return (float)sum/amount;
    }

}
