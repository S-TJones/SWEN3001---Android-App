package com.example.taskmanagement;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.view.View;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

        // Floating Button
        FloatingActionButton addButton = findViewById(R.id.add_activity);

        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Button Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }



}