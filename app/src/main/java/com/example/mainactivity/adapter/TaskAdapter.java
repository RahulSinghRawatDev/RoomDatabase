package com.example.mainactivity.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mainactivity.R;
import com.example.mainactivity.model.Task;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private List<Task> taskList;
    private Context context;

    public TaskAdapter(Context context, List<Task> taskList){
      this.context = context;
      this.taskList = taskList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.taskitem, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.txtSubject.setText("Subject : "+task.getTaskName());
        holder.txtDescription.setText("Description : "+task.getTaskDescription());
        holder.taskId.setText("Id : "+String.valueOf(task.getId()));
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView taskId,txtSubject,txtDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.taskId = itemView.findViewById(R.id.taskId);
            this.txtDescription = itemView.findViewById(R.id.txtDescription);
            this.txtSubject = itemView.findViewById(R.id.txtSubject);
        }
    }
}
