package com.ff.challenge.library.base.presentation.extension

import android.os.Build
import timber.log.Timber
import java.security.spec.KeySpec
import java.util.*
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

object EncryptExtension {

    private const val SECRET_FACTORY_KEY = "PBKDF2WithHmacSHA256"
    private const val AES_KEY = "AES"
    private const val ITERATION_COUNT = 65536
    private const val KEY_LENGTH = 256
    private const val CIPHER_KEY = "AES/CBC/PKCS5Padding"
    private const val UTF8_KEY = "UTF-8"
    private const val EMPTY_STRING = ""

    fun encrypt(strToEncrypt: String, secret: String, salt: String): String {
        try {
            val iv = byteArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

            val ivSpec = IvParameterSpec(iv)
            val factory: SecretKeyFactory = SecretKeyFactory.getInstance(SECRET_FACTORY_KEY)
            val spec: KeySpec = PBEKeySpec(
                secret.toCharArray(), salt.toByteArray(), ITERATION_COUNT, KEY_LENGTH
            )
            val tmp: SecretKey = factory.generateSecret(spec)
            val secretKey = SecretKeySpec(tmp.encoded, AES_KEY)
            val cipher = Cipher.getInstance(CIPHER_KEY)
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec)

            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Base64.getEncoder().encodeToString(
                    cipher.doFinal(strToEncrypt.toByteArray(charset(UTF8_KEY)))
                )
            } else {
                EMPTY_STRING
            }
        } catch (e: Exception) {
            Timber.e("Error while encrypting: $e")
        }
        return EMPTY_STRING
    }
}
