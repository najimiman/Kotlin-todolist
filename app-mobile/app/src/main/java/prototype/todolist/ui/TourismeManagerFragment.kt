package prototype.todolist.ui


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import prototype.todolist.LoginFragmentDirections
import prototype.todolist.R
import prototype.todolist.databinding.FragmentTaskFormBinding
import prototype.todolist.databinding.FragmentTaskManagerBinding
import prototype.todolist.utils.Status


class TourismeManagerFragment : BaseFragment<FragmentTaskManagerBinding>(FragmentTaskManagerBinding::inflate) {

    private val viewModel: TourismeViewModel by viewModels()
    private lateinit var adapter: TourismeRecyclerViewAdapter
    private lateinit var recyclerView: RecyclerView

    override fun init(view: View) {
        this.setProgressBar(R.id.progressBar)
        adapter =  TourismeRecyclerViewAdapter(arrayListOf(), view.findNavController() )
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter =  adapter
        }

        // getUsers observe
        viewModel.getTasks().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> this.showProgressBar()
                Status.ERROR -> this.showResponseError(it.message.toString())
                Status.SUCCESS -> {
                    binding.recyclerView.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    adapter.apply {
                        addTasks(it.data!!)
                        notifyDataSetChanged()
                    }
                }
            }
        })

    }

    override fun listeners(view: View) {
        binding.apply {
            floatingActionButton.setOnClickListener{
                val action = TourismeManagerFragmentDirections.actionTaskManagerFragmentToTaskFormFragment(taskid = 0 )
                view.findNavController().navigate(action)
            }
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_menu_add -> {
                val action = TourismeManagerFragmentDirections.
                actionTaskManagerFragmentToTaskFormFragment(taskid = 0)
                    recyclerView.findNavController().navigate(action)
                true
            }
            R.id.item_menu_login -> {
//                val action = TourismeManagerFragmentDirections.
//                actionTaskManagerFragmentToTaskFormFragment(taskid = 0)
//                recyclerView.findNavController().navigate(action)
                val action = TourismeManagerFragmentDirections.actionTaskManagerFragmentToLoginFragment2()
               findNavController().navigate(action)

//                Toast.makeText(this.activity,"Login",Toast.LENGTH_LONG).show()

                true
            }
            else->super.onOptionsItemSelected(item)
        }
    }

    // Todo : Implémentez le code du button Ajouter une tâche dans le menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.layout_menu, menu)
        inflater.inflate(R.menu.layout_menu,menu)
    }



}