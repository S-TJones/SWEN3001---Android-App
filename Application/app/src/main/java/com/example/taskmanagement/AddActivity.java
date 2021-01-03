package com.example.taskmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.taskmanagement.Classes.MainTask;

public class AddActivity extends AppCompatActivity {

    // XML components declared here
    EditText taskName, taskTime;
    Button addTaskButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // Instantiate XML components here
        taskName = findViewById(R.id.add_activity_edit_text);
        addTaskButton = findViewById(R.id.activity_screen3_button);

        // When the ADD-New_Task Button is clicked
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Make a MainTask object to store the data entered
                MainTask mTask = new MainTask();

                Toast.makeText(AddActivity.this, "Adding a New Task", Toast.LENGTH_LONG).show();

                // Should load the MainActivity when clicked
                Intent saveMainTask = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(saveMainTask);
            }
        });
    }
}