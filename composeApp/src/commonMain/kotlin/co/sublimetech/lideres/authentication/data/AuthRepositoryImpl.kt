package co.sublimetech.lideres.authentication.data

import co.sublimetech.lideres.authentication.domain.AuthRepositoryInterface
import co.sublimetech.lideres.core.domain.Result
import co.sublimetech.lideres.core.domain.DataError
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class AuthRepositoryImpl : AuthRepositoryInterface {
    private val auth = Firebase.auth

    override suspend fun registerUser(
        userEmail: String,
        userPassword: String,
    ): Result<Boolean, DataError.Network> {
        return withContext(Dispatchers.IO) {
            try {
                val registerIntent = auth.createUserWithEmailAndPassword(userEmail, userPassword)
                Result.Success(registerIntent.user?.email != null)
            } catch (e: Exception) {
                Result.Error(DataError.Network.UNKNOWN)
            }
        }
    }

    override suspend fun loginUser(
        userEmail: String,
        userPassword: String,
    ): Result<String, DataError.Network> {
        return withContext(Dispatchers.IO) {
            try {
                val loginIntent = auth.signInWithEmailAndPassword(userEmail, userPassword)
                Result.Success(loginIntent.user?.uid!!)
            }  catch (e: Exception) {
                Result.Error(DataError.Network.UNKNOWN)
            }
        }
    }
}