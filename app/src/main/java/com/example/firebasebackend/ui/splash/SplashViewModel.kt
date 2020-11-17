package com.example.firebasebackend.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SplashViewModel : ViewModel() {

    val openMain = MutableLiveData<Boolean>()
    val openAuth = MutableLiveData<Boolean>()

    fun auth() {
        val mUser = FirebaseAuth.getInstance().currentUser
        if (mUser == null) {
            openAuth.postValue(true)
        } else {
            loadToken(mUser)
        }
    }

    private fun loadToken(mUser: FirebaseUser) {
        mUser.getIdToken(true)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    openMain.postValue(true)
                } else {
                    openAuth.postValue(true)
                }
            }
    }
}