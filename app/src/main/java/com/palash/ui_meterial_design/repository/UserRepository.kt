package com.palash.ui_meterial_design.repository

import android.util.Log
import com.palash.ui_meterial_design.end_point_api.UnauthorizedAPI
import com.palash.ui_meterial_design.response.login.request.LoginRequest
import javax.inject.Inject

class UserRepository @Inject constructor(private val unauthorizedAPI: UnauthorizedAPI) {

    suspend fun loginResponse(loginRequest: LoginRequest){
        val response = unauthorizedAPI.getLoginResponse(loginRequest)
        Log.d("MyLoginResponse", response.body()!!.token)
    }
}