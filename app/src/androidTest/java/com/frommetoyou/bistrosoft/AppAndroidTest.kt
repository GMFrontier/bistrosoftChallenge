package com.frommetoyou.bistrosoft

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.frommetoyou.data.datasource.Database
import dagger.hilt.android.testing.HiltAndroidRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject

abstract class AppAndroidTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var database: Database

    protected lateinit var context: Context

    @Before
    open fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        hiltRule.inject()
        database.clearAllTables()
    }

    @After
    open fun tearDown() {
        database.close()
    }

}