package com.theteampotato.gifit.data.local.repository

import androidx.lifecycle.LiveData

interface BaseRepository<T> {

    suspend fun insert(entity : T)

    suspend fun insertAll(entities: List<T>)

    suspend fun delete(entity: T)

    suspend fun update(entity: T)

    suspend fun get(id: Int) : LiveData<T>

    suspend fun getAll() : LiveData<List<T>>

}