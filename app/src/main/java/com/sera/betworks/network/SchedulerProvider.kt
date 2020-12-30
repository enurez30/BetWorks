package com.sera.betworks.network

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


object SchedulerProvider {

    // UI thread
    fun ui(): Scheduler? {
        return AndroidSchedulers.mainThread()
    }

    // IO thread
    fun io(): Scheduler {
        return Schedulers.io()
    }

    // Computation thread
    fun computation(): Scheduler {
        return Schedulers.computation()
    }

}