package com.hanlu.calculator;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.support.v7.app.AlertDialog.*;

public class MainActivity extends AppCompatActivity {

    private EditText input1;
    private EditText input2;
    private TextView output;
    private Button add;
    private Button minus;
    private Button times;
    private Button divide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input1 = findViewById(R.id.input1);
        input2 = findViewById(R.id.input2);
        output = findViewById(R.id.output);
        add = findViewById(R.id.add);
        minus = findViewById(R.id.subtract);
        times = findViewById(R.id.multiple);
        divide = findViewById(R.id.divide);



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s_input1 = input1.getText().toString();
                String s_input2 = input2.getText().toString();
                double num = Double.parseDouble(s_input1) + Double.parseDouble(s_input2);
                String result = "" + num;
                output.setText(result);
                makeMessage(result);
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s_input1 = input1.getText().toString();
                String s_input2 = input2.getText().toString();
                double num = Double.parseDouble(s_input1) - Double.parseDouble(s_input2);
                String result = "" + num;
                output.setText(result);
                makeMessage(result);
            }
        });

        times.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s_input1 = input1.getText().toString();
                String s_input2 = input2.getText().toString();
                double num = Double.parseDouble(s_input1) * Double.parseDouble(s_input2);
                String result = "" + num;
                output.setText(result);
                makeMessage(result);
            }
        });

        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s_input1 = input1.getText().toString();
                String s_input2 = input2.getText().toString();
                if(s_input2.equals("0")) {
                    String error = "Error";
                    output.setText(error);
                    makeMessage(error);
                }
                else {
                    double num = Double.parseDouble(s_input1) / Double.parseDouble(s_input2);
                    String result = "" + num;
                    output.setText(result);
                    makeMessage(result);

                }

            }
        });


    }

    public void makeMessage(final String s)
    {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("answer is " + s);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        input1.setText("0");
                        input2.setText("0");
                        output.setText("0");
                    }
                });


        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}
