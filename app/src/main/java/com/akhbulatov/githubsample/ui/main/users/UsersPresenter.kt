package com.akhbulatov.githubsample.ui.main.users

import com.akhbulatov.githubsample.data.global.DataManager
import com.akhbulatov.githubsample.models.User
import com.akhbulatov.githubsample.ui.global.BasePresenterImpl

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersPresenter(private val dataManager: DataManager) : BasePresenterImpl<UsersView>() {

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

                        }
                    }
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    if (!call.isCanceled) {
                        view?.showLoadingProgress(false)
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