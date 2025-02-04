package com.example.xogamesapp.di

import android.content.Context
import androidx.room.Room
import com.example.xogamesapp.data.model.GameDatabase
import com.example.xogamesapp.data.model.GameHistoryDao
import com.example.xogamesapp.data.model.GameHistoryRepository
import com.example.xogamesapp.data.model.OfflineGamesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//object AppModule {
//
//    @Provides
//    @Singleton
//    fun provideDatabase(
//        @ApplicationContext appContext: Context
//    ): GameDatabase {
//        return Room.databaseBuilder(
//            appContext,
//            GameDatabase::class.java,
//            "game_history_db"
//        ).build()
//    }
//
//    @Provides
//    fun provideGameHistoryDao(db: GameDatabase): GameHistoryDao {
//        return db.gameHistoryDao()
//    }
//
//    @Provides
//    fun provideGameHistoryRepository(dao: GameHistoryDao): OfflineGamesRepository {
//        return OfflineGamesRepository(dao)
//    }
//}
//
///**
// * App container for Dependency injection.
// */
//interface AppContainer {
//    val itemsRepository: GameHistoryRepository
//}
//
///**
// * [AppContainer] implementation that provides instance of [OfflineGamesRepository]
// */
//class AppDataContainer(private val context: Context) : AppContainer {
//    /**
//     * Implementation for [GameHistoryRepository]
//     */
//    override val itemsRepository: GameHistoryRepository by lazy {
//        OfflineGamesRepository(InventoryDatabase.getDatabase(context).itemDao())
//    }
//}
