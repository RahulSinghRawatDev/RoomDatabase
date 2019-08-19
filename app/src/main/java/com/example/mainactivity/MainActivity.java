package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mainactivity.adapter.TaskAdapter;
import com.example.mainactivity.db.DbHandler;
import com.example.mainactivity.model.Task;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edTaskSubject, edTaskDescription;
    private Button btnSave;
    private RecyclerView rcView;
    private TaskAdapter taskAdapter;
    private TextView txtCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getTasks();
    }

    private void initAdapter(List<Task> tasks) {
        rcView.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new TaskAdapter(MainActivity.this, tasks);
        rcView.setAdapter(taskAdapter);
        taskAdapter.notifyDataSetChanged();
        txtCount.setText("Count : " + tasks.size());
    }

    private void getTasks() {
        class GetTasks extends AsyncTask<Void, Void, List<Task>> {

            @Override
            protected List<Task> doInBackground(Void... voids) {
                List<Task> taskList = DbHandler
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .getTaskDao()
                        .getAllTask();
                return taskList;
            }

            @Override
            protected void onPostExecute(List<Task> tasks) {
                super.onPostExecute(tasks);
                initAdapter(tasks);
            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();

    }

    private void initView() {
        edTaskDescription = findViewById(R.id.edTaskDescription);
        edTaskSubject = findViewById(R.id.edTaskSubject);
        btnSave = findViewById(R.id.btnSave);
        rcView = findViewById(R.id.rcView);
        txtCount = findViewById(R.id.txtCount);
        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnSave) {
            saveTask();
        }
    }


    private void saveTask() {
        final String taskSubject = edTaskSubject.getText().toString();
        final String taskDescription = edTaskDescription.getText().toString();

        if (TextUtils.isEmpty(taskSubject)) {
            edTaskSubject.setError("task subject required");
            edTaskSubject.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(taskDescription)) {
            edTaskDescription.setError("task description required");
            edTaskDescription.requestFocus();
            return;
        }


        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                Task task = new Task();
                task.setTaskName(taskSubject);
                task.setTaskDescription(taskDescription);
                task.setTaskFinish(false);
                DbHandler.getInstance(getApplicationContext()).getAppDatabase().getTaskDao().insertTask(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                getTasks();
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
        }

        SaveTask saveTask = new SaveTask();
        saveTask.execute();

    }
}
