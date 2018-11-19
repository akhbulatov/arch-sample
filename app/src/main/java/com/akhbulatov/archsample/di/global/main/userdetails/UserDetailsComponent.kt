package com.akhbulatov.archsample.di.global.main.userdetails

import com.akhbulatov.archsample.presentation.ui.main.userdetails.UserDetailsFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [UserDetailsModule::class])
@UserDetailsScope
interface UserDetailsComponent {
    fun inject(fragment: UserDetailsFragment)

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance fun login(login: String): Builder

        fun build(): UserDetailsComponent
    }
}