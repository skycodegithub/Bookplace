package com.example.bookplace;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SecondActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText SignupEmail,signupPassword;
    private Button SignupButton;
    private TextView loginRedirectText;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        auth = FirebaseAuth.getInstance();
        SignupEmail = findViewById(R.id.Signup_email);
        signupPassword = findViewById(R.id.Signup_password);
        View signupButton = findViewById(R.id.signup_button);
        loginRedirectText = findViewById(R.id.loginRedirectText);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = SignupEmail.getText().toString().trim();
                String pass = signupPassword.getText().toString().trim();

                if (pass.isEmpty()){
                    signupPassword.setError("Email cannot be empty");
                }
                if(pass.isEmpty()) {
                    signupPassword.setError("Password cannot be empty");
                }else {
                    auth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SecondActivity.this, "Signup Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SecondActivity.this, MainActivity.class));
                            }else {
                                Toast.makeText(SecondActivity.this, "Signup Faild"+ task.getException(),Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            }
        });
        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SecondActivity.this,MainActivity.class));
            }
        });
    }
}