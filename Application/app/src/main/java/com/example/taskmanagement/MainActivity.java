package com.example.taskmanagement;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    // The Buttons and EditText goes here
    EditText enterTitle;
    EditText dateString; // Idk if it accepts it as a Date data-type or String
    EditText time; // ^ Same as above, Time data-type or String
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate XML components
        listView = findViewById(R.id.list_view);
    }
}