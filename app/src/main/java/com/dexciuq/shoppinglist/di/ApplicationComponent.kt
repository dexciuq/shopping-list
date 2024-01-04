package com.dexciuq.shoppinglist.di

import android.app.Application
import com.dexciuq.shoppinglist.data.provider.ShoppingListProvider
import com.dexciuq.shoppinglist.presentation.MainActivity
import com.dexciuq.shoppinglist.presentation.ProductFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(fragment: ProductFragment)
    fun inject(activity: MainActivity)
    fun inject(provider: ShoppingListProvider)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}