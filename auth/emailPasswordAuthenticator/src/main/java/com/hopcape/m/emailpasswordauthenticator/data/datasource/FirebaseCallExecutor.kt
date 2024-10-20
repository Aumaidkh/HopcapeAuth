package com.hopcape.m.emailpasswordauthenticator.data.datasource

import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.hopcape.m.common.AuthResult
import com.hopcape.m.common.error.DataError

inline fun <D> safeFirebaseCall(
    onFailure: (Exception) -> Unit = {},
    block: () -> AuthResult.Success<D, DataError>
): AuthResult<D, DataError> {
    return try {
        block()
    } catch (e: FirebaseAuthInvalidUserException) {
        onFailure(e)
        AuthResult.Error(DataError.USER_NOT_FOUND)
    } catch (e: FirebaseAuthInvalidCredentialsException) {
        onFailure(e)
        AuthResult.Error(DataError.INVALID_EMAIL_AND_PASSWORD)
    } catch (e: FirebaseAuthUserCollisionException) {
        onFailure(e)
        AuthResult.Error(DataError.ERROR_EMAIL_ALREADY_IN_USE)
    } catch (e: FirebaseAuthWeakPasswordException) {
        onFailure(e)
        AuthResult.Error(DataError.ERROR_WEAK_PASSWORD)
    } catch (e: FirebaseNetworkException) {
        onFailure(e)
        AuthResult.Error(DataError.CONNECTION_ERROR)
    } catch (e: Exception) {
        onFailure(e)
        AuthResult.Error(DataError.UNKNOWN_ERROR)
    }
}