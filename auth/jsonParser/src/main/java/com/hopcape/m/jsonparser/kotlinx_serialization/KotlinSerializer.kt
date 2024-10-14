package com.hopcape.m.jsonparser.kotlinx_serialization

import com.hopcape.m.jsonparser.JsonParser

internal class KotlinSerializer constructor(): JsonParser {

    override fun <T> toJson(data: T): String {
        return ""
    }

    override fun <T> fromJson(
        json: String,
        clazz: Class<T>
    ): T {
        TODO("Operation not implemented yet")
    }
}