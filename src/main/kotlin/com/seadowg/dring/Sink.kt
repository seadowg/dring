package com.seadowg.dring

interface Sink<T> {
  open fun process(item: T)
}
