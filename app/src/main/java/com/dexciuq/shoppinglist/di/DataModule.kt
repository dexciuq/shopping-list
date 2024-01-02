package com.dexciuq.shoppinglist.di

import android.app.Application
import com.dexciuq.shoppinglist.data.db.AppDatabase
import com.dexciuq.shoppinglist.data.db.dao.ProductDao
import com.dexciuq.shoppinglist.data.repository.ProductRepositoryImpl
import com.dexciuq.shoppinglist.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    fun bindProductRepository(impl: ProductRepositoryImpl): ProductRepository

    companion object {

        @Provides
        fun provideAppDatabase(application: Application): AppDatabase {
            return AppDatabase.getInstance(application)
        }

        @Provides
        fun provideProductDao(appDatabase: AppDatabase): ProductDao {
            return appDatabase.productDao()
        }
    }
}