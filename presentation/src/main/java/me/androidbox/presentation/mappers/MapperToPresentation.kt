package me.androidbox.presentation.mappers

interface MapperToPresentation<in D, out P> {
    fun map(domain: D): P
}
