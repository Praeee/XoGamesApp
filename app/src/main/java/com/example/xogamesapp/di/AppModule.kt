package com.example.xogamesapp.di

import com.example.xogamesapp.domain.DeleteGameHistoryByIdUseCase
import com.example.xogamesapp.domain.DeleteGameHistoryByIdUseCaseImpl
import com.example.xogamesapp.domain.GetAllGameHistoryUseCase
import com.example.xogamesapp.domain.GetAllGameHistoryUseCaseImpl
import com.example.xogamesapp.domain.GetGameHistoryByIdUseCase
import com.example.xogamesapp.domain.GetGameHistoryByIdUseCaseImpl
import com.example.xogamesapp.domain.InsertGameHistoryUseCase
import com.example.xogamesapp.domain.InsertGameHistoryUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
@Suppress("UNUSED")
abstract class DomainSingletonModule {

    @Binds
    abstract fun bindsInsertGameHistoryUseCase(
        insertGameHistoryUseCaseImpl: InsertGameHistoryUseCaseImpl
    ): InsertGameHistoryUseCase

    @Binds
    abstract fun bindsGetAllGameHistoryUseCase(
        getAllGameHistoryUseCaseImpl: GetAllGameHistoryUseCaseImpl
    ): GetAllGameHistoryUseCase

    @Binds
    abstract fun bindsGetGameHistoryByIdUseCase(
        getGameHistoryByIdUseCaseImpl: GetGameHistoryByIdUseCaseImpl
    ): GetGameHistoryByIdUseCase

    @Binds
    abstract fun bindsDeleteGameHistoryByIdUseCase(
        deleteGameHistoryByIdUseCaseImpl: DeleteGameHistoryByIdUseCaseImpl
    ): DeleteGameHistoryByIdUseCase

}