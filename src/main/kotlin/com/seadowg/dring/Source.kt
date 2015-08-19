package com.seadowg.dring

import com.seadowg.dring.function.Transform

open class Source<T> {
  val sources = arrayListOf<Sink<T>>()

  fun bind(source: Sink<T>) {
    this.sources.add(source)
  }

  fun push(item: T) {
    this.sources.forEach { source -> source.process(item) }
  }

  fun <Y> map(transform: Transform<T, Y>): Source<Y> {
    return map { item -> transform.transform(item) }
  }

  fun <Y> map(transform: (T) -> Y): Source<Y> {
    val newSource = TransformedSource(transform)
    bind(newSource)

    return newSource
  }

  private class TransformedSource<T, Y>(val transform: (T) -> Y): Source<Y>(), Sink<T> {
    override fun process(item: T) {
      push(transform(item))
    }
  }
}
