package prototype.todolist.ui


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import prototype.todolist.R
import prototype.todolist.data.TaskRepository
import android.util.Log
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class TaskAdapter() : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {


    private val taskRepository = TaskRepository()
    class TaskViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val taskTitle: TextView = view.findViewById<Button>(R.id.taskTitle)
        val taskPriority: TextView = view.findViewById<Button>(R.id.taskPriority)
        val taskTimestamp: TextView = view.findViewById<Button>(R.id.taskTimestamp)
        val cardView: CardView = view.findViewById(R.id.cardview)

        val prioritiesArray: Array<String> = view.resources.getStringArray(R.array.priorities)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return taskRepository.getAllTasks().size
    }

    override fun onBindViewHolder(taskViewHolder: TaskViewHolder, position: Int) {
        val task = this.taskRepository.getAllTasks()[position]
        taskViewHolder.taskTitle.text = task.title
        taskViewHolder.taskPriority.text = task.priority.toString()
        taskViewHolder.taskTimestamp.text = task.timestamp.toString()

        val item1 = taskViewHolder.prioritiesArray[0]
        val item2 = taskViewHolder.prioritiesArray[1]
        val item3 = taskViewHolder.prioritiesArray[2]
        if (task.priority==1){
            taskViewHolder.taskPriority.text=item1
        }
        else if (task.priority==2){
            taskViewHolder.taskPriority.text=item2
        }
        else{
            taskViewHolder.taskPriority.text=item3
        }


        val dd = task.timestamp.toString().toLong()
        val date=Date(dd)
        taskViewHolder.taskTimestamp.text=date.toString()
//        Log.d(date.toString(),"mm")

        taskViewHolder.cardView.setOnClickListener {

            task.title = task.title + "+"

            // Todo : supprimer ces deux lignes et voir est ce que RecyclerView continue d'afficher les updates ?
            val repository = TaskRepository()
            repository.save(task)
            this.notifyDataSetChanged()

            // Todo : Afficher un message aprés Update
            // Toast.makeText(context,"Update $task", Toast.LENGTH_LONG).show()
        }
    }

}