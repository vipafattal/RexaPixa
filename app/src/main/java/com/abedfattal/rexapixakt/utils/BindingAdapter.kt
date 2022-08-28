package com.abedfattal.rexapixakt.utils

import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.material.chip.Chip
import com.google.android.material.textfield.TextInputLayout
import kotlin.math.roundToInt

class BindingAdapters {
    companion object {


        @JvmStatic
        @BindingAdapter("android:url")
        fun ImageView.loadImage(url: String?) {
            Glide.with(context).load(url)
                .centerCrop()
                .into(this)
        }

        @JvmStatic
        @BindingAdapter("android:url", "android:imageRadius")
        fun ImageView.loadImage(url: String?, radius: Float) {
            Glide.with(context).load(url)
                .transform(CenterCrop(), RoundedCorners(radius.toInt()))
                .into(this)
        }

        @JvmStatic
        @set:BindingAdapter("android:isVisible")
        inline var View.isVisible: Boolean
            get() = visibility == View.VISIBLE
            set(value) {
                visibility = if (value) View.VISIBLE else View.GONE
            }

        @JvmStatic
        @BindingAdapter("android:setErrorText")
        fun TextInputLayout.setErrorText(errorMessage: String?) {
            error = errorMessage
        }

        @JvmStatic
        @BindingAdapter("android:onClickNavigateTo")
        fun View.onClicks(direction: NavDirections) {
            setOnClickListener {
                findNavController().navigate(direction)
            }
        }
        @JvmStatic
        @BindingAdapter("android:onClickNavigateTo")
        fun View.onClickNavigation(action: Int) {
            setOnClickListener {
                findNavController().navigate(action)
            }
        }

        @JvmStatic
        @BindingAdapter("android:setTextWatcher")
        fun EditText.addTextWatcher(textWatcher: MutableLiveData<String?>) {
            addTextChangedListener {
                textWatcher.value = it.toString()
            }
        }

        @JvmStatic
        @BindingAdapter("android:setTextWatcher")
        fun EditText.addTextWatcher(textWatcher: TextWatcher?) {
            if (textWatcher != null)
                addTextChangedListener(textWatcher)
        }


    }
}