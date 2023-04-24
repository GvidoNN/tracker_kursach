package my.lovely.trucks.presentation.registration

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import my.lovely.trucks.R
import my.lovely.trucks.databinding.FragmentRegistrationBinding

@AndroidEntryPoint
class RegistrationFragment: Fragment(R.layout.fragment_registration) {

    private val registrationViewModel: RegistrationViewModel by viewModels()
    private lateinit var binding : FragmentRegistrationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registrationViewModel.progressBar.observe(viewLifecycleOwner){
            if (it == true) {
                binding.progressBar.isVisible = true
                binding.linearRegHide.isVisible = false
            } else {
                binding.progressBar.isVisible = false
                binding.linearRegHide.isVisible = true
            }
        }

        binding.btLogIn.setOnClickListener {
            val login = binding.edRegLogin.text.toString()
            val passwd = binding.edRegPassword.text.toString()
            val email = binding.edRegEmail.text.toString()
            registrationViewModel.sendPostRegistration(login = login, password = passwd, email = email)
            registrationViewModel.registration.observe(viewLifecycleOwner){
                if(it.signUp){
                    findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
                } else {
                    binding.tvRegError.text = it.exception
                    binding.tvRegError.isVisible = true
                    Log.d("MyLog","Ошибка ${it.exception}")
                }
            }
        }

        binding.tvRegLogIn.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
        }
    }
}