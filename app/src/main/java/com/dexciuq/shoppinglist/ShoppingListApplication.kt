package com.dexciuq.shoppinglist

import android.app.Application
import android.content.Context
import com.dexciuq.shoppinglist.di.AppComponent
import com.dexciuq.shoppinglist.di.DaggerAppComponent

class ShoppingListApplication : Application() {

    val appComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is ShoppingListApplication -> appComponent
        else -> applicationContext.appComponent
    }