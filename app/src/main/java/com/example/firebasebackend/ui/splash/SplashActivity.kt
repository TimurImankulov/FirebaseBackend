package com.example.firebasebackend.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.firebasebackend.R
import com.example.firebasebackend.ui.auth.AuthActivity
import com.example.firebasebackend.ui.main.MainActivity
import com.example.firebasebackend.utils.launchActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    private val viewModel by viewModel<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupViewModel()
        viewModel.auth()
    }

    private fun setupViewModel() {
        viewModel.openMain.observe(this, Observer {
            launchActivity<MainActivity>()
        })
        viewModel.openAuth.observe(this, Observer {
            launchActivity<AuthActivity>()
        })
    }
}
