package com.example.recyclerviewsample.common

/**
 * Маппер для перевода одного типа в другой
 */
interface Mapper<IN, OUT> {
    fun map(from: IN): OUT
    fun map(from: List<IN>): List<OUT> = from.map { map(it) }
}
