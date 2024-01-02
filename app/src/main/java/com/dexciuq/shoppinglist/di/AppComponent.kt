package com.dexciuq.shoppinglist.di

import android.app.Application
import com.dexciuq.shoppinglist.presentation.MainActivity
import com.dexciuq.shoppinglist.presentation.ProductFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(fragment: ProductFragment)
    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }
}