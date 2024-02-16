package com.danielanastacio.login_register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.danielanastacio.login_register.databinding.ActivityRegisterBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var editEmail: TextInputEditText
    private lateinit var editPassword: TextInputEditText
    private lateinit var buttonSignUp: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var progressBar: ProgressBar
    private lateinit var textLoginView: TextView

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
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        editEmail = findViewById(R.id.editEmail)
        editPassword = findViewById(R.id.editPassword)
        buttonSignUp = findViewById(R.id.buttonSignUp)
        progressBar = findViewById(R.id.progressBar)
        textLoginView = findViewById(R.id.textLoginView)

        auth = Firebase.auth

        binding.buttonSignUp.setOnClickListener { view ->
            when (view.id) {
                R.id.buttonSignUp -> {
                    register()
                }
            }
        }
        binding.textLoginView.setOnClickListener {view ->
            when (view.id) {
                R.id.textLoginView ->{
                    var intent: Intent = Intent(applicationContext, Login::class.java)
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

    private fun register() {
        if(isValid()){
            var email = editEmail.text.toString()
            var password = editPassword.text.toString()
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    ProgressBar.GONE
                    if (task.isSuccessful) {
                        Toast.makeText(this,
                            "Account Created",
                            Toast.LENGTH_SHORT,
                        ).show()
                        val user = auth.currentUser
                    } else {
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }

        }else{
            Toast.makeText(this,
                "Fill in all fields correctly",
                Toast.LENGTH_SHORT,
            ).show()
        }
    }
}