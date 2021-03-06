package com.kryptkode.core.utils

import com.kryptkode.core.utils.FakeDataFactory.getJson
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

fun MockWebServer.mockHttpResponse(fileName: String, responseCode: Int) = enqueue(
        MockResponse()
                .setResponseCode(responseCode)
                .setBody(getJson(fileName))
)
