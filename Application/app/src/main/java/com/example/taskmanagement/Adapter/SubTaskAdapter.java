package com.example.taskmanagement.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmanagement.Classes.SubTask;
import com.example.taskmanagement.R;
import com.example.taskmanagement.SubTaskActivity;

import java.util.List;

public class SubTaskAdapter extends RecyclerView.Adapter<SubTaskAdapter.ViewHolder> {

    private List<SubTask> subTaskList;
    private SubTaskActivity mainActivity;

    // Constructor
    public SubTaskAdapter(SubTaskActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    // Recycler
    public ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sub_task_layout, parent, false);
        return new ViewHolder(itemView);
    }

    public void  onBindViewHolder(ViewHolder holder, int position){
        SubTask item = subTaskList.get(position);
        holder.subTask.setText(item.getSub_task());
        holder.subTask.setChecked(toBoolean(item.getStatus()));
    }

    private boolean toBoolean(int num){
        return num != 0;
    }

    public int getItemCount(){
        return subTaskList.size();
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
