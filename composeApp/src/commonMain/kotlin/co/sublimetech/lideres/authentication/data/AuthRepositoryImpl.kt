package co.sublimetech.lideres.authentication.data

import co.sublimetech.lideres.authentication.data.dto.User
import co.sublimetech.lideres.authentication.domain.AuthRepositoryInterface
import co.sublimetech.lideres.core.domain.DataError
import co.sublimetech.lideres.core.domain.Result
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.GoogleAuthProvider
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
                val localDateTime =
                    currentTimestamp.toLocalDateTime(TimeZone.currentSystemDefault())
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

    override suspend fun loginUser(
        tokenId: String,
        accessToken: String,
    ): Result<Boolean, DataError> {
        return withContext(Dispatchers.IO) {
            try {
                val credential = GoogleAuthProvider.credential(tokenId, accessToken)
                val authResult = auth.signInWithCredential(credential)

                if (authResult.user?.uid?.let { validateActiveUser(it) } is Result.Success) {
                    Result.Success(true)
                } else {
                    Result.Error(DataError.Network.UNAUTHORIZED)
                }

            } catch (e: Exception) {
                println(e)
                Result.Error(DataError.Network.UNKNOWN)
            }
        }
    }

    override suspend fun validateActiveUser(userId: String): Result<Boolean, DataError> {
        return withContext(Dispatchers.IO) {
            try {
                val docRef = firestore.collection("usuarios").document(userId)
                val snapshot = docRef.get().data<User>()
                val subscribed = snapshot.active

                if (subscribed) {
                    println("Subscribed: $subscribed")
                    Result.Success(subscribed)

                } else {
                    println("not subscribed")
                    Result.Error(DataError.Network.UNAUTHORIZED)
                }
            } catch (e: Exception) {
                println(e)
                Result.Error(DataError.Network.UNKNOWN)
            }
        }
    }


    override suspend fun logout() {
        auth.signOut()
    }
}
