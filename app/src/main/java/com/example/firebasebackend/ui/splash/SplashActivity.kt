package com.example.firebasebackend.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import com.example.firebasebackend.R
import com.example.firebasebackend.ui.auth.AuthActivity
import com.example.firebasebackend.ui.main.MainActivity
import com.example.firebasebackend.ui.main.MainViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import org.koin.androidx.viewmodel.compat.ScopeCompat.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

 //   private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val mUser = FirebaseAuth.getInstance().currentUser

        if (mUser == null) {
            startActivity(Intent(this, AuthActivity::class.java))
        } else {
            loadToken(mUser)
        }
    }

    private fun loadToken(mUser: FirebaseUser) {
        mUser.getIdToken(true)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, MainActivity::class.java))
                    // ...
                } else {
                    startActivity(Intent(this, AuthActivity::class.java))
                }
            }
    }
}
