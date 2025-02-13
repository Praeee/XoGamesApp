package com.example.xogamesapp.utils

fun interface Mapper<I, O> {
    fun map(input: I): O
}

open class DataMapper<I, O>(
    private val mapping: (I) -> O,
) : Mapper<I, O> {
    override fun map(input: I): O = mapping(input)
}