package com.example.taskmanagement.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmanagement.Classes.AddNewSubTask;
import com.example.taskmanagement.Classes.SubTask;
import com.example.taskmanagement.Database.MainTaskDatabase;
import com.example.taskmanagement.Database.SubTaskDatabase;
import com.example.taskmanagement.R;
import com.example.taskmanagement.SubTaskActivity;

import java.util.List;

public class SubTaskAdapter extends RecyclerView.Adapter<SubTaskAdapter.ViewHolder> {

    private List<SubTask> subTaskList;
    private SubTaskActivity mainActivity;
    private SubTaskDatabase db;

    // Constructor
    public SubTaskAdapter(SubTaskDatabase db, SubTaskActivity mainActivity){
        this.mainActivity = mainActivity;
        this.db = db;
    }

    // Recycler
    public ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sub_task_layout, parent, false);
        return new ViewHolder(itemView);
    }

    public void onBindViewHolder(ViewHolder holder, int position){
        db.openDatabase();
        SubTask item = subTaskList.get(position);
        holder.subTask.setText(item.getSub_task());
        holder.subTask.setChecked(toBoolean(item.getStatus()));
        holder.subTask.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    db.updateStatus(item.getId(), 1);
                }else {
                    db.updateStatus(item.getId(), 0);
                }
            }
        });
    }

    private boolean toBoolean(int num){
        return num != 0;
    }

    public int getItemCount(){
        return subTaskList.size();
    }

    public void setSubTaskList(List<SubTask> taskList){
        this.subTaskList = taskList;
        notifyDataSetChanged();
    }

    public Context getContext(){
        return mainActivity;
    }

    public void deleteItem(int position){
        SubTask item = subTaskList.get(position);
        db.deleteSubTask(item.getId());
        subTaskList.remove(position);
        notifyItemRemoved(position);
    }

    public void editItem(int position){
        SubTask item = subTaskList.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("ID", item.getId());
        bundle.putString("Name", item.getSub_task());
        AddNewSubTask fragment = new AddNewSubTask();
        fragment.setArguments(bundle);
        fragment.show(mainActivity.getSupportFragmentManager(), AddNewSubTask.TAG);
    }

    // ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox subTask;

        ViewHolder(View view){
            super(view);
            subTask = view.findViewById(R.id.todoCheckBox);
        }
    }
}
