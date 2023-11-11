package com.ovais.common.utils

fun String?.default(value: String? = null) = this ?: value ?: EMPTY_STRING
fun Int?.default(value: Int? = null) = this ?: value ?: 0
fun Long?.default(value: Long? = null) = this ?: value ?: 0L
fun Boolean.default(value:Boolean? = null) =  this ?: value ?: false