package com.palash.ui_meterial_design.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.palash.ui_meterial_design.repository.UserRepository
import com.palash.ui_meterial_design.response.login.request.LoginRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel(){

    fun loginUser(loginRequest: LoginRequest){
        viewModelScope.launch {
            userRepository.loginResponse(loginRequest)
        }
    }
}