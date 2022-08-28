package com.abedfattal.rexapixakt.framework.utils

import com.abedfattal.rexapixakt.models.ProcessState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

/**
 * Sometimes we can't control the data type of the [ProcessState], e.g. when have fixed types in remote API response.
 *
 * @param block is only class the process is [ProcessState.Success], since it's the only one that have data.
 */
inline fun <reified Data, T : ProcessState<Data>, R> Flow<T>.transform(
    crossinline block: suspend (Data) -> R
): Flow<ProcessState<R>> {
    return map {
        if (it is ProcessState.Success<*>)
            ProcessState.Success(block(it.data as Data))
        else
            it.transformProcessType<R>()
    }
}


/**
 * Execute an action when flow process is [ProcessState.Success].
 *
 */
suspend inline infix fun <T> Flow<ProcessState<T>>.doOnSuccess(crossinline block: suspend (T?) -> Unit): Flow<ProcessState<T>> {
    return onEach {
        if (it is ProcessState.Success<T>)
            block(it.data)

    }
}

/**
 * Execute an action when flow process is [ProcessState.Failed].
 *
 */
suspend inline infix fun Flow<ProcessState<*>>.doOnFailure(crossinline block: suspend () -> Unit): Flow<ProcessState<*>> {
    return onEach {
        if (it is ProcessState.Failed<*>)
            block()

    }
}


/**
 * Create new flow that only if [ProcessState.Success] will emit values.
 *
 */
inline val <reified T> Flow<ProcessState<T>>.onSuccess: Flow<T?>
    get() = flow<T?> {
        this@onSuccess.collect {
            if (it is ProcessState.Success<*>)
                emit(it.data as? T)
        }
    }

inline val <reified T> Flow<ProcessState<T>>.onFail: Flow<ProcessState.Failed<T>>
    get() = flow {
        this@onFail.collect {
            if (it is ProcessState.Failed<T>)
                emit(it)
        }
    }

inline val <reified T> Flow<ProcessState<T>>.onComplete: Flow<ProcessState.CompletableProcess<T>>
    get() = flow {
        this@onComplete.collect {
            if (it is ProcessState.CompletableProcess)
                emit(it)
        }
    }

fun <T> T.toSuccessProcess(exception: Exception? = null): ProcessState.Success<T> {
    return ProcessState.Success(this, exception)
}

