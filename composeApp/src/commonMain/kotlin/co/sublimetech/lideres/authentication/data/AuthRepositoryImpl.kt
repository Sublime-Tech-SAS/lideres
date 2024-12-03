package co.sublimetech.lideres.authentication.data

import co.sublimetech.lideres.authentication.domain.AuthRepositoryInterface
import co.sublimetech.lideres.core.domain.DataError
import co.sublimetech.lideres.core.domain.Result
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class AuthRepositoryImpl : AuthRepositoryInterface {
    private val auth = Firebase.auth
    private val firestore = Firebase.firestore


    override suspend fun updateStorage(): Result<Boolean, DataError> {
        return withContext(Dispatchers.IO) {
            try {
                val currentTimestamp = Clock.System.now()
                val localDateTime = currentTimestamp.toLocalDateTime(TimeZone.currentSystemDefault())
                val date = localDateTime.date
                val time = localDateTime.time

                firestore.collection("prueba")
                    .document("syncTrigger")
                    .set(mapOf("timestamp" to "${time.hour}:${time.minute} $date"))

                Result.Success(true)
            } catch (e: Exception) {
                Result.Error(DataError.Network.UNKNOWN)
            }
        }

    }

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
    ): Result<String, DataError> {
        return withContext(Dispatchers.IO) {
            try {
                 val loginIntent = auth.signInWithEmailAndPassword(userEmail, userPassword)

                Result.Success(loginIntent.user?.email.toString())
            } catch (e: Exception) {
                Result.Error(e.message as DataError)
            }
        }
    }
}
