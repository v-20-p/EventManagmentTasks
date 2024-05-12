package com.example.todotasks.presentation.Screens.auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.todotasks.presentation.navigation.destination.destination
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor() :ViewModel() {
    private val auth = Firebase.auth

    val isSigned =
        if (auth.currentUser == null) {
            mutableStateOf(destination.AuthScreens.title)

        }else{
            mutableStateOf(destination.MainScreens.title)
        }

    val error = mutableStateOf("")

    fun login(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful){

                } else {
                    error.value = task.exception?.message.orEmpty()
                }
            }
    }
    fun  logOut(){
        auth.signOut()
        isSigned.value = destination.AuthScreens.title
    }
    fun signUp(email: String, password: String){
        auth.createUserWithEmailAndPassword(email, password)

            .addOnCompleteListener{
                if (it.isSuccessful){
                    isSigned.value = destination.MainScreens.title
                }else{

                    error.value = it.exception?.message.orEmpty()
                }
            }
    }
    fun resetPassword(email: String){
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    error.value = "Check your email"
                }else{
                    error.value = it.exception?.message.orEmpty()
                }
            }

    }



}