package com.dreamsoftware.coins4all.core.di

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import com.dreamsoftware.coins4all.core.constants.Constants.BASE_URL
import com.dreamsoftware.coins4all.data.local.room.Converters
import com.dreamsoftware.coins4all.data.local.room.Coins4AllDatabase
import com.dreamsoftware.coins4all.data.remote.api.Coins4allApi
import com.dreamsoftware.coins4all.data.repository.Coins4AllRepositoryImpl
import com.dreamsoftware.coins4all.data.utils.MoshiParser
import com.dreamsoftware.coins4all.domain.repository.Coins4AllRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCoins4AllApi(): Coins4allApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(Coins4allApi::class.java)

    @Provides
    @Singleton
    fun provideCoins4AllRepository(api: Coins4allApi, db: Coins4AllDatabase): Coins4AllRepository =
        Coins4AllRepositoryImpl(
            api,
            db.coinDao,
            db.overviewDao,
            db.coinDetailDao,
            db.coinCurrencyDao,
            db.favoriteCoinsDao
        )

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext applicationContext: Context,
        converter: Converters
    ): Coins4AllDatabase = Room.databaseBuilder(
        applicationContext,
        Coins4AllDatabase::class.java,
        Coins4AllDatabase.DATABASE_NAME
    ).addTypeConverter(converter).build()

    @Provides
    fun provideTypeConverter(): Converters = Converters(MoshiParser(Moshi.Builder().build()))

}