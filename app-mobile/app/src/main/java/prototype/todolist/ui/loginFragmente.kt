package prototype.todolist.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import prototype.todolist.R
import prototype.todolist.dao.TourismeDao
import prototype.todolist.databinding.FragmentLoginFragmenteBinding
import prototype.todolist.models.User
import prototype.todolist.utils.Status
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [loginFragmente.newInstance] factory method to
 * create an instance of this fragment.
 */
class loginFragmente : BaseFragment<FragmentLoginFragmenteBinding>(FragmentLoginFragmenteBinding::inflate) {
    // TODO: Rename and change types of parameters
    var idUser : Int = 0
    private val viewModel: TourismeViewModel by viewModels()
    private lateinit var tourismedao: TourismeDao
//    val coroutineScope = CoroutineScope(Dispatchers.Main)
    override fun init(view: View) {
        tourismedao = TourismeDao()
    }

    @SuppressLint("SuspiciousIndentation")
    override fun listeners(view: View) {

        binding.apply {
            loginBtn.setOnClickListener {
//                var emailinput=email.text.toString()
//                var passwordinput=password.text.toString()
                val email = binding.email.text.toString().trim()
                val password = binding.password.text.toString().trim()

//                var user= User(
//                    idUser,email,password
//                )

                GlobalScope.launch(Dispatchers.Main) {
                    val response: Response<User> = tourismedao.login(email, password)
                    Log.d("Response data", response.body().toString())
                    val userId = response.code()
                    //Log.d("Responsebodyy", userId.toString())
                    if (response.isSuccessful) {
                        val user: User? = response.body()
                        val userData: Map<String, Any>? = user?.user
                        val userId: Int? = userData?.get("id") as? Int
                        Log.d("User ID", userData?.get("id").toString())
                        // Use the userId as needed
                    } else {
                        Log.d("ResponseError", response.message())
                        // Handle error response
                    }

//                                val token = response.body()?.
//                                Log.d("user fromLogin", response.body()?.user?.get("id")?.toString()?.toDoubleOrNull()?.toInt())

                }
//                viewModel.login(user).observe(viewLifecycleOwner, Observer {
//                    when (it.status) {
//                        Status.LOADING ->showProgressBar()
//                        Status.ERROR -> showResponseError(it.message.toString())
//                        Status.SUCCESS -> {
//                            Log.d("email respons", it.data.toString())
////                            val userId =  it.data?.id
//                            Log.d("emaill", email)
//                            val prefs = requireContext().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
//
////                            if (userId != null) {
////                                prefs.edit().putInt("userId", userId).apply()
////                            }
//                            binding.email.setText("")
//                            binding.password.setText("")
//
//                            val action = loginFragmenteDirections.actionLoginFragmenteToTaskManagerFragment()
//                            findNavController().navigate(action)
////                            val action = TourismeFormFragmentDirections.actionTaskFormFragmentToTaskManagerFragment()
////                            view.findNavController().navigate(action)
//                            Toast.makeText(context, "showing.", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//                })
            }
        }
    }


}