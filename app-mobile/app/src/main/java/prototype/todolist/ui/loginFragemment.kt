package prototype.todolist.ui

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import prototype.todolist.LoginFragmentDirections
import prototype.todolist.databinding.FragmentLoginBinding
import prototype.todolist.models.User
import prototype.todolist.utils.Status

class loginFragemment: BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate){
    var idUser : Int = 0
    private val viewModel: TourismeViewModel by viewModels()
    override fun init(view: View) {

    }

    @SuppressLint("SuspiciousIndentation")
    override fun listeners(view: View) {
        binding.apply {
            loginBtn.setOnClickListener {
                var emailinput=email.text.toString()
                var passwordinput=password.text.toString()
                var user=User(
                    idUser,emailinput,passwordinput
                )
                viewModel.login(user).observe(viewLifecycleOwner, Observer {
                    when (it.status) {
                        Status.LOADING ->showProgressBar()
                        Status.ERROR -> showResponseError(it.message.toString())
                        Status.SUCCESS -> {
                            Log.d("email respons", it.data?.email.toString())
                            val userId =  it.data?.id
                            val prefs = requireContext().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)

                            if (userId != null) {
                                prefs.edit().putInt("userId", userId).apply()
                            }

                            binding.email.setText("")
                            binding.password.setText("")

//                            val action = LoginFragmentDirections.actionLoginFragmentToTaskManagerFragment()
//                            findNavController().navigate(action)
                            val action = TourismeFormFragmentDirections.actionTaskFormFragmentToTaskManagerFragment()
                            view.findNavController().navigate(action)
                            Toast.makeText(context, "showing.", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
        }
    }

}