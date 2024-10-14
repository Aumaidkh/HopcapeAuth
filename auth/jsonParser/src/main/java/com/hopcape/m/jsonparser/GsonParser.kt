package com.hopcape.m.jsonparser

import com.google.gson.Gson
import javax.inject.Inject

internal class GsonParser @Inject constructor(
    private val gson: Gson
): JsonParser {

    override fun <T> toJson(data: T): String {
        return gson.toJson(data)
    }

    override fun <T> fromJson(
        json: String,
        clazz: Class<T>
    ): T {
        return gson.fromJson(json,clazz)
    }
}