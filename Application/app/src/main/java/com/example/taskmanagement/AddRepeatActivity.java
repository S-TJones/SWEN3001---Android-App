package com.example.taskmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddRepeatActivity extends AppCompatActivity {

    Button saveBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_repeat);
        saveBtn = findViewById(R.id.saveDays);


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
        //nothing was added when I entered that last task
        //The chicken task was there from before. Cool. Yh
    //I clicked save

}
