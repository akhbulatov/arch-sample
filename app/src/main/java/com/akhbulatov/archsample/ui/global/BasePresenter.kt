package com.akhbulatov.archsample.ui.global

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter<T : MvpView> : MvpPresenter<T>() {
    private val compositeDisposable = CompositeDisposable()

    override fun onDestroy() = compositeDisposable.dispose()

    protected fun Disposable.connect() = compositeDisposable.add(this)
}