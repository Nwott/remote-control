package com.sayna.remotecontrol.di

import android.content.Context
import android.hardware.ConsumerIrManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(Singleton::class)
object ConsumerIrModule {
    @Provides
    @Singleton
    fun ProvideConsumerIrManager(@ApplicationContext context: Context): ConsumerIrManager?
    {
        return context.getSystemService(Context.CONSUMER_IR_SERVICE) as ConsumerIrManager?
    }
}