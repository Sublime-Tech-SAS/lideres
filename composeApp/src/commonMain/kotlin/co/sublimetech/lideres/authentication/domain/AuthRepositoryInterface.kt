package co.sublimetech.lideres.authentication.domain

import co.sublimetech.lideres.core.domain.DataError
import co.sublimetech.lideres.core.domain.Result

interface AuthRepositoryInterface {

    suspend fun updateStorage(): Result<Boolean, DataError>
    suspend fun loginUser(tokenId: String, accessToken: String): Result<Boolean, DataError>
    suspend fun validateActiveUser(userId: String): Result<Boolean, DataError>
    suspend fun logout()


}