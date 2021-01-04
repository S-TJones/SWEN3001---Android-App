package com.example.taskmanagement.Classes;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.taskmanagement.Database.MainTaskDatabase;
import com.example.taskmanagement.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AddNewSubTask extends BottomSheetDialogFragment {

    public static final String TAG = "ActionBottomDialog";

    private EditText newSubTaskName;
    private Button newSubTaskSaveButton;
    private MainTaskDatabase db;

    public static AddNewSubTask newInstance(){
        return new AddNewSubTask();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.DialogStyle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_sub_task, container,false);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        newSubTaskName = getView().findViewById(R.id.sub_task_name);
        newSubTaskSaveButton = getView().findViewById(R.id.newSubTask);

        db = new MainTaskDatabase(getActivity());
        db.openDatabase();

        boolean isUpdate = false;
        final Bundle bundle = getArguments();
        if (bundle != null){
            isUpdate = true;
            String task = bundle.getString("Name");
            newSubTaskName.setText(task);

            if (task.length()>0){
                newSubTaskSaveButton.setTextColor(ContextCompat.getColor(getContext(), R.color.design_default_color_primary_dark));
            }
        }

        newSubTaskName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")){
                    newSubTaskSaveButton.setEnabled(false);
                    newSubTaskSaveButton.setTextColor(Color.GRAY);
                } else {
                    newSubTaskSaveButton.setEnabled(true);
                    newSubTaskSaveButton.setTextColor(ContextCompat.getColor(getContext(), R.color.design_default_color_primary_dark));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        boolean finalIsUpdate = isUpdate;
        newSubTaskSaveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String text = newSubTaskName.getText().toString();
                if (finalIsUpdate){
                    db.updateSubTask(bundle.getInt("ID"), text);
                } else {
                    SubTask task = new SubTask();
                    task.setSub_task(text);
                    task.setStatus(0);
                    db.insertSubTask(task);
                }
                dismiss();
            }
        });
    }

    @Override
    public void onDismiss(DialogInterface dialog){
        Activity activity = getActivity();

        if (activity instanceof DialogCloseListener){
            ((DialogCloseListener)activity).handleDialogClose(dialog);
        }
    }
}
