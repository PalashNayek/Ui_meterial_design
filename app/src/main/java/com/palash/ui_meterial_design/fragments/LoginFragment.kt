package com.palash.ui_meterial_design.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.palash.ui_meterial_design.R
import com.palash.ui_meterial_design.databinding.FragmentIntroBinding
import com.palash.ui_meterial_design.databinding.FragmentLoginBinding
import com.palash.ui_meterial_design.response.login.request.LoginRequest
import com.palash.ui_meterial_design.utils.NetworkResult
import com.palash.ui_meterial_design.view_model.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.txtRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.btnLogin.setOnClickListener {

            binding.progressBar.isVisible = true
            binding.btnLogin.isVisible = false

            loginViewModel.loginUser(LoginRequest("0lelplR", "kminchelle"))
        }

        loginViewModel.loginResponseLiveData.observe(viewLifecycleOwner, Observer {
            binding.progressBar.isVisible = false
            when(it){
                is NetworkResult.Loading -> {
                    binding.progressBar.isVisible = true
                }
                is NetworkResult.Success -> {
                    Log.d("Token", it.data!!.token)
                    findNavController().navigate(R.id.action_loginFragment_to_dashFragment)
                }
                is NetworkResult.Error -> {
                    binding.txtError.text = it.message
                    binding.btnLogin.isVisible = true
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}