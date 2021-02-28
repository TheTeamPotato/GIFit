package com.theteampotato.gifit

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * Add this rule to your JUnit 4 tests
 */
@ExperimentalCoroutinesApi
class CoroutineTestRule(
    private val dispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
) : TestWatcher(), TestCoroutineScope by TestCoroutineScope(dispatcher) {

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        // This could also be just cleanupTestCoroutines()
        // But I'm not sure if this works so test it before you use it
        dispatcher.cleanupTestCoroutines()
        Dispatchers.resetMain()
    }
}