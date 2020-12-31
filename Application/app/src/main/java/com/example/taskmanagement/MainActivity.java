package com.example.taskmanagement;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private ActionBarDrawerToggle actionToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.activity_main);
        actionToggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.Open, R.string.Close);

        drawerLayout.addDrawerListener(actionToggle);
        actionToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
                        Toast.makeText(MainActivity.this, "Alarms",Toast.LENGTH_SHORT).show();break;
                    case reminders:
                        Toast.makeText(MainActivity.this, "Reminders",Toast.LENGTH_SHORT).show();break;
                    case settings:
                        Toast.makeText(MainActivity.this, "Settings",Toast.LENGTH_SHORT).show();break;
                    default:
                        return true;
                }

                return true;

            }
        });

    }

    // Determines when an item is selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(actionToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}