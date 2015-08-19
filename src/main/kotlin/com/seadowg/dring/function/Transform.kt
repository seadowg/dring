package com.seadowg.dring.function

interface Transform<T, Y> {
    fun transform(item: T): Y
}