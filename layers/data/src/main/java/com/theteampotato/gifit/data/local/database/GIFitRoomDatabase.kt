package com.theteampotato.gifit.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.theteampotato.gifit.data.local.dao.SearchResultDao
import com.theteampotato.gifit.data.local.entity.SearchResultEntity

@Database(
    entities = [SearchResultEntity::class],
    version = 1,
    exportSchema = false
)
abstract class GIFitRoomDatabase : RoomDatabase() {

    abstract fun searchResultDao(): SearchResultDao

    companion object {
        @Volatile
        private var instance: GIFitRoomDatabase? = null

        fun getDatabase(context: Context): GIFitRoomDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, GIFitRoomDatabase::class.java, "GIFitRoomDatabase")
                .fallbackToDestructiveMigration()
                .build()
    }

}