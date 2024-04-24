package com.example.fyp_hydration_app_mate.ui.auth.hydrationLogin

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.fyp_hydration_app_mate.R
import com.example.fyp_hydration_app_mate.activitiesList.HydrationListActivity
import com.example.fyp_hydration_app_mate.databinding.ActivityLoginHydrationBinding
import timber.log.Timber

class LoginHydration : AppCompatActivity() {

    private lateinit var hydrationLoginBinding : ActivityLoginHydrationBinding
    private lateinit var loginRegisterViewModel: LoginRegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_hydration)
        hydrationLoginBinding = ActivityLoginHydrationBinding.inflate(layoutInflater)
        setContentView(hydrationLoginBinding.root)

        hydrationLoginBinding.login.setOnClickListener {

            signIn(hydrationLoginBinding.email.text.toString(), hydrationLoginBinding.password.text.toString()
            )
        }

        hydrationLoginBinding.createAccountButton.setOnClickListener {
            createAccount(hydrationLoginBinding.email.text.toString(), hydrationLoginBinding.password.text.toString())
        }

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        loginRegisterViewModel = ViewModelProvider(this).get(LoginRegisterViewModel::class.java)
        loginRegisterViewModel.liveFirebaseUser.observe(this, Observer
        { firebaseUser -> if (firebaseUser != null)
            startActivity(Intent(this, HydrationListActivity::class.java)) })

        loginRegisterViewModel.firebaseAuthManager.errorStatus.observe(this, Observer
        { status -> checkStatus(status) })
    }

    private fun signIn(email: String, password: String) {
        Timber.d("signIn:$email")
        if (!validateForm()) { return }

        loginRegisterViewModel.login(email,password)
    }


    private fun validateForm(): Boolean {
        var valid = true

        val email = hydrationLoginBinding.email.text.toString()
        if (TextUtils.isEmpty(email)) {
            hydrationLoginBinding.email.error = "Required."
            valid = false
        } else {
            hydrationLoginBinding.email.error = null
        }

        val password = hydrationLoginBinding.password.text.toString()
        if (TextUtils.isEmpty(password)) {
            hydrationLoginBinding.password.error = "Required."
            valid = false
        } else {
            hydrationLoginBinding.password.error = null
        }
        return valid
    }


    private fun checkStatus(error:Boolean) {
        if (error)
            Toast.makeText(this,
                getString(R.string.auth_failed),
                Toast.LENGTH_LONG).show()
    }

    private fun updateUI() {
        Toast.makeText(baseContext, "Login Successful", Toast.LENGTH_SHORT).show()
    }


    private fun createAccount(email: String, password: String) {
        Timber.d("createAccount:$email")
        if (!validateForm()) { return }

        loginRegisterViewModel.register(email,password)
    }



}

