package com.elmacbeto.myapplication.data.datasource.remote.api

import android.content.Intent
import com.elmacbeto.myapplication.data.datasource.remote.Resource
import com.elmacbeto.myapplication.data.datasource.remote.flowCall
import com.elmacbeto.myapplication.data.datasource.remote.response.AuthResponse
import com.elmacbeto.myapplication.sis.di.FirebaseClientModule
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FirebaseApiService @Inject constructor(
    private val client: FirebaseClientModule
) {
    private val authFirebase = client.getInstanceFirebaseAuth()

    suspend fun singInWithGoogle(data: Intent): Flow<Resource<AuthResponse>> =
        flowCall {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val googleInAccount = task.getResult(ApiException::class.java)
            val idToken = googleInAccount.idToken
            val credential = GoogleAuthProvider.getCredential(idToken, null)

            val response = client.getInstance.signInWithCredential(credential).await().let {
                AuthResponse(
                    userEmail = authFirebase.currentUser?.email,
                    photoUrl = authFirebase.currentUser?.photoUrl.toString()
                )
            }
            response
        }

    suspend fun createNewRegister(user: String, password: String): Flow<Resource<AuthResponse>> =
        flowCall {
            val response = authFirebase.createUserWithEmailAndPassword(user, password).await().let {
                AuthResponse(
                    userEmail = authFirebase.currentUser?.email,
                    photoUrl = authFirebase.currentUser?.photoUrl.toString()
                )
            }
            response
        }

    suspend fun loginUser(user: String, password: String): Flow<Resource<AuthResponse>> =
        flowCall {
            val response = authFirebase.signInWithEmailAndPassword(user, password).await().let {
                AuthResponse(
                    userEmail = authFirebase.currentUser?.email,
                    photoUrl = authFirebase.currentUser?.photoUrl.toString()
                )
            }
            response
        }

    fun getClientProvide(): GoogleSignInClient = client.googleSing

}