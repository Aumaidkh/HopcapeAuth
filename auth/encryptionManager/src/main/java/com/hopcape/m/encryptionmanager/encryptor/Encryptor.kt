package com.hopcape.m.encryptionmanager.encryptor

interface Encryptor {

    fun encrypt(data: String): ByteArray

}