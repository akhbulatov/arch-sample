package com.akhbulatov.archsample.ui.global

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import retrofit2.Call
import java.util.*

abstract class BasePresenter<T : MvpView> : MvpPresenter<T>() {

    private val requestSet = HashSet<Call<*>>()

    override fun onDestroy() {
        cancelRequests()
        super.onDestroy()
    }

    protected fun addRequest(request: Call<*>) {
        requestSet.add(request)
    }

    private fun cancelRequests() {
        for (request in requestSet) {
            request.cancel()
        }
    }
}