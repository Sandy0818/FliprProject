package com.example.fliprproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonlogin;
    private TextView textViewSignup;
    ProgressBar progressbar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            //that means user is already logged in
            //so close this activity
            finish();

            //and open profile activity
            //startActivity(new Intent(getApplicationContext(), home.class));
        }

        //initializing views
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        buttonlogin = (Button) findViewById(R.id.buttonlogin);
        textViewSignup = (TextView) findViewById(R.id.textViewSignup);
        //progressDialog = new ProgressDialog(this);

        //attaching listener to button
        //buttonSignup.setOnClickListener(this);
        progressbar = findViewById(R.id.progressBar2);
        textViewSignup.setOnClickListener(this);

        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                Log.d("LOGIN", email + " " + password);

                if (TextUtils.isEmpty(email)) {
                    editTextEmail.setError("Please enter email");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    editTextPassword.setError("Please enter password");
                    return;
                }
                if(password.length()<6)
                {
                    editTextPassword.setError("password must be >=6 characters");
                    return;           }
                progressbar.setVisibility(View.VISIBLE);

                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"User Logged in",Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(getApplicationContext(),home.class));
                        }
                        else{
                            Toast.makeText(MainActivity.this,"Error!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });

    }

    @Override
    public void onClick(View view) {
        if(view == textViewSignup)
        {
            startActivity(new Intent(this, RegisterUser.class));
        }
    }
}
