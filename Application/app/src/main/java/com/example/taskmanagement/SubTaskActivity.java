package com.example.taskmanagement;

import android.content.DialogInterface;
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
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmanagement.Adapter.SubTaskAdapter;
import com.example.taskmanagement.Classes.AddNewSubTask;
import com.example.taskmanagement.Classes.DialogCloseListener;
import com.example.taskmanagement.Classes.RecyclerItemTouchHelper;
import com.example.taskmanagement.Classes.SubTask;
import com.example.taskmanagement.Database.SubTaskDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
//clicking save I think- after trying to add a sub activity
public class SubTaskActivity extends AppCompatActivity implements DialogCloseListener {

    private RecyclerView subTaskRV;
    private SubTaskAdapter taskAdapter;
    private FloatingActionButton fab;

    private List<SubTask> taskList;
    private SubTaskDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_task);
//        getSupportActionBar().hide();

        // Initialize the list and DB
        taskList = new ArrayList<>();
        try {
            db = new SubTaskDatabase(this);
            db.openDatabase();

        } catch (Exception e) {
            e.printStackTrace();
        }


        subTaskRV = findViewById(R.id.recycler_view);
        subTaskRV.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new SubTaskAdapter(db,this);
        subTaskRV.setAdapter(taskAdapter);

        fab = findViewById(R.id.add_activity);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerItemTouchHelper(taskAdapter));
        itemTouchHelper.attachToRecyclerView(subTaskRV);

        // Dummy Data
//        SubTask example = new SubTask(1, 1);
//        example.setSub_task("Wash the dishes.");
//        example.setStatus(0);
//        taskList.add(example);

//        taskAdapter.setSubTaskList(taskList);
        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        taskAdapter.setSubTaskList(taskList);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewSubTask.newInstance().show(getSupportFragmentManager(), AddNewSubTask.TAG);
            }
        });
    }

    @Override
    public void handleDialogClose(DialogInterface dialogInterface) {
        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        taskAdapter.setSubTaskList(taskList);
        taskAdapter.notifyDataSetChanged();
    }
}