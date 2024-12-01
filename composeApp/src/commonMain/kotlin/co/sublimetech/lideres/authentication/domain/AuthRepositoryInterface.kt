package co.sublimetech.lideres.authentication.domain

import co.sublimetech.lideres.core.domain.DataError
import co.sublimetech.lideres.core.domain.Result

interface AuthRepositoryInterface {

    suspend fun registerUser(userEmail: String, userPassword: String): Result<Boolean, DataError>
    suspend fun loginUser(userEmail: String, userPassword: String): Result<String, DataError>


}