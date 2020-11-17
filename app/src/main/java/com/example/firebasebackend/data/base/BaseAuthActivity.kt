package com.example.firebasebackend.data.base

import android.content.Intent
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firebasebackend.ui.main.MainActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_auth.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

abstract class BaseAuthActivity : AppCompatActivity() {

    private var auth = Firebase.auth
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    protected var storedVerificationId: String? = ""

    protected fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = task.result?.user

                    user?.getIdToken(true)?.addOnCompleteListener {
                        if (it.isSuccessful) {
                            val idToken: String? = it.result?.token
                            startActivity(Intent(this, MainActivity::class.java))
                        } else {
                        }
                    }
                } else {
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                    }
                }
            }
    }

    private fun setupCallback() {
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                signInWithPhoneAuthCredential(p0)
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                Toast.makeText(applicationContext, "onVerificationFailed", Toast.LENGTH_LONG).show()
            }

            override fun onCodeSent(
                verificationId: String,
                p1: PhoneAuthProvider.ForceResendingToken
            ) {
                super.onCodeSent(verificationId, p1)
                storedVerificationId = verificationId
            }
        }
    }

    protected fun verifyPhone(phone: String) {
        setupCallback()
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phone)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(callbacks)
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    protected fun startTimer() {
        btnSentPhone.isEnabled = false

        val timer = object : CountDownTimer(60_000, 1_000) {
            override fun onTick(millisUntilFinished: Long) {
                tvTimer.text = dateFormat(millisUntilFinished)
                tvTimer.visibility = View.VISIBLE
            }

            override fun onFinish() {
                btnSentPhone.isEnabled = true
                tvTimer.visibility = View.GONE
            }
        }
        timer.start()
    }

    fun dateFormat(millisUntilFinished: Long): String {
        val format = SimpleDateFormat("mm:ss", Locale.getDefault())
        val date = Date(millisUntilFinished)

        return format.format(date)
    }
}
