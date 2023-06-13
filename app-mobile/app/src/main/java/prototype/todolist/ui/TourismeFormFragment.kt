package prototype.todolist.ui


import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import prototype.todolist.R
import prototype.todolist.databinding.FragmentTaskFormBinding
import prototype.todolist.models.Tourisme
import prototype.todolist.utils.Status

class TourismeFormFragment : BaseFragment<FragmentTaskFormBinding>(FragmentTaskFormBinding::inflate) {

    companion object {
        val TASKID = "taskid" // Il resemble à une variable static
    }

    private val viewModel: TourismeViewModel by viewModels()
    private var taskId =  0 // La valeur 0 signifie que le formulaire est dans l'état d'insertion
    private  var tourisme : Tourisme? = null

    override fun init(view: View) {

        arguments?.let {
            taskId = it.getInt(TASKID)
        }

        this.setProgressBar(R.id.progressBar)

        // Add
//        if(taskId == 0){
//            this.tourisme = Tourisme(0,"","", System.currentTimeMillis())
//            binding.btnDelete.visibility = View.INVISIBLE
//            binding.progressBar.visibility = View.GONE
//        }
        // Update
//        else
//        {
//
//            // Call : FindById
//            viewModel.findById(taskId).observe(viewLifecycleOwner, Observer {
//                when (it.status) {
//                    Status.LOADING -> this.showProgressBar()
//                    Status.ERROR -> this.showResponseError(it.message.toString())
//                    Status.SUCCESS -> {
//                        this.hideProgressBar()
//                        binding.apply {
//                            tourisme = it.data!!
//                            editTaskTitle.setText(tourisme?.title)
//                            spinner.setSelection(tourisme?.priority!!)
//                        }
//                    }
//                }
//            })
//
//        }


    }

    override fun listeners(view: View) {
        binding.apply {
            btnSave.setOnClickListener {
                if(TextUtils.isEmpty(editTaskTitle.text)){
                    Toast.makeText(context, "It's empty!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                val taskTitle = editTaskTitle.text.toString()
                val priority = spinner.selectedItemPosition
                val task = Tourisme(
                    taskId,
                    taskTitle,
//                    priority,
                    tourisme?.nom!!

                )

                viewModel.save(task).observe(viewLifecycleOwner, Observer {
                    when (it.status) {
                        Status.LOADING -> showProgressBar()
                        Status.ERROR -> showResponseError(it.message.toString())
                        Status.SUCCESS -> {
                            binding.form.visibility = View.VISIBLE
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(context, "Saved!", Toast.LENGTH_SHORT).show()
                            val action = TourismeFormFragmentDirections.actionTaskFormFragmentToTaskManagerFragment()
                            view.findNavController().navigate(action)
                        }
                    }
                })
//


            }
            btnDelete.setOnClickListener {
                viewModel.delete(taskId).observe(viewLifecycleOwner, Observer {
                    when (it.status) {
                        Status.LOADING -> showProgressBar()
                        Status.ERROR -> showResponseError(it.message.toString())
                        Status.SUCCESS -> {
                            binding.form.visibility = View.VISIBLE
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(activity, "${tourisme?.nom} : Deleted", Toast.LENGTH_LONG).show()
                        }
                    }
                })
                val action = TourismeFormFragmentDirections.actionTaskFormFragmentToTaskManagerFragment()
                view.findNavController().navigate(action)
            }
        }

    }


}
