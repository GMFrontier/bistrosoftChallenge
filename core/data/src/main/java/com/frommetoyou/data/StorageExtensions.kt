package com.frommetoyou.data

fun <T> T.parseData(): Result<T> = Result.success(this)