package com.example.patryk.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Grades extends AppCompatActivity {

    private ArrayList grades=new ArrayList<GradeModel>();   //obiekt ArrayList przechowujący listę ocen
    int numberOfGrades;
    Button finish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);

        finish=(Button) findViewById(R.id.finish);
        finish.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        backToMain();
                    }
                }
        );

        Bundle mainData=getIntent().getExtras();    //odebranie danych z MainActivity
        numberOfGrades=mainData.getInt("liczbaOcen");
        for(int i=1;i<=numberOfGrades;i++){                //wypełnianie ArrayList danymi
            grades.add(new GradeModel("ocena "+i));
        }
    GradesAdapter gradesAdapter=new GradesAdapter(this, grades);    //ustawienie adaptera
        ListView listView = (ListView)findViewById(R.id.listView);          //czyli połączenie danych z widokiem
        listView.setAdapter(gradesAdapter);


    }

public void backToMain(){       //powrót do MainActivity
        if(GradeModel.allChecked(grades)){
            Bundle gradesAmount=new Bundle();
            gradesAmount.putFloat("average", GradeModel.average(grades));
            Intent returnMainActivity= new Intent();
            returnMainActivity.putExtras(gradesAmount); //wysłanie do MainActivity średniej
            setResult(RESULT_OK, returnMainActivity);
            finish();
        }
        else{
            Toast.makeText(Grades.this, "Wypelnij wszystkie pola!", Toast.LENGTH_SHORT).show();
        }
}
}
