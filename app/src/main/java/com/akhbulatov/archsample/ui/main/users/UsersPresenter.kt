package com.akhbulatov.archsample.ui.main.users

import com.akhbulatov.archsample.data.global.DataManager
import com.akhbulatov.archsample.models.User
import com.akhbulatov.archsample.ui.global.BasePresenter
import com.akhbulatov.archsample.ui.global.ErrorHandler
import com.arellomobile.mvp.InjectViewState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@InjectViewState
class UsersPresenter @Inject constructor(
    private val dataManager: DataManager,
    private val errorHandler: ErrorHandler
) : BasePresenter<UsersView>() {

    override fun onFirstViewAttach() {
        loadUsers()
    }

    private fun loadUsers() {
        viewState.showLoadingProgress(true)

        val usersRequest = dataManager.getUsers()
        usersRequest.enqueue(object : Callback<List<User>> {

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                viewState.showLoadingProgress(false)

                if (response.isSuccessful) {
                    viewState.showUsers(response.body()!!)
                } else {
                    viewState.showError(response.message())
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                if (!call.isCanceled) {
                    viewState.showLoadingProgress(false)
                    errorHandler.proceed(t) { viewState.showError(it) }
                }
            }
        })

        addRequest(usersRequest)
    }

    fun onUserClicked(user: User) {
        viewState.navigateToUserDetails(user)
    }

    fun onFavoritesClicked() {
        viewState.navigateToFavorites()
    }
}