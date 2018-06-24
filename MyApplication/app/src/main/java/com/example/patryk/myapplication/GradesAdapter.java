package com.example.patryk.myapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

public class GradesAdapter extends ArrayAdapter<GradeModel>
{
    //przechowujemy referencjÄ™ do listy ocen
    private List<GradeModel> gradesList;
    private Activity context;
    public GradesAdapter(Activity context, List<GradeModel> gradesList)
    {
        super(context, R.layout.grade, gradesList);
        //ustawienie wartoĹ›ci pĂłl
        this.gradesList=gradesList;
        this.context=context;
    }
    //tworzenie nowego wiersza
    @Override
    public View getView(int numberOfRow, View oldView, ViewGroup parent)
    {
        final GradeModel currentGrade= gradesList.get(numberOfRow); //obiekt przechowujący dane o aktualnej ocenie
        View view = null;
        //tworzenie nowego wiersza
        if (oldView == null)
        {
            LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.grade, null);  //utworzenie nowego wiersza widoku

            //wybranie konkretnego przycisku radiowego musi zmieniaÄ‡ dane w modelu
            RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
            TextView gradeName = (TextView)view.findViewById(R.id.gradeName);
            gradeName.setText(currentGrade.getName()); //Przypisanie nazwy pobranej z modelu do etykiety
            radioGroup.check(R.id.grade2);
            //currentGrade.setGrade(2);
            radioGroup.setOnCheckedChangeListener(
                    new RadioGroup.OnCheckedChangeListener()
                    {
                        @Override
                        public void onCheckedChanged(RadioGroup group, //referencja do grupy
                                                     //przyciskĂłw
                                                     int checkedId) //identyfikator wybranego
                        //przycisku
                        {
                            // 1) odczytanie z etykiety, ktĂłry obiekt modelu przechowuje dane o
                            //zmienionej ocenie
                            if (checkedId == R.id.grade2)
                            {
                                currentGrade.setGrade(2);
                            }
                            if (checkedId == R.id.grade3)
                            {
                                currentGrade.setGrade(3);
                            }
                            if (checkedId == R.id.grade4)
                            {
                                currentGrade.setGrade(4);
                            }
                            if (checkedId == R.id.grade5)
                            {
                                currentGrade.setGrade(5);
                            }

                            // 2) zapisanie zmienionej oceny

                        }
                    }
            );
            //powiÄ…zanie grupy przyciskĂłw z obiektem w modelu
        }
        //aktualizacja istniejÄ…cego wiersza
        else
        {
            view = oldView;
            RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
            //powiÄ…zanie grupy przyciskĂłw z obiektem w modelu
             ///grupaOceny.setTag(gradesList.get(numerWiersza));

        }

        TextView gradeName = (TextView) view.findViewById(R.id.gradeName);
        //ustawienie tekstu etykiety na podstawie modelu
        gradeName.setText(currentGrade.getName());

        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
        //zaznaczenie odpowiedniego przycisku na podtawie modelu
        if(currentGrade.getGrade()==2) {
            radioGroup.check(R.id.grade2);
        }
        if(currentGrade.getGrade()==3) {
            radioGroup.check(R.id.grade3);
        }
        if(currentGrade.getGrade()==4) {
            radioGroup.check(R.id.grade4);
        }
        if(currentGrade.getGrade()==5) {
            radioGroup.check(R.id.grade5);
        }
        //zwrĂłcenie nowego lub zaktualizowanego wiersza listy
        return view;
    }
}

