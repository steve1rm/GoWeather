package me.androidbox.presentation.rules

import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class MockWebServerRule : TestRule {
    override fun apply(base: Statement?, description: Description?): Statement {
        return object: Statement() {
            override fun evaluate() {
                val mockWebServer = MockWebServer()
                mockWebServer.start(8080)
                base?.evaluate()
                mockWebServer.shutdown()
            }
        }
    }
}
