package com.ovais.common

interface Mapper<F, T> {
    fun map(from: F): T
}