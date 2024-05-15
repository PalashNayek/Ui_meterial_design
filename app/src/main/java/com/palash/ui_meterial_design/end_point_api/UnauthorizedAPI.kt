package com.palash.ui_meterial_design.end_point_api

import com.palash.ui_meterial_design.response.login.request.LoginRequest
import com.palash.ui_meterial_design.response.login.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UnauthorizedAPI {

    @POST("auth/login")
    suspend fun getLoginResponse(@Body loginRequest: LoginRequest) : Response<LoginResponse>
}