package prototype.todolist.ui

import android.content.Context
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView

import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import prototype.todolist.R
import prototype.todolist.models.Favorite
import prototype.todolist.models.Tourisme
import prototype.todolist.repositoryies.TourismeRepository


class TourismeRecyclerViewAdapter(private val Citys: ArrayList<Tourisme>, navController: NavController )
    : RecyclerView.Adapter<TourismeRecyclerViewAdapter.DataViewHolder>() {

    var User_id:Int  =0
    private val navController = navController



    class DataViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById<Button>(R.id.taskTitle)
        val city: TextView = view.findViewById<Button>(R.id.taskPriority)
        val taskTimestamp: TextView = view.findViewById<Button>(R.id.taskTimestamp)
        val cardView: CardView = view.findViewById(R.id.cardview)
        val imagev: ImageView = view.findViewById<ImageView>(R.id.imageView1)
        val buttonfavorite:Button=view.findViewById<Button>(R.id.buttonfavorite)
            fun bind(tourisme: Tourisme) {
            name.text = tourisme.nom.toString()
            city.text = tourisme.city.toString()
//            taskTimestamp.text = task.timestamp.toString()
//            imagev.setImageResource(task.image)
            val imageUrl = "http://192.168.56.1:8000/les images de tourisme/${tourisme.image}"

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


    override fun getItemCount(): Int  = Citys.size

    override fun onBindViewHolder(dataViewHolder: DataViewHolder, position: Int) {

        val city = Citys[position]
        dataViewHolder.bind(city)

        dataViewHolder.cardView.setOnClickListener {
            // update
            val action = TourismeManagerFragmentDirections.actionTaskManagerFragmentToTaskFormFragment(taskid = city.id )
            navController.navigate(action)
        }
        dataViewHolder.buttonfavorite.setOnClickListener {
            val prefs = dataViewHolder.itemView.context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
            User_id = prefs.getInt("userId", 0)
            Log.d("userId", User_id.toString())
            Log.d("ocdeId", city.id.toString())
            val cityplagesId=city.id.toString()
//            Log.e("rrr","hello")
            GlobalScope.launch(Dispatchers.Main) {
//            val favorite = Favorite(id = 0,cityplagesId = cityplagesId.toString().toInt(), UserId = UserId)
                val favoritee = Favorite(0,city.id.toString().toInt(), User_id )
                Log.d("favoritee",favoritee.toString())
                TourismeRepository().save(favoritee)
                notifyDataSetChanged()
            }


        }
    }

    fun addCity(citys: List<Tourisme>) {
        this.Citys.apply {
            clear()
            addAll(citys)
        }

    }



}