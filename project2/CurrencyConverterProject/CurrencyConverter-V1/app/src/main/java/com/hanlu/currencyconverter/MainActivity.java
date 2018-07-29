package com.hanlu.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText rmbField;
    private EditText usdField;
    private Button convertButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rmbField = findViewById(R.id.rmbInput);
        usdField = findViewById(R.id.dollarInput);
        convertButton = findViewById(R.id.convertButton);
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double usd = 0.0;
                double rmb = 0.0;
                String s_usd = usdField.getText().toString();
                String s_rmb = rmbField.getText().toString();
                if (s_usd.matches("\\d+(\\.\\d+)?")) {
                    usd = Double.parseDouble(s_usd);
                }
                if (s_rmb.matches("\\d+(\\.\\d+)?")) {
                    rmb = Double.parseDouble(s_rmb);
                }
                if (usd != 0 && rmb != 0) {
                    System.out.println("Error: At least one value must be 0.");
                    usd = 0;
                    rmb = 0;
                } else if (usd != 0) {
                    rmb = (double) Math.round((usd * 6.63) * 1000) / 1000;
                } else if (rmb != 0) {
                    usd =  (double) Math.round((rmb / 6.63) * 1000) / 1000;
                }
                s_usd = "$" + usd;
                s_rmb = "￥" + rmb;

                usdField.setText("");
                rmbField.setText("");

                usdField.setHint(s_usd);
                rmbField.setHint(s_rmb);
            }
        });
    }
}
