package com.abedfattal.rexapixakt.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.abedfattal.rexapixakt.R


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