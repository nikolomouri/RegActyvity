package com.example.registeractivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    private lateinit var eTEmail: EditText
    private lateinit var eTPass: EditText
    private lateinit var eTPass2: EditText
    private lateinit var bRegister: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_activity)
        init()

        registerListener()

    }

    private fun init() {
        eTEmail = findViewById(R.id.eTEmail)
        eTPass = findViewById(R.id.eTPass)
        eTPass2 = findViewById(R.id.eTPass2)
        bRegister = findViewById(R.id.bRegister)
    }

    private fun registerListener() {
        bRegister.setOnClickListener {
            val email = eTEmail.text.toString()
            val password = eTPass.text.toString()
            val password2 = eTPass2.text.toString()
            if (password == password2) {

                if (email.isEmpty() || password.isEmpty() || password2.isEmpty() || password.length < 8 || password2.length < 8) {
                    Toast.makeText(
                        this,
                        "Email or Passwords are empty or incorect",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                } else if (password.contains("[0-9]".toRegex()) && password.contains("[a-z]".toRegex())&&password.contains("[A-Z]".toRegex())) {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(
                                    this,
                                    "You have successfully registered",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                }else {
                    Toast.makeText(this,"password dont contain numers or symbols", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(
                    this,
                    " Passwords don't match",
                    Toast.LENGTH_SHORT
                ).show()

            }
        }
    }
}