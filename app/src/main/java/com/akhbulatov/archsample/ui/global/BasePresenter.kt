package com.akhbulatov.archsample.ui.global

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import me.aartikov.alligator.Navigator

abstract class BasePresenter<T : MvpView>(private val navigator: Navigator) : MvpPresenter<T>() {

    private val compositeDisposable = CompositeDisposable()

    override fun onDestroy() = compositeDisposable.dispose()

    protected fun Disposable.connect() = compositeDisposable.add(this)

    open fun onBackPressed() = navigator.goBack()
}