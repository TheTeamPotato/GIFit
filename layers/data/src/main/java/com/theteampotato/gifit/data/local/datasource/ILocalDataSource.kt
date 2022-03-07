package com.theteampotato.gifit.data.local.datasource

import androidx.lifecycle.LiveData

interface ILocalDataSource<T> {

    suspend fun insert(entity: T) : Long

    suspend fun insertAll(entities: List<T>)

    suspend fun update(entity: T)

    suspend fun delete(entity: T)

    suspend fun get(id : Int) : LiveData<T>

    suspend fun getAll(): LiveData<List<T>>

}