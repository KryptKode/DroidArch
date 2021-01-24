package com.kryptkode.core.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kryptkode.core.cache.converter.DateConverter
import com.kryptkode.core.cache.converter.LocationConverter
import com.kryptkode.core.cache.converter.StringListConverter
import com.kryptkode.core.cache.keys.UserRemoteKeys
import com.kryptkode.core.cache.keys.UserRemoteKeysDao
import com.kryptkode.core.cache.user.DbUser
import com.kryptkode.core.cache.user.UserDao

/**
 * Database schema that holds the list of repos.
 */
@Database(
    entities = [DbUser::class, UserRemoteKeys::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(
    StringListConverter::class,
    DateConverter::class,
    LocationConverter::class,
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun userRemoteKeysDao(): UserRemoteKeysDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "droidArch.db"
            )
                .build()
    }
}
