package com.example.xogamesapp.di

import android.app.Application
import androidx.room.Room
import com.example.xogamesapp.data.model.GameDatabase
import com.example.xogamesapp.data.model.GameHistoryDao
import com.example.xogamesapp.data.model.GameHistoryRepository
import com.example.xogamesapp.data.model.GameHistoryRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideGameHistoryDao(db: GameDatabase): GameHistoryDao {
        return db.gameHistoryDao()
    }

    @Provides
    @Singleton
    fun provideGameHistoryRepository(dao: GameHistoryDao): GameHistoryRepository {
        return GameHistoryRepositoryImpl(dao)
    }

    @Provides
    @Singleton
    fun provideMasterDatabase(app: Application): GameDatabase {
        return Room.databaseBuilder(
            app,
            GameDatabase::class.java,
            "game_history_database",
        ).fallbackToDestructiveMigration().build()
    }

}
