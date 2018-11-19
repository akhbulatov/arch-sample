package com.akhbulatov.archsample.di.global.main.users

import com.akhbulatov.archsample.presentation.ui.main.users.UsersFragment
import dagger.Subcomponent

@Subcomponent(modules = [UsersModule::class])
@UsersScope
interface UsersComponent {
    fun inject(fragment: UsersFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): UsersComponent
    }
}