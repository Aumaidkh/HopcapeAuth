package com.hopcape.m.encryptionmanager.manager

import com.hopcape.m.encryptionmanager.decryptor.Decryptor
import com.hopcape.m.encryptionmanager.encryptor.Encryptor
import javax.inject.Inject

class EncryptionManagerImpl @Inject constructor(
    private val encryptor: Encryptor,
    private val decryptor: Decryptor
): EncryptionManager {
    override fun encrypt(string: String): ByteArray {
        return encryptor.encrypt(data = string)
    }

    override fun decrypt(data: ByteArray): String {
        return decryptor.decrypt(data = data)
    }
}