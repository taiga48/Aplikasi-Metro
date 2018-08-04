package com.jojo.metroapp.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jojo.metroapp.Model.User;
import com.jojo.metroapp.R;

public class SignUpActivity extends AppCompatActivity {

    // Firebase
    FirebaseDatabase database;
    DatabaseReference users;

    EditText edtUsername, edtEmail, edtPassword;
    Button btnDaftar;
    TextView tvToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Firebase
        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");

        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);

        btnDaftar = (Button) findViewById(R.id.btnDaftar);

        tvToLogin = (TextView) findViewById(R.id.tvToLogin);

        tvToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(s);
            }
        });

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              final User user = new User(edtUsername.getText().toString(),
                      edtEmail.getText().toString(),
                      edtPassword.getText().toString());

              users.addListenerForSingleValueEvent(new ValueEventListener() {
                  @Override
                  public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                      if (dataSnapshot.child(user.getUsername()).exists())
                          Toast.makeText(SignUpActivity.this, "Username sudah terdaftar", Toast.LENGTH_SHORT).show();
                      else {
                          users.child(user.getUsername()).setValue(user);
                          Toast.makeText(SignUpActivity.this, "Sukses terdaftar!", Toast.LENGTH_SHORT).show();
                      }
                  }

                  @Override
                  public void onCancelled(@NonNull DatabaseError databaseError) {

                  }
              });
            }
        });



    }
}
