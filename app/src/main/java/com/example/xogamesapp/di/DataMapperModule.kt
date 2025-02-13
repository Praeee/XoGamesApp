package com.example.xogamesapp.di

import com.example.xogamesapp.data.model.GameHistoryEntity
import com.example.xogamesapp.game.model.GameHistory
import com.example.xogamesapp.game.model.toEntity
import com.example.xogamesapp.game.model.toGameHistory
import com.example.xogamesapp.mapper.AppDataMappers
import com.example.xogamesapp.utils.DataMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataMapperModule {

    @Provides
    fun providesGameHistoryToEntityMapper(): DataMapper<GameHistory, GameHistoryEntity> {
        return DataMapper {
            it.toEntity()
        }
    }

    @Provides
    fun providesGameHistoryEntityToModelMapper(): DataMapper<GameHistoryEntity, GameHistory> {
        return DataMapper {
            it.toGameHistory()
        }
    }

    @Provides
    fun providesAppDataMappers(
        gameHistoryToEntityMapper: DataMapper<GameHistory, GameHistoryEntity>,
        gameHistoryEntityToModelMapper: DataMapper<GameHistoryEntity, GameHistory>
    ): AppDataMappers {
        return AppDataMappers(
            gameHistoryToEntityMapper,
            gameHistoryEntityToModelMapper
        )
    }


}