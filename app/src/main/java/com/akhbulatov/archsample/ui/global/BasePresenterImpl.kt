package com.akhbulatov.archsample.ui.global

import retrofit2.Call
import java.util.*

abstract class BasePresenterImpl<T : BaseView> : BasePresenter<T> {

    protected var view: T? = null
    private val requestSet = HashSet<Call<*>>()

    override fun attachView(view: T) {
        this.view = view
    }

    override fun detachView() {
        cancelRequests()
        view = null
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