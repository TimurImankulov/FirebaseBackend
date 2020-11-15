package com.example.firebasebackend.ui.auth

import android.os.Bundle
import com.example.firebasebackend.R
import com.example.firebasebackend.data.base.BaseAuthActivity
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : BaseAuthActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        setupListeners()
    }

    private fun setupListeners() {
        btnSentPhone.setOnClickListener {
            verifyPhone(etInputNumber.text.toString())
        }

        btnSentCode.setOnClickListener {
            storedVerificationId?.let { verificationId ->
                val credential = PhoneAuthProvider.getCredential(
                    verificationId,
                    etInputCode.text.toString()
                )

                signInWithPhoneAuthCredential(credential)
            }
        }
    }
}

