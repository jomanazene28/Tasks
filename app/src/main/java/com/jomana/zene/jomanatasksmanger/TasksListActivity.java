package com.jomana.zene.jomanatasksmanger;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jomana.zene.jomanatasksmanger.data.MyAdapterTask;
import com.jomana.zene.jomanatasksmanger.data.MyAdapterTask;
import com.jomana.zene.jomanatasksmanger.data.MyTask;

public class TasksListActivity extends AppCompatActivity
{
    private EditText etAdd;
    private ImageButton ibAdd;
    private ListView lvTasks;
    private MyAdapterTask adapterTask;

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etAdd=(EditText) findViewById(R.id.etAdd);
        ibAdd=(ImageButton) findViewById(R.id.ibAdd);
        lvTasks=(ListView) findViewById(R.id.lvtasks);
        adapterTask=new MyAdapterTask(this,R.layout.item_my_task);
        lvTasks.setAdapter(adapterTask);
        eventHandler();
        initListView();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logOut:
                FirebaseAuth.getInstance().signOut();
                Intent i=new Intent(TasksListActivity.this,LoginActivity.class);
                startActivity(i);
                break;
            case R.id.Settings:
                Toast.makeText(getBaseContext(),"Settings",Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }

    private void dataHandler()
    {
        String stAdd=etAdd.getText().toString();

        if (stAdd.length()==0)
            etAdd.setError("Wrong Add");
    }

    private void eventHandler()
    {
        ibAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(TasksListActivity.this,AddTasksActivity.class);
                startActivity(i);

            }
        });
    }

 private void initListView(){

     String email=FirebaseAuth.getInstance().getCurrentUser().getEmail().replace('.','_');
     DatabaseReference reference= FirebaseDatabase.getInstance().getReference(email);
     reference.child("MyTasks").addValueEventListener(new ValueEventListener() {
         @Override
         public void onDataChange(DataSnapshot dataSnapshot) {
             adapterTask.clear();
             for (DataSnapshot ds:dataSnapshot.getChildren())
             {
                 MyTask myTask=ds.getValue(MyTask.class);
                 myTask.setId(ds.getKey());
                 adapterTask.add(myTask);
             }
         }

         @Override
         public void onCancelled(DatabaseError databaseError) {

         }
     });
 }


}
