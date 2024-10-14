package com.hopcape.m.encryptionmanager.decryptor

interface Decryptor {

    fun decrypt(data: ByteArray): String
}