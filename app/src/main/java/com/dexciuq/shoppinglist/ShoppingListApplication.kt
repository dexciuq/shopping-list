package com.dexciuq.shoppinglist

import android.app.Application
import android.content.Context
import com.dexciuq.shoppinglist.di.ApplicationComponent
import com.dexciuq.shoppinglist.di.DaggerApplicationComponent

class ShoppingListApplication : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}

val Context.applicationComponent: ApplicationComponent
    get() = when (this) {
        is ShoppingListApplication -> component
        else -> applicationContext.applicationComponent
    }