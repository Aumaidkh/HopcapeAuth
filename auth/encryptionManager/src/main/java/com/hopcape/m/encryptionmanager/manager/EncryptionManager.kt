package com.hopcape.m.encryptionmanager.manager

interface EncryptionManager {

    fun encrypt(string: String): ByteArray

    fun decrypt(data: ByteArray): String
}