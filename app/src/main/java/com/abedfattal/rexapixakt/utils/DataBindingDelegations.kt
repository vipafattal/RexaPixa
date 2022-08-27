package com.abedfattal.rexapixakt.utils

import android.app.Activity
import android.view.View
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ActivityBindingProvider<out T : ViewDataBinding>(@LayoutRes private val layoutRes: Int) :
    ReadOnlyProperty<Activity, T> {

    private var binding: T? = null

    override operator fun getValue(thisRef: Activity, property: KProperty<*>): T {
        return binding ?: createBinding(thisRef).also { binding = it }
    }

    private fun createBinding(activity: Activity): T {
        return DataBindingUtil.setContentView(activity, layoutRes)
    }
}





