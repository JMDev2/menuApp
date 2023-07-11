package com.morarafrank.template.di

import android.content.Context
import androidx.room.Room
import com.morarafrank.template.db.model.MenuDao
import com.morarafrank.template.db.model.MenuDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesMenuDao(
        db: MenuDb
    ) = db.menuDao()

    @Provides
    @Singleton
    fun providesMenuDb(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        MenuDb::class.java,
        "menuDb"
    )
        .fallbackToDestructiveMigration()
        .build()


}