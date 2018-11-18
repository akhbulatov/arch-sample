package com.akhbulatov.githubsample.ui.global

interface BasePresenter<T : BaseView> {
    fun attachView(view: T)
    fun detachView()
}