package com.palash.ui_meterial_design.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.palash.ui_meterial_design.end_point_api.UnauthorizedAPI
import com.palash.ui_meterial_design.response.login.request.LoginRequest
import com.palash.ui_meterial_design.response.login.response.LoginResponse
import com.palash.ui_meterial_design.utils.NetworkResult
import org.json.JSONObject
import javax.inject.Inject

class UserRepository @Inject constructor(private val unauthorizedAPI: UnauthorizedAPI) {

    private val _loginMutableLiveData = MutableLiveData<NetworkResult<LoginResponse>>()
    val loginResponseLiveData: LiveData<NetworkResult<LoginResponse>>
        get() = _loginMutableLiveData

    suspend fun loginResponse(loginRequest: LoginRequest){
        val response = unauthorizedAPI.getLoginResponse(loginRequest)
        if (response.isSuccessful && response.body() != null) {
            _loginMutableLiveData.postValue(NetworkResult.Success(response.body()!!))

        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _loginMutableLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _loginMutableLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
        //Log.d("MyLoginResponse", response.body()!!.token)
    }

}