package me.androidbox.presentation.rules

import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.TestRule
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.junit.runners.model.Statement

class MockWebServerRule : TestWatcher() {

    private val mockWebServer by lazy {
        MockWebServer()
    }

    override fun starting(description: Description?) {
        super.starting(description)
        mockWebServer.start(8080)
    }

    override fun finished(description: Description?) {
        mockWebServer.shutdown()
        super.finished(description)
    }

}