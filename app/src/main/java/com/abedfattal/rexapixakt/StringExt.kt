package com.abedfattal.rexapixakt

import java.lang.Integer.parseInt
import java.lang.NumberFormatException

fun String.tryParse(): Int? {
    return try { parseInt(this) }
    catch (e: NumberFormatException) { null }
}