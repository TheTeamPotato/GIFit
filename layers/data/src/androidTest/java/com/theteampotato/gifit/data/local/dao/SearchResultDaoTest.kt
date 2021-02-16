package com.theteampotato.gifit.data.local.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest

import com.google.common.truth.Truth.assertThat

import com.theteampotato.gifit.TestUtil
import com.theteampotato.gifit.data.local.db.GIFitRoomDatabaseTest
import com.theteampotato.gifit.data.local.entity.SearchResult
import com.theteampotato.gifit.getOrAwaitValue

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@SmallTest
class SearchResultDaoTest : GIFitRoomDatabaseTest() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var searchResultDao: SearchResultDao
    private lateinit var searchResult: SearchResult
    private lateinit var listOfResults: List<SearchResult>

    @Before
    fun init() = runBlocking {
        searchResultDao = database.searchResultDao()
        searchResult = TestUtil.newSearch()
        listOfResults = TestUtil.newListOfSearch()
    }

    @Test
    fun verify_inserted_testData_then_searchString_entity_should_be_equalTo_searchResult_searchString(): Unit = runBlocking {
        searchResultDao.insert(searchResult)
        val results = searchResultDao.getAll().getOrAwaitValue()
        assertThat(results[0].searchString).isEqualTo(searchResult.searchString)
    }

    @Test
    fun verify_deleted_testData_then_check_searchString_should_be_contain(): Unit = runBlocking {
        searchResultDao.insertAll(listOfResults)
        searchResultDao.insert(searchResult)
        val result = searchResultDao.getResultById(2).getOrAwaitValue()
        searchResultDao.delete(result)
        val results = searchResultDao.getAll().getOrAwaitValue()
        assertThat(results[2].searchString).contains(searchResult.searchString)
    }

    @Test
    fun verify_updated_testData_searchString_then_isFavorite_entity_should_be_false(): Unit = runBlocking {
        searchResultDao.insertAll(listOfResults)
        val results = searchResultDao.getAll().getOrAwaitValue()
        results.forEach {
            if (it.searchString == "Say Hello") {
                it.isFavorite = false
                searchResultDao.update(it)
            }
        }
        val updatedResults = searchResultDao.getAll().getOrAwaitValue()
        assertThat(updatedResults[0].isFavorite).isEqualTo(false)
    }

    @Test
    fun verify_getAll_insertedList_then_listSize_should_not_equalTo_zero(): Unit = runBlocking {
        searchResultDao.insertAll(listOfResults)
        val results = searchResultDao.getAll().getOrAwaitValue()
        assertThat(results.size).isNotEqualTo(0)
    }

}