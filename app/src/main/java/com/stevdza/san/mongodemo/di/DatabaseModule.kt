package com.stevdza.san.mongodemo.di

import com.stevdza.san.mongodemo.data.MongoRepository
import com.stevdza.san.mongodemo.data.MongoRepositoryImpl
import com.stevdza.san.mongodemo.model.Address
import com.stevdza.san.mongodemo.model.Person
import com.stevdza.san.mongodemo.model.Pet
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideRealm(): Realm {
        val config = RealmConfiguration.Builder(
            schema = setOf(
                Person::class, Address::class, Pet::class
            )
        )
            .compactOnLaunch()
            .build()
        return Realm.open(config)
    }

    @Singleton
    @Provides
    fun provideMongoRepository(realm: Realm): MongoRepository {
        return MongoRepositoryImpl(realm = realm)
    }

}