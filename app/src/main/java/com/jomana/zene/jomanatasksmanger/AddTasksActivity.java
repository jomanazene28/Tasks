package com.jomana.zene.jomanatasksmanger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jomana.zene.jomanatasksmanger.data.MyTask;

public class AddTasksActivity extends AppCompatActivity {
    private EditText etText;
    private EditText etPhone;
    private EditText etLocation;
    private Button btnContacts;
    private ImageButton ibLocation;
    private RatingBar rtBarPriority;
    private Button btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tasks);

        etText = (EditText) findViewById(R.id.etText);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etLocation = (EditText) findViewById(R.id.etLocation);
        btnContacts = (Button) findViewById(R.id.btnContacts);
        ibLocation = (ImageButton) findViewById(R.id.ibLocation);
        rtBarPriority = (RatingBar) findViewById(R.id.rtBarPriority);
        btnSave = (Button) findViewById(R.id.btnSave);
        eventHandler();
    }

    private void dataHandler() {
        String stText = etText.getText().toString();
        String stPhone = etPhone.getText().toString();
        String stLocation = etLocation.getText().toString();
        int stBarPriority = (int) rtBarPriority.getRating();

        boolean isOk = true;
        if (stText.length() == 0) {
            etText.setError("Wrong Text");
            isOk = false;
        }
        if (stPhone.length() == 0) {
            etPhone.setError("Wrong Phone");
            isOk = false;
        }
        if (stLocation.length() == 0) {
            etLocation.setError("Wrong Location");
            isOk = false;
        }
        if (isOk) {
            //isok
            MyTask myTask = new MyTask();
            myTask.setPhone(stPhone);
            myTask.setTitle(stText);
            myTask.setAddress(stLocation);
            myTask.setPrioroty(stBarPriority);
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

            //reference.push().setValue("hello world");
            String email= FirebaseAuth.getInstance().getCurrentUser().getEmail();
            email=email.replace(".","_");

            reference.child(email).child("MyTasks").push().setValue(myTask, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if (databaseError == null) {
                        Toast.makeText(AddTasksActivity.this, "saved", Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        Toast.makeText(AddTasksActivity.this, "saving faild", Toast.LENGTH_LONG).show();
                    }
                }

            });




        }


        }

    /*
    putting event handlers (listeners)
     */

    private void eventHandler() {
        btnContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataHandler();


            }
        });
        ibLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }







}
