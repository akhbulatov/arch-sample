package com.akhbulatov.archsample.domain.global

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class SchedulersProvider {
    open fun io(): Scheduler = Schedulers.io()
    open fun ui(): Scheduler = AndroidSchedulers.mainThread()
}