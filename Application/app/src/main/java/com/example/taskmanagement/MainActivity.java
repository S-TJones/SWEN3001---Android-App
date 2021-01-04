package com.example.taskmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmanagement.Classes.MainTask;
import com.example.taskmanagement.Database.MainTaskDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // XML components go here
//    RecyclerView recyclerView;
    ListView recyclerView;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionToggle;
    private NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate XML components
        recyclerView = findViewById(R.id.recycler_view);

        // Display Tasks ---------------------------------------------------------
        MainTaskDatabase mainTaskDatabase = new MainTaskDatabase(MainActivity.this);
        showTasksOnListView(mainTaskDatabase);

        // OnClickListener for List-items
        recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                MainTask selectedTask = (MainTask) parent.getItemAtPosition(position);
//                mainTaskDatabase.deleteOneMainTask(selectedTask);
//                showTasksOnListView(mainTaskDatabase);

                // Should load the SubActivity when clicked
                Intent subTaskActivity = new Intent(getApplicationContext(), SubTaskActivity.class);
                startActivity(subTaskActivity);

                Toast.makeText(MainActivity.this, "Task selected", Toast.LENGTH_SHORT).show();
            }
        });
        // -----------------------------------------------------------------------

        // Floating Button on Main Activity --------------------
        FloatingActionButton addButton = findViewById(R.id.add_activity);

        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Button Clicked", Toast.LENGTH_SHORT).show();

                // Should load the AddActivity when clicked
                Intent addMainTask = new Intent(getApplicationContext(), AddActivity.class);
                startActivity(addMainTask);
            }
        });
        // -----------------------------------------------------


        // Nav-Bar on Main Activity ----------------------------------------------------------------
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.activity_main);
        actionToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close);

        drawerLayout.addDrawerListener(actionToggle);
        actionToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Navigation View
        NavigationView navView = (NavigationView) findViewById(R.id.nv);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                final int alarms = 1000308;
                final int reminders = 1000234;
                final int settings = 1000257;

                switch(id)
                {
                    case alarms:
                        Toast.makeText(MainActivity.this, "Alarms",Toast.LENGTH_SHORT).show();
                        break;
                    case reminders:
                        Toast.makeText(MainActivity.this, "Reminders",Toast.LENGTH_SHORT).show();
                        break;
                    case settings:
                        Toast.makeText(MainActivity.this, "Settings",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        return true;
                }

                return true;

            }
        });
        // -----------------------------------------------------------------------------------------

    }

    // Shows Tasks
    public void showTasksOnListView(MainTaskDatabase mainTaskDatabase){
        List<MainTask> mainTasks = mainTaskDatabase.getAllMainTasks();

        ArrayAdapter mainTaskArrayAdapter = new ArrayAdapter<MainTask>(MainActivity.this, android.R.layout.simple_list_item_1, mainTasks);
        recyclerView.setAdapter(mainTaskArrayAdapter);
    }

    // Determines when an item is selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(actionToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);

    }


}