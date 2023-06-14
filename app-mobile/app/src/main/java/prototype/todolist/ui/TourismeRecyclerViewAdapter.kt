package prototype.todolist.ui

import android.content.Context
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import prototype.todolist.R
import prototype.todolist.models.Tourisme
import prototype.todolist.models.User


class TourismeRecyclerViewAdapter(private val tasks: ArrayList<Tourisme>, navController: NavController )
    : RecyclerView.Adapter<TourismeRecyclerViewAdapter.DataViewHolder>() {

    var userId:Int  =0
    private val navController = navController

    class DataViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById<Button>(R.id.taskTitle)
        val city: TextView = view.findViewById<Button>(R.id.taskPriority)
        val taskTimestamp: TextView = view.findViewById<Button>(R.id.taskTimestamp)
        val cardView: CardView = view.findViewById(R.id.cardview)
        val imagev: ImageView = view.findViewById<ImageView>(R.id.imageView1)
        val buttonfavorite:Button=view.findViewById<Button>(R.id.buttonfavorite)
        fun bind(task: Tourisme) {
            name.text = task.nom.toString()
            city.text = task.city.toString()
//            taskTimestamp.text = task.timestamp.toString()
//            imagev.setImageResource(task.image)
            val imageUrl = "http://192.168.56.1:8000/les images de tourisme/${task.image}"

//            Log.d("img", meal.img.toString())
//            Picasso.get().load(imageUrl).into(mealImg)
            Picasso.get().load(imageUrl).into(imagev)



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.task_item, parent, false)
        return DataViewHolder(layout)
    }


    override fun getItemCount(): Int  = tasks.size

    override fun onBindViewHolder(dataViewHolder: DataViewHolder, position: Int) {

        val task = tasks[position]
        dataViewHolder.bind(task)

        dataViewHolder.cardView.setOnClickListener {
            // update
            val action = TourismeManagerFragmentDirections.actionTaskManagerFragmentToTaskFormFragment(taskid = task.id )
            navController.navigate(action)
        }
        dataViewHolder.buttonfavorite.setOnClickListener {
            val prefs = dataViewHolder.itemView.context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
            userId = prefs.getInt("userId", 0)
            Log.d("userId", userId.toString())

        }
    }

    fun addTasks(tasks: List<Tourisme>) {
        this.tasks.apply {
            clear()
            addAll(tasks)
        }

    }



}