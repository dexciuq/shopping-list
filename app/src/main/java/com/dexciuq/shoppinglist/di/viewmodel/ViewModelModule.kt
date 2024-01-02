package com.dexciuq.shoppinglist.di.viewmodel

import androidx.lifecycle.ViewModel
import com.dexciuq.shoppinglist.presentation.MainViewModel
import com.dexciuq.shoppinglist.presentation.ProductViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProductViewModel::class)
    fun bindProductViewModel(viewModel: ProductViewModel): ViewModel
}