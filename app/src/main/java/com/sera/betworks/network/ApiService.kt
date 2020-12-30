package com.sera.betworks.network


import com.sera.betworks.network.model.UserModel
import io.reactivex.Flowable

import retrofit2.http.GET




interface ApiService {

    @GET("/users/1")
    fun getUser(): Flowable<UserModel?>?

}