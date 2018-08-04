package com.jojo.metroapp.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.jojo.metroapp.fragment.HomeFragment;

public class LoginActivity extends AppCompatActivity {

    // Firebase
    FirebaseDatabase database;
    DatabaseReference users;

    EditText edtEmail, edtPassword;
    Button btnMasuk;
    TextView tvToSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Firebase
        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");

        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);

        btnMasuk = (Button) findViewById(R.id.btnMasuk);

        tvToSignUp = (TextView) findViewById(R.id.tvToSignUp);

        tvToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(l);
            }
        });

        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn(edtEmail.getText().toString(),
                        edtPassword.getText().toString());
            }
        });
    }

    private void signIn(final String email, final String password) {
        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(email).exists()){
                    if (email.isEmpty()){
                        User login = dataSnapshot.child(email).getValue(User.class);
                        if (login.getPassword().equals(password)){
                            Toast.makeText(LoginActivity.this, "Sukses Login", Toast.LENGTH_SHORT).show();
                            Intent l = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(l);
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Password salah", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(LoginActivity.this, "Username belum terdaftar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
