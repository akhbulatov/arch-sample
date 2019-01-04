package com.akhbulatov.archsample.di.main.users

import com.akhbulatov.archsample.ui.main.users.UsersFragment
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