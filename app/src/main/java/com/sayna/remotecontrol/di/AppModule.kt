package com.sayna.remotecontrol.di

import android.app.Application
import androidx.room.Room
import com.sayna.remotecontrol.feature_rc_action.data.data_source.RCActionDatabase
import com.sayna.remotecontrol.feature_rc_action.data.repository.RCActionRepositoryImpl
import com.sayna.remotecontrol.feature_rc_action.domain.repository.RCActionRepository
import com.sayna.remotecontrol.feature_rc_action.domain.use_case.AddRCActionUseCase
import com.sayna.remotecontrol.feature_rc_action.domain.use_case.DeleteRCActionUseCase
import com.sayna.remotecontrol.feature_rc_action.domain.use_case.GetRCActionUseCase
import com.sayna.remotecontrol.feature_rc_action.domain.use_case.GetRCActionsUseCase
import com.sayna.remotecontrol.feature_rc_action.domain.use_case.RCActionUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun ProvideRCActionDatabase(app: Application): RCActionDatabase {
        return Room.databaseBuilder(
            app,
            RCActionDatabase::class.java,
            RCActionDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun ProvideRCActionRepository(db: RCActionDatabase): RCActionRepository {
        return RCActionRepositoryImpl(db.rcActionDao)
    }

    @Provides
    @Singleton
    fun ProvideRCActionUseCases(repository: RCActionRepository): RCActionUseCases {
        return RCActionUseCases(
            getRCActionsUseCase = GetRCActionsUseCase(repository),
            getRCActionUseCase = GetRCActionUseCase(repository),
            deleteRCActionUseCase = DeleteRCActionUseCase(repository),
            addRCActionUseCase = AddRCActionUseCase(repository)
        )
    }
}