package com.jomana.zene.jomanatasksmanger;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity
{
    private EditText etEmail;
    private EditText etPassword;
    private Button btnSignIn;
    private Button btnNewAccount;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail=(EditText) findViewById(R.id.etEmail);
        etPassword=(EditText) findViewById(R.id.etPassword);
        btnSignIn=(Button) findViewById(R.id.btnSignIn);
        btnNewAccount=(Button) findViewById(R.id.btnNewAccount);
        auth=FirebaseAuth.getInstance();

        //reference.push().setValue("jomana");
        eventHandler();

    }
    /*1.getting data from the ui from(edittext,checkbox,radiobutton etc..)
      2.checking data (the email text is ok, the password...,
      3.dealing with the data
     */

    private void dataHandler() {
        //1.getting data
        String stEmail = etEmail.getText().toString();
        String stPassword = etPassword.getText().toString();
        //2.checking
        boolean isOk = true;
        if (stEmail.length() == 0) {
            etEmail.setError("Wrong Email");
            isOk = false;
        }
        if (stPassword.length() == 0) {
            etPassword.setError("Wrong Password");
            isOk = false;
        }
        if (isOk)
            signIn(stEmail, stPassword);


    }



    private void eventHandler()
    {
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dataHandler();



            }
        });
        btnNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(i);

            }
        });


    }
    private void signIn(String email,String passw){
        auth.signInWithEmailAndPassword(email, passw).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(LoginActivity.this,"signIn Successful",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(LoginActivity.this,TasksListActivity.class);
                    startActivity(i);

                }
                else
                {
                   Toast.makeText(LoginActivity.this,"signIn faild"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }
            }
        });

    }



}

