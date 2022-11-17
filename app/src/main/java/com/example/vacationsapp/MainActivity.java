package com.example.vacationsapp;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    String[] arrDestinations = {"Cartagena","San Andrés","Santa Marta"};
    String selDestination;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText fullname = findViewById(R.id.fullname);
        EditText date = findViewById(R.id.date);
        Spinner destinations = findViewById(R.id.destinations);
        EditText numberOfPeople = findViewById(R.id.numberOfPeople);
        EditText numberOfDays = findViewById(R.id.numberOfDays);
        RadioButton cityTour = findViewById(R.id.cityTour);
        RadioButton nightClub = findViewById(R.id.nightclub);
        TextView totalplan = findViewById(R.id.totalplan);
        TextView totaldiscount = findViewById(R.id.totaldiscount);
        Button calculate = findViewById(R.id.calculate);
        Button clean = findViewById(R.id.clean);


        ArrayAdapter adDestinations = new ArrayAdapter(this, android.R.layout.simple_list_item_checked,arrDestinations);
        destinations.setAdapter(adDestinations);

        destinations.setOnItemSelectedListener(this);


        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!fullname.getText().toString().isEmpty()
                    && !date.getText().toString().isEmpty()
                       && !numberOfPeople.getText().toString().isEmpty()
                        && !numberOfDays.getText().toString().isEmpty()){
                    int xnumberOfPeople = parseInt(numberOfPeople.getText().toString());
                    int xnumberOfDays = parseInt(numberOfDays.getText().toString());
                    if(xnumberOfPeople >= 1 && xnumberOfPeople <=10){
                        if(xnumberOfDays >=2 && xnumberOfDays <= 5){

                        long cost = 0;
                        switch(selDestination){
                            case "Cartagena":
                                cost = 250000;
                                break;
                            case "San Andrés":
                                cost = 200000;
                                break;
                            case "Santa Marta":
                                cost= 230000;
                                break;
                        }
                        double discountAmount = 0;
                        if(xnumberOfPeople >=8){
                            discountAmount = 0.10;
                        }

                        long xadditionals = 0;
                        if(cityTour.isChecked()){
                            xadditionals = 100000;
                        }
                        if(nightClub.isChecked()){
                            xadditionals = 80000;
                        }



                        double xtotal = (cost* xnumberOfDays * xnumberOfPeople)+(xadditionals*xnumberOfPeople);
                        double xdiscount = xtotal * discountAmount;
                        double xtotalDiscount = xtotal - xdiscount;

                            DecimalFormat numberFormat = new DecimalFormat("###,###,###,###");

                            totaldiscount.setText(numberFormat.format(xdiscount));
                            totalplan.setText(numberFormat.format(xtotalDiscount));

                        }else{
                            Toast.makeText(getApplicationContext(),"The amount of days should be between 2 and 5", Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(getApplicationContext(),"The amount of people should be between 1 and 10",Toast.LENGTH_SHORT).show();
                    }


                }else{
                    Toast.makeText(getApplicationContext(),"Please enter your fullname, date, amount of people and amount of days",Toast.LENGTH_SHORT).show();
                }


            }
        });


        clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullname.setText("");
                date.setText("");
                numberOfPeople.setText("");
                numberOfDays.setText("");
                cityTour.setChecked(true);
                totalplan.setText("");
                totaldiscount.setText("");
                fullname.requestFocus();
                destinations.setSelection(0);
            }
        });




    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selDestination = arrDestinations[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}