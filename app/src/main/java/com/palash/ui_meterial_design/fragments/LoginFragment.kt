package com.palash.ui_meterial_design.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.palash.ui_meterial_design.R
import com.palash.ui_meterial_design.databinding.FragmentIntroBinding
import com.palash.ui_meterial_design.databinding.FragmentLoginBinding
import com.palash.ui_meterial_design.response.login.request.LoginRequest
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
            loginViewModel.loginUser(LoginRequest("0lelplR", "kminchelle"))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}