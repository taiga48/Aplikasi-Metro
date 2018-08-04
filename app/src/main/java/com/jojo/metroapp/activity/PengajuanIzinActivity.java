package com.jojo.metroapp.activity;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.jojo.metroapp.R;

import java.util.Calendar;

public class PengajuanIzinActivity extends AppCompatActivity {

    EditText edtAlasan, edtDari, edtSampai;
    Button btnDate, btnDate2;

    Calendar calendar;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengajuan_izin);

        edtAlasan = (EditText) findViewById(R.id.edtAlasan);
        edtDari = (EditText) findViewById(R.id.edtDari);
        edtSampai = (EditText) findViewById(R.id.edtSampai);

        btnDate = (Button) findViewById(R.id.btnDate);
        btnDate2 = (Button) findViewById(R.id.btnDate2);


    }
}
