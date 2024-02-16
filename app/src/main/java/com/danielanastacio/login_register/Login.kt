package com.danielanastacio.login_register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.danielanastacio.login_register.databinding.ActivityLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var buttonSignIn: Button
    private lateinit var textRegister: TextView
    private lateinit var auth: FirebaseAuth
    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            var intent: Intent = Intent(applicationContext,
                MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        editEmail = findViewById(R.id.editEmail)
        editPassword = findViewById(R.id.editPassword)
        textRegister = findViewById(R.id.textRegisterView)
        buttonSignIn = findViewById(R.id.buttonSignIn)

        auth = Firebase.auth

        binding.textRegisterView.setOnClickListener { view ->
            when (view.id) {
                R.id.textRegisterView -> {
                    var intent: Intent = Intent(applicationContext,
                        Register::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
        binding.buttonSignIn.setOnClickListener { view ->
            when (view.id) {
                R.id.buttonSignIn -> {
                    login()
                    var intent: Intent = Intent(applicationContext,
                        MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private fun isValid(): Boolean {
        return (editEmail.text.toString() != ""
                && editPassword.text.toString() != "")
    }

    private fun login() {
        if (isValid()) {
            var email = editEmail.text.toString()
            var password = editPassword.text.toString()
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            baseContext,
                            "Login successful",
                            Toast.LENGTH_SHORT,
                        ).show()
                        val user = auth.currentUser
                    } else {
                        Toast.makeText(
                            baseContext,
                            "Login failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
        }
    }
}