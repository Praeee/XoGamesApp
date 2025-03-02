package com.example.data.database

import android.app.Application
import androidx.room.Room
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
