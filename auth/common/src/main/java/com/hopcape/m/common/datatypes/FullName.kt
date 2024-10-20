package com.hopcape.m.common.datatypes

@JvmInline
value class FullName (val value: String) {
    val isBlank get() = value.isBlank()
    val isEmpty get() = value.isEmpty()
    val length get() = value.length
    val isValid get() = value.contains("")
}