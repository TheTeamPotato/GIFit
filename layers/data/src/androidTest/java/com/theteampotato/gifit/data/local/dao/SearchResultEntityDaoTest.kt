package com.theteampotato.gifit.data.local.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest

import com.google.common.truth.Truth.assertThat

import com.theteampotato.gifit.TestUtil
import com.theteampotato.gifit.data.local.db.GIFitRoomDatabaseTest
import com.theteampotato.gifit.data.local.entity.SearchResultEntity
import com.theteampotato.gifit.testing.getOrAwaitValue

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@SmallTest
class SearchResultEntityDaoTest : GIFitRoomDatabaseTest() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var searchResultDao: SearchResultDao
    private lateinit var SearchResultEntity: SearchResultEntity
    private lateinit var listOfResultEntities: List<SearchResultEntity>

    @Before
    fun init() = runBlocking {
        searchResultDao = database.searchResultDao()
        SearchResultEntity = TestUtil.newSearch()
        listOfResultEntities = TestUtil.newListOfSearch()
    }

    @Test
    fun verify_inserted_testData_then_searchString_entity_should_be_equalTo_searchResult_searchString(): Unit = runBlocking {
        searchResultDao.insert(SearchResultEntity)
        val results = searchResultDao.getAll().getOrAwaitValue()
        assertThat(results[0].searchText).isEqualTo(SearchResultEntity.searchText)
    }

    @Test
    fun verify_deleted_testData_then_check_searchString_should_be_contain(): Unit = runBlocking {
        searchResultDao.insertAll(listOfResultEntities)
        searchResultDao.insert(SearchResultEntity)
        val result = searchResultDao.getResultById(2).getOrAwaitValue()
        searchResultDao.delete(result)
        val results = searchResultDao.getAll().getOrAwaitValue()
        assertThat(results[2].searchText).contains(SearchResultEntity.searchText)
    }

    @Test
    fun verify_updated_testData_searchString_then_isFavorite_entity_should_be_false(): Unit = runBlocking {
        searchResultDao.insertAll(listOfResultEntities)
        val results = searchResultDao.getAll().getOrAwaitValue()
        results.forEach {
            if (it.searchText == "Say Hello") {
                it.isFavorite = false
                searchResultDao.update(it)
            }
        }
        val updatedResults = searchResultDao.getAll().getOrAwaitValue()
        assertThat(updatedResults[0].isFavorite).isEqualTo(false)
    }

    @Test
    fun verify_getAll_insertedList_then_listSize_should_not_equalTo_zero(): Unit = runBlocking {
        searchResultDao.insertAll(listOfResultEntities)
        val results = searchResultDao.getAll().getOrAwaitValue()
        assertThat(results.size).isNotEqualTo(0)
    }

}