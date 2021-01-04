package com.example.taskmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.taskmanagement.Classes.MainTask;
import com.example.taskmanagement.Database.MainTaskDatabase;

public class AddActivity extends AppCompatActivity {

    // XML components declared here
    EditText taskName, taskHour, taskMinutes;
    Button addTaskButton, addDaysButton;
    Switch dayOrNight;
    CalendarView calendar;
    String taskDate;
//nope
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // Instantiate XML components here
        taskName = findViewById(R.id.add_activity_edit_text);
        taskHour = findViewById(R.id.editTextHour);
        taskMinutes = findViewById(R.id.editTextMins);
        dayOrNight = findViewById(R.id.AMorPM);
        addTaskButton = findViewById(R.id.activity_screen3_button);
        addDaysButton = findViewById(R.id.repeat_checkbox);
        calendar = findViewById(R.id.calendarView);
        //sweet. Nah / LOOOl @ labels
        //we mave to add the transaction to the backstack. I think it saves state. Where do we do the switch?
        // When a day is selected on the Calendar
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                taskDate = String.valueOf(dayOfMonth);
            }
        });

        // When the ADD-New_Task Button is clicked
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainTask mTask;

                try {
                    // Retrieve the data entered by the user
                    String nameOfTask = taskName.getText().toString();
                    String hour = taskHour.getText().toString();
                    String minutes = taskMinutes.getText().toString();
                    StringBuilder time = new StringBuilder();
                    boolean switchAMPM = dayOrNight.isChecked();

                    // Gets the AM or PM
                    time.append(hour + ":" + minutes);
                    if (switchAMPM){
                        time.append(" am");
                    } else {
                        time.append(" pm");
                    }

                    // Make a MainTask object to store the data entered
                    mTask = new MainTask(-1, nameOfTask, taskDate, time.toString());

                } catch (Exception error) {
                    error.printStackTrace();
                    // Error Task
                    mTask = new MainTask(-1, "Error making Task", "No Date", "00:00");
                    Toast.makeText(AddActivity.this, "Error", Toast.LENGTH_LONG).show();
                }

                // Database
                MainTaskDatabase db = new MainTaskDatabase(AddActivity.this);
                boolean addedTask = db.addOneMainTask(mTask);

                Toast.makeText(AddActivity.this, "Adding a New Task", Toast.LENGTH_LONG).show();

                // Should load the MainActivity when clicked
                Intent saveMainTask = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(saveMainTask);
                finish();
            }
        });

        // When the ADD-Days Button is clicked
        addDaysButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent saveMainTask = new Intent(getApplicationContext(), AddRepeatActivity.class);
                startActivity(saveMainTask);


            }
        });
    }
}