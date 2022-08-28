package com.abedfattal.rexapixakt.framework.utils

import android.util.Log
import com.abedfattal.rexapixakt.R
import com.abedfattal.rexapixakt.models.ProcessState
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.IOException
import java.net.ProtocolException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by ${User} on ${Date}
 */

/** @suppress */
internal inline fun <reified T> newRequest(
    crossinline requestBlock: suspend () -> Response<T>
) = flow<ProcessState<T>> {
    onRequestCompleted(
        request = {
            emit(ProcessState.Loading())
            requestBlock()
        },
        onSuccess = { emit(ProcessState.Success(it)) },
        onError = { reason, userMsg -> emit(ProcessState.Failed(reason, userMsg)) }
    )
}

@PublishedApi
internal inline fun <reified T> onRequestCompleted(
    request: () -> Response<T>,
    onSuccess: (data: T) -> Unit,
    onError: (reason: String?, friendlyMsg: Int) -> Unit
) {

    var userErrorMsg: Int = R.string.unknown_error
    var errorReason: String? = "Unknown error"
    var data: T? = null
    val className = T::class.java.name
    var errorCode = R.string.unknown_error

    try {

        val response = request()

        if (response.isSuccessful)
            data = response.body()
        else {
            errorCode = response.code()
            errorReason = (response.errorBody() as ResponseBody).string()
            printLog("Unknown error", "for $className,reason: $errorReason")
        }

    } catch (e: UnknownHostException) {
        printLog("Get response for $className", e.message ?: "Unknown error")
        userErrorMsg = R.string.no_internet
    } catch (io: SocketTimeoutException) {
        printLog("Get response for $className", io.message ?: "Unknown error")
        errorReason = io.message
        userErrorMsg = R.string.timeout
    } catch (so: SocketException) {
        printLog("Get response for $className", so.message ?: "Unknown error")
        errorReason = so.message
        userErrorMsg = R.string.connection_reset
    } catch (pro: ProtocolException) {
        printLog("Get response for $className", pro.message ?: "Unknown error")
        errorReason = pro.message

        userErrorMsg = R.string.connection_error
    } catch (io: IOException) {
        printLog("Get response for $className", io.message ?: "Unknown error")
        userErrorMsg = R.string.service_not_available
        errorReason = io.message
    }

    if (data != null) onSuccess(data)
    else onError("reason:$errorReason, code:$errorCode", userErrorMsg)
}

fun printLog(tag: String, msg: String) {
    Log.d(tag, msg)
}

