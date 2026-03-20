package com.itvo.sales.di

import android.content.Context
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import com.itvo.sales.data.local.dao.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.itvo.sales.data.local.SalesDatabase

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): SalesDatabase {

        return databaseBuilder(
            context,
            SalesDatabase::class.java,
            "sales.db"
        ).build()
    }

    @Provides
    fun provideProductDao(
        database: SalesDatabase
    ): ProductDao {
        return database.productDao()
    }


}
