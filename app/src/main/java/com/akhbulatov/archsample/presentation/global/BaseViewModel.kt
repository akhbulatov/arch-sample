package com.akhbulatov.archsample.presentation.global

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ru.terrakok.cicerone.Router

abstract class BaseViewModel(private val router: Router) : ViewModel() {
    private val subscriptions = CompositeDisposable()

    override fun onCleared() {
        subscriptions.dispose()
    }

    protected fun Disposable.connect() = subscriptions.add(this)

    open fun onBackPressed() = router.exit()
}