package com.frommetoyou.common

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import java.math.BigInteger
import java.text.SimpleDateFormat
import java.util.Date

fun Context.findAndroidActivity(): Activity? {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    return null
}

fun factorialBig(n: Int): BigInteger {
    require(n >= 0) { "El número debe ser mayor o igual a cero." }
    return (1..n).map { it.toBigInteger() }
        .fold(BigInteger.ONE) { acc, i -> acc.multiply(i) }
}

fun formatDate(millis: Long): String {
    val date = Date(millis)
    val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss") // Formato que prefieras
    return sdf.format(date)
}