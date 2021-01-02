package com.example.taskmanagement;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmanagement.Adapter.SubTaskAdapter;
import com.example.taskmanagement.Classes.SubTask;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class SubTaskActivity extends AppCompatActivity {

    private RecyclerView subTaskRV;
    private SubTaskAdapter taskAdapter;

    private List<SubTask> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_task);
//        getSupportActionBar().hide();

        // Initialize the list
        taskList = new ArrayList<>();

        subTaskRV = findViewById(R.id.recycler_view);
        subTaskRV.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new SubTaskAdapter(this);
        subTaskRV.setAdapter(taskAdapter);

        // Dummy Data
//        SubTask example = new SubTask(1, 1);
//        example.setSub_task("Wash the dishes.");
//        example.setStatus(0);
//        taskList.add(example);

        taskAdapter.setSubTaskList(taskList);
    }

}