package com.ff.challenge.library.base.data.interceptor

import android.content.Context
import android.os.SystemClock
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import timber.log.Timber
import java.io.IOException
import java.net.URI
import java.util.Locale

@Suppress("detekt.UnsafeCast")
class FakeInterceptor constructor(private val context: Context) : Interceptor {

    companion object {
        private const val contentType = "application/json;charset=UTF-8"
    }

    override fun intercept(chain: Interceptor.Chain): Response {

        SystemClock.sleep(1000)

        var response: Response? = null
        val uri = chain.request().url().uri()
        val randomCode = 200
        val defaultFileName = getFileName(chain)
        val suggestionFileName = defaultFileName?.toLowerCase(Locale.getDefault())!!
        val fileName: String = getFilePath(uri, suggestionFileName) + ".json"

        try {

            val responseString = context.assets?.open(fileName)?.bufferedReader().use {
                it?.readText()
            }

            val builder = Response.Builder().code(randomCode)
                .message(responseString.toString())
                .request(chain.request())
                .protocol(Protocol.HTTP_1_0)
                .body(
                    ResponseBody.create(
                        MediaType.get(contentType),
                        responseString.toString().toByteArray()
                    )
                )
                .addHeader("content-type", contentType)

            response = builder.build()

        } catch (e: IOException) {
            Timber.e(e, "Error: %s", e.message)
        }

        return response!!
    }

    private fun getFilePath(uri: URI, fileName: String): String {
        val path: String = if (uri.path.lastIndexOf('/') != uri.path.length - 1) {
            uri.path.substring(0, uri.path.lastIndexOf('/') + 1)
        } else {
            uri.path
        }
        return uri.host + path.toLowerCase(Locale.getDefault()) + fileName
    }

    private fun getFileName(chain: Interceptor.Chain): String? {
        val fileName =
            chain.request().url().pathSegments()[chain.request().url().pathSegments().size - 1]
        return if (fileName.isEmpty()) "index" else fileName + "_" + chain.request().method()
            .toLowerCase(
                Locale.getDefault()
            )
    }
}