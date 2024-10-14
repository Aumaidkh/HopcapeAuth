package com.hopcape.m.jsonparser

interface JsonParser {

    fun <T> toJson(data: T): String

    fun <T> fromJson(json: String,clazz: Class<T>): T
}