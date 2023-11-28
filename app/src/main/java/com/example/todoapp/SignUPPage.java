package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUPPage extends AppCompatActivity {
    EditText task_id,task_name,task_status;
    Button submit;
    DBHelper db;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_uppage);
        task_id = (EditText) findViewById(R.id.task_id);
        task_name = (EditText) findViewById(R.id.Task_name);
        task_status = (EditText) findViewById(R.id.task_status);
        submit = (Button) findViewById(R.id.submit1);
        db = new DBHelper(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String task_id_1 = task_id.getText().toString();
                int id1=Integer.parseInt(task_id_1);
                String task_name_1 = task_name.getText().toString();
                String task_status_1 = task_status.getText().toString();

                if(task_id_1.equals("") || task_name_1.equals("")|| task_status_1.equals("") )
                {
                    Toast.makeText(SignUPPage.this, "Fill all the fields", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    Boolean result = db.check_ID(task_id_1);
                    if(result == false)
                    {
                        Boolean res = db.insertData(id1, task_name_1, task_status_1);
                        if(res==true)
                        {
                            Toast.makeText(SignUPPage.this,"Registration Succesful!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Event.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(SignUPPage.this,"Registration Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(SignUPPage.this, "Task already exists.\n Please Login", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), LogInPage.class);
                        startActivity(intent);
                    }

                }
            }

        });
    }
}