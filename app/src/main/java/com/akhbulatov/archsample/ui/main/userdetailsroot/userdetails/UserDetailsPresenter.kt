package com.akhbulatov.archsample.ui.main.userdetailsroot.userdetails

import com.akhbulatov.archsample.data.global.DataManager
import com.akhbulatov.archsample.models.UserDetails
import com.akhbulatov.archsample.ui.global.BasePresenter
import com.akhbulatov.archsample.ui.global.ErrorHandler
import com.arellomobile.mvp.InjectViewState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@InjectViewState
class UserDetailsPresenter(
    private val dataManager: DataManager,
    private val login: String,
    private val errorHandler: ErrorHandler
) : BasePresenter<UserDetailsView>() {

    private val executor: ExecutorService = Executors.newSingleThreadExecutor()

    override fun onFirstViewAttach() {
        loadUserDetails()
    }

    override fun onDestroy() {
        executor.shutdown()
        super.onDestroy()
    }

    private fun loadUserDetails() {
        viewState.showLoadingProgress(true)
        viewState.showContentLayout(false)

        val userDetailsRequest = dataManager.getUserDetails(login)
        userDetailsRequest.enqueue(object : Callback<UserDetails> {

            override fun onResponse(call: Call<UserDetails>, response: Response<UserDetails>) {
                viewState.showLoadingProgress(false)
                viewState.showContentLayout(true)

                if (response.isSuccessful) {
                    viewState.showUserDetails(response.body()!!)
                } else {
                    viewState.showError(response.message())
                }
            }

            override fun onFailure(call: Call<UserDetails>, t: Throwable) {
                if (!call.isCanceled) {
                    viewState.showLoadingProgress(false)
                    errorHandler.proceed(t) { viewState.showError(it) }
                }
            }
        })

        addRequest(userDetailsRequest)
    }

    fun onAddToFavoritesClicked(userDetails: UserDetails) {
        executor.submit { dataManager.addUserToFavorites(userDetails) }
        viewState.showToFavoritesAdded()
    }

    fun onBackClicked() = viewState.backToUser()
}
