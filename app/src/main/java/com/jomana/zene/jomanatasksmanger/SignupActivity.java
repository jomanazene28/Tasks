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
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {
    private EditText etEmail;
    private EditText etFullName;
    private EditText etPass1;
    private EditText etPass2;
    private Button btnSignUp;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etFullName = (EditText) findViewById(R.id.etFullName);
        etPass1 = (EditText) findViewById(R.id.etPassw1);
        etPass2 = (EditText) findViewById(R.id.etPassw2);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        eventHandler();
        auth = FirebaseAuth.getInstance();


    }


    private void dataHandler() {
        String stEmail = etEmail.getText().toString();
        String stFullName = etFullName.getText().toString();
        String stPass1 = etPass1.getText().toString();
        String stPass2 = etPass2.getText().toString();

        boolean isOk=true;
        if (stEmail.length() == 0) {
            etEmail.setError("Wrong Email");
            isOk = false;
        }
        if (stFullName.length() == 0) {
            etFullName.setError("Wrong Fullname");
            isOk = false;
        }
        if (stPass1.length() == 0) {
            etPass1.setError("Wrong password");
            isOk = false;
        }
        if (stPass2.length() == 0){
            etPass2.setError("Wrong Password");
        isOk = false;
    }
        if(isOk)
            creatAcount(stEmail,stPass1);

    }

    private void eventHandler() {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(i);
                dataHandler();


            }
        });
    }

    private FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser user=firebaseAuth.getCurrentUser();
            if (user!=null)

                        Toast.makeText(SignupActivity.this,"user is signed in.",Toast.LENGTH_SHORT).show();
           else
               Toast.makeText(SignupActivity.this,"user signed out",Toast.LENGTH_SHORT).show();





        }


    };
    protected void onStart(){
        super.onStart();
        auth.addAuthStateListener(authStateListener);
    }
    protected void onStop(){
        super.onStop();
        if (authStateListener!=null)
            auth.removeAuthStateListener(authStateListener);
    }

    private void creatAcount(String email,String passw){
        auth.createUserWithEmailAndPassword(email, passw).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful())
                {
                    Toast.makeText(SignupActivity.this,"Authentication Successful",Toast.LENGTH_SHORT).show();
                    finish();
                }
                else
                {
                    Toast.makeText(SignupActivity.this,"Authentication failed"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }

            }
        });

    }


}

