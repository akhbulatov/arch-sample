package com.akhbulatov.githubsample.ui.main.userdetailsroot.userdetails

import com.akhbulatov.githubsample.data.global.DataManager
import com.akhbulatov.githubsample.models.UserDetails
import com.akhbulatov.githubsample.ui.global.BasePresenterImpl
import com.akhbulatov.githubsample.ui.global.ErrorHandler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class UserDetailsPresenter(
    private val dataManager: DataManager,
    private val login: String,
    private val errorHandler: ErrorHandler
) : BasePresenterImpl<UserDetailsView>() {

    private val executor: ExecutorService = Executors.newSingleThreadExecutor()

    override fun attachView(view: UserDetailsView) {
        super.attachView(view)
        loadUserDetails()
    }

    override fun detachView() {
        executor.shutdown()
        super.detachView()
    }

    private fun loadUserDetails() {
        view?.let {
            it.showLoadingProgress(true)
            it.showContentLayout(false)

            val userDetailsRequest = dataManager.getUserDetails(login)
            userDetailsRequest.enqueue(object : Callback<UserDetails> {
                override fun onResponse(call: Call<UserDetails>, response: Response<UserDetails>) {
                    view?.let {
                        it.showLoadingProgress(false)
                        it.showContentLayout(true)

                        if (response.isSuccessful) {
                            it.showUserDetails(response.body()!!)
                        } else {
                            view?.showError(response.message())
                        }
                    }
                }

                override fun onFailure(call: Call<UserDetails>, t: Throwable) {
                    if (!call.isCanceled) {
                        view?.showLoadingProgress(false)
                        errorHandler.proceed(t) { view?.showError(it) }
                    }
                }
            })

            addRequest(userDetailsRequest)
        }
    }

    fun onAddToFavoritesClicked(userDetails: UserDetails) {
        executor.submit { dataManager.addUserToFavorites(userDetails) }
        view?.showToFavoritesAdded()
    }

    fun onBackClicked() = view?.backToUser()
}
