package com.akhbulatov.archsample.ui.main.users

import com.akhbulatov.archsample.data.global.DataManager
import com.akhbulatov.archsample.models.User
import com.akhbulatov.archsample.ui.global.BasePresenterImpl
import com.akhbulatov.archsample.ui.global.ErrorHandler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersPresenter(
    private val dataManager: DataManager,
    private val errorHandler: ErrorHandler
) : BasePresenterImpl<UsersView>() {

    override fun attachView(view: UsersView) {
        super.attachView(view)
        loadUsers()
    }

    private fun loadUsers() {
        view?.let {
            it.showLoadingProgress(true)

            val usersRequest = dataManager.getUsers()
            usersRequest.enqueue(object : Callback<List<User>> {
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    view?.let {
                        it.showLoadingProgress(false)

                        if (response.isSuccessful) {
                            it.showUsers(response.body()!!)
                        } else {
                            view?.showError(response.message())
                        }
                    }
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    if (!call.isCanceled) {
                        view?.showLoadingProgress(false)
                        errorHandler.proceed(t) { view?.showError(it) }
                    }
                }
            })

            addRequest(usersRequest)
        }
    }

    fun onUserClicked(user: User) {
        view?.navigateToUserDetails(user)
    }

    fun onFavoritesClicked() {
        view?.navigateToFavorites()
    }
}