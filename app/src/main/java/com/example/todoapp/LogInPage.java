package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogInPage extends AppCompatActivity {
    Button submit;
    EditText task_number;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_page);
        task_number = (EditText) findViewById(R.id.aadhar);
        db = new DBHelper(this);
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String task_numberr = task_number.getText().toString();

                if(task_number.equals(""))
                {
                    Toast.makeText(LogInPage.this, "Enter task number", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean result = db.check_ID(task_numberr);
                    if(result == false)
                    {
                        Toast.makeText(LogInPage.this, "Task does not exists.\n Kindly Register", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), SignUPPage.class);
                        startActivity(intent);
                    }
                    else{
                        Intent intent = new Intent(getApplicationContext(), Event.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }
}