package com.abedfattal.rexapixakt.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.abedfattal.rexapixakt.R


fun <T1, T2> combine(
    f1: LiveData<T1>,
    f2: LiveData<T2>,
    combine: (Pair<T1?, T2?>) -> R

): LiveData<Pair<T1?, T2?>> = MediatorLiveData<Pair<T1?, T2?>>().also { mediator ->
    mediator.value = Pair(f1.value, f2.value)

    mediator.addSource(f1) { t1: T1? ->
        val (_, t2) = mediator.value!!
        combine
        mediator.value = Pair(t1, t2)
    }

    mediator.addSource(f2) { t2: T2? ->
        val (t1, _) = mediator.value!!
        mediator.value = Pair(t1, t2)
    }
}

fun <R, T1, T2> MediatorLiveData<R>.addSources(
    sourceOne: LiveData<T1>,
    sourceTwo: LiveData<T2>,
    combine: (Pair<T1?, T2?>) -> R
): MediatorLiveData<R> {
    addSource(sourceOne) {
        value = combine(it to sourceTwo.value)
    }
    addSource(sourceTwo) {
        value = combine(sourceOne.value to it)
    }
    return this
}

fun <R, T1 : Any?, T2 : Any?, T3 : Any?> MediatorLiveData<R>.addSources(
    sourceOne: LiveData<T1>,
    sourceTwo: LiveData<T2>,
    sourceThree: LiveData<T3>,
    combine: (Triple<T1?, T2?, T3?>) -> R
): MediatorLiveData<R> {
    addSource(sourceOne) {
        value = combine(Triple(it, sourceTwo.value, sourceThree.value))
    }
    addSource(sourceTwo) {
        value = combine(Triple(sourceOne.value, it, sourceThree.value))
    }
    addSource(sourceThree) {
        value = combine(Triple(sourceOne.value, sourceTwo.value, it))
    }
    return this
}