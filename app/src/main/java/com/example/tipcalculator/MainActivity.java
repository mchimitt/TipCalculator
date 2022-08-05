package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText bill;
    private EditText tip;
    private TextView bill_result;
    private TextView tip_result;

    private EditText guest_number;
    private TextView split_tip;
    private TextView split_bill;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bill = findViewById(R.id.bill_edit);
        tip = findViewById(R.id.tip_edit);
        bill_result = findViewById(R.id.final_bill);
        tip_result = findViewById(R.id.tip_money);

        guest_number = findViewById(R.id.guest_number_edit);
        split_tip = findViewById(R.id.split_tip_result);
        split_bill = findViewById(R.id.split_bill_result);

    }

    public void CalculateTip(View view) {
        if(bill.getText().toString().equals("") || tip.getText().toString().equals("")){
            Toast.makeText(this, "Please enter the bill and the tip", Toast.LENGTH_LONG).show();
            bill_result.setText("0.00");
            tip_result.setText("0.00");

        } else {
            String bill_money_str = bill.getText().toString();
            String tip_percent_str = tip.getText().toString();

            float bill_money = Float.parseFloat(bill_money_str);
            float tip_percent = Float.parseFloat(tip_percent_str) / 100;

            float final_tip_num = bill_money * tip_percent;
            float final_bill_num = bill_money + final_tip_num;

            String final_tip = String.format("%.2f", final_tip_num);
            String final_bill = String.format("%.2f", final_bill_num);

            bill_result.setText(final_bill);
            tip_result.setText(final_tip);
        }
    }

    public void SplitTip(View view) {
        if(guest_number.getText().toString().equals("")){
            Toast.makeText(this, "Please enter the number of guests splitting the bill", Toast.LENGTH_LONG).show();
            split_tip.setText("0.00 per person");
            split_bill.setText("0.00 per person");
        }
        else{
            float guests = Float.parseFloat(guest_number.getText().toString());
            float bill = Float.parseFloat(bill_result.getText().toString());
            float tip = Float.parseFloat(tip_result.getText().toString());

            float tip_res = tip / guests;
            float bill_res = bill / guests;

            String tip_res_str = String.format("%.2f", tip_res);
            String bill_res_str = String.format("%.2f", bill_res);

            split_tip.setText(tip_res_str + " per person");
            split_bill.setText(bill_res_str + " per person");
        }
    }
}