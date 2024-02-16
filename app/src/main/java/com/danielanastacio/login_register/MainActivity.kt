package com.danielanastacio.login_register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.danielanastacio.login_register.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var buttonLogout: Button
    private lateinit var textUserDetails: TextView
    private lateinit var auth: FirebaseAuth
    private lateinit var user: FirebaseUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        textUserDetails = findViewById(R.id.textUserDetails)
        buttonLogout = findViewById(R.id.buttonLogout)

        if(auth.currentUser == null){
            var intent: Intent = Intent(applicationContext, Login::class.java)
            startActivity(intent)
            finish()
        }else{
            user = auth.currentUser!!
            textUserDetails.text = user.email
            binding.buttonLogout.setOnClickListener{view->
                when(view.id){
                    R.id.buttonLogout->{
                        auth.signOut()
                        var intent: Intent = Intent(applicationContext, Login::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }

        }
    }
}