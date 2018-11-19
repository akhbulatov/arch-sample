package com.akhbulatov.archsample.ui.global

interface BasePresenter<T : BaseView> {
    fun attachView(view: T)
    fun detachView()
}