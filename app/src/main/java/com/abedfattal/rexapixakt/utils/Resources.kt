package com.abedfattal.rexapixakt.utils

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.abedfattal.rexapixakt.RexaPixaApplication


fun colorOf(colorID: Int, context: Context = RexaPixaApplication.app): Int =
    ContextCompat.getColor(context, colorID)

fun drawableOf(@DrawableRes drawableImg: Int, context: Context = RexaPixaApplication.app) =
    ContextCompat.getDrawable(context, drawableImg)

fun stringer(stringID: Int, mContext: Context = RexaPixaApplication.app): String =
    mContext.resources.getString(stringID)

fun stringify(stringID: Int, mContext: Context = RexaPixaApplication.app): String =
    mContext.resources.getString(stringID)



