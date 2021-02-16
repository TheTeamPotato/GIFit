package com.theteampotato.gifit.data.local.db

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry

import com.theteampotato.gifit.data.local.database.GIFitRoomDatabase

import kotlinx.coroutines.runBlocking

import org.junit.After
import org.junit.Before

abstract class GIFitRoomDatabaseTest {

    lateinit var database: GIFitRoomDatabase

    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, GIFitRoomDatabase::class.java).build()
    }

    @After
    fun closeDb() {
        database.close()
    }

}