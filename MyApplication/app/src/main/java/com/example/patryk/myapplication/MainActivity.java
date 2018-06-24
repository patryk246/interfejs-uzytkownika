package com.example.patryk.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText e1,e2,e3;
    Button button, good, bad;   //przyciski(przejście do okna z ocenami, zakończenie pozytywne(średnia>=3) i negatywne(średnia<3))
    String name;
    int numberOfGrades;
    boolean isNameCorrect=false,isSurnameCorrect=false, isNumberOfGradesCorrect=false,isFieldValidated=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        setWindow();
                    }
                }
        );

        good=(Button)findViewById(R.id.button2);
        good.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        goodFinish();
                    }
                }
        );

        bad=(Button)findViewById(R.id.button3);
        bad.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        badFinish();
                    }
                }
        );

        e1=(EditText)findViewById(R.id.editText1);
       // e1.setOnFocusChangeListener(
                //new View.OnFocusChangeListener(){
                  //  public void onFocusChange(View v, boolean hasFocus){
                     //   if(!hasFocus){
                        //    validateText(e1);
                          //  if(isTextValidated==true){
                              //  isNameCorrect=true;
                          //  }
                            //else{
                              //  isNameCorrect=false;
                          //  }
                         //  showButton();
                       // }
                   // }
               // }
       // );

        e1.addTextChangedListener(
                new TextWatcher() {

                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        validateText(e1);
                        if(isFieldValidated==true){
                            isNameCorrect=true;
                        }
                        else{
                            isNameCorrect=false;
                        }
                        showButton();
                    }
                }
        );

        e2=(EditText)findViewById(R.id.editText2);
    /*    e2.setOnFocusChangeListener(
                new View.OnFocusChangeListener(){
                    public void onFocusChange(View v, boolean hasFocus){
                        if(!hasFocus){
                            validateText(e2);
                            if(isTextValidated==true){
                                isSurnameCorrect=true;
                            }
                            else{
                                isSurnameCorrect=false;
                            }
                            showButton();
                        }
                    }
                }
        );*/

    e2.addTextChangedListener(
            new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    validateText(e2);
                    if(isFieldValidated==true){
                        isSurnameCorrect=true;
                    }
                    else{
                        isSurnameCorrect=false;
                    }
                    showButton();
                }
            }
    );

       e3=(EditText)findViewById(R.id.editText3);
        /* e3.setOnFocusChangeListener(
                new View.OnFocusChangeListener(){
                    public void onFocusChange(View v, boolean hasFocus){
                        if(!hasFocus){
                            validateNumber(e3);
                            showButton();
                        }
                    }
                }
        );*/

        e3.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        validateNumber(e3);
                        showButton();
                    }
                }
        );

    }
    protected void showButton() {
        if (isNameCorrect && isSurnameCorrect && isNumberOfGradesCorrect) {
            button.setVisibility(View.VISIBLE);
        } else {
            button.setVisibility(View.INVISIBLE);
        }
    }


    protected void setWindow(){
        Intent intent = new Intent(MainActivity.this, Grades.class);    //utworzenie obiektu do nowej aktywności
        intent.putExtra("liczbaOcen", numberOfGrades);  //przekazanie liczby ocen do aktywności
        startActivityForResult(intent, 69); //przejście do aktywności
    }

    protected void validateText(EditText e){
        isFieldValidated=false;
        name=e.getText().toString();
        if(name.equals("")) {
            e.setError("Pole nie moze byc puste!");
            isFieldValidated=false;
        }
        else{
            if(!name.matches("^[A-Z]{1}[a-ząęłńśćźżó-]{2,15}\\s?$")){
                e.setError("Wpisz poprawna wartosc");
                isFieldValidated=false;
            }
            else {
                isFieldValidated = true;
            }
        }

    }

    protected void validateNumber(EditText e) {
        try {
            numberOfGrades = Integer.parseInt(e.getText().toString());
            if (numberOfGrades < 5 || numberOfGrades > 15) {
                e.setError("Wprowadz liczbe ocen miedzy 5 a 15");
                isNumberOfGradesCorrect = false;
            } else {
                isNumberOfGradesCorrect = true;
            }
        } catch (NumberFormatException e1) {
            e.setError("Wpisz poprawna wartosc");
            isNumberOfGradesCorrect = false;
        }
    }

    protected void onActivityResult(int kodZadania, int kodWyniku, Intent dane) //odebranie danych przekazanych przez wywołaną
    {                                                                           //aktywność
        super.onActivityResult(kodZadania, kodWyniku, dane);
        if (kodWyniku==RESULT_OK && kodZadania==69)
        {
            Bundle gradesAmount=dane.getExtras();
            float average=gradesAmount.getFloat("average");

            if(average>=3){
                good.setVisibility(View.VISIBLE);
            }
            else{
                bad.setVisibility(View.VISIBLE);
            }

            e1.setEnabled(false);
            e2.setEnabled(false);
            e3.setEnabled(false);

            TextView averageText=(TextView)findViewById(R.id.average);
            String text=Float.toString(average);
            averageText.setText(text);
            averageText.setVisibility(View.VISIBLE);
        }

    }

    protected void goodFinish(){    //pozytywne zakonczenie
        Toast.makeText(this, "Gratulacje! Otrzymujesz zaliczenie!", Toast.LENGTH_LONG).show();
        finish();
    }

    protected void badFinish(){ //negatywne zakonczenie
        Toast.makeText(this, "Wysylam podanie o zaliczenie warunkowe", Toast.LENGTH_LONG).show();
        finish();
    }
}
