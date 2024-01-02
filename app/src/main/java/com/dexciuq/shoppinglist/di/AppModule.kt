package com.dexciuq.shoppinglist.di

import com.dexciuq.shoppinglist.di.viewmodel.ViewModelModule
import dagger.Module

@Module(includes = [DataModule::class, ViewModelModule::class])
class AppModule