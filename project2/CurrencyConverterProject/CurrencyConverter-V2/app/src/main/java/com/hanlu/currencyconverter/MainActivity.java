package com.hanlu.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText rmbField;
    private EditText usdField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rmbField = findViewById(R.id.rmbInput);
        rmbField.setTag("1");
        usdField = findViewById(R.id.dollarInput);
        usdField.setTag("1");

        rmbField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                usdField.setTag("0");
                if (rmbField.getTag() == "1")
                    updatedText(editable, "￥", "$", 1 / 6.63, usdField);
                usdField.setTag("1");
            }

        });
        usdField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                rmbField.setTag("0");
                if (usdField.getTag() == "1")
                    updatedText(editable, "$", "￥", 6.63, rmbField);
                rmbField.setTag("0");
            }
        });
    }


    public void updatedText(Editable editable, String currency, String nextCurrency, double multiplier, EditText Field) {
        //System.out.println("AFTER: " + editable);
        String input = editable.toString();
        input = input.replace(currency, "");
        double num;
        try {
            num = Double.parseDouble(input) * multiplier;
            if (num < 0)
                num = 0;
        } catch (Exception ex) {
            num = 0;
        }
        DecimalFormat df = new DecimalFormat("#.00");
        input = nextCurrency + " " + df.format(num);
        Field.setText(input);
        //Field.setTag("1");

    }
}
