@file:Suppress("DEPRECATION")

package com.example.journalsearch

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.journalsearch.databinding.ActivityForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth

@Suppress("DEPRECATION")
class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog
    private var email = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        supportActionBar!!.hide()

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please Wait")
        progressDialog.setCanceledOnTouchOutside(false)

        binding.btnSubmit.setOnClickListener {
            validateData()
        }
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun validateData() {
        email = binding.emailEt.text.toString().trim()
        if (email.isEmpty()){
            Toast.makeText(this,"Enter Email...", Toast.LENGTH_SHORT)
                .show()
        }
        else if (Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this, "Invalid Email Pattern...", Toast.LENGTH_SHORT)
                .show()
        }
        else{
            recoverPassword()
        }
    }

    private fun recoverPassword() {
        progressDialog.setMessage("Sending Password Reset Instructions to $email")
        progressDialog.show()
        firebaseAuth.sendPasswordResetEmail(email)
            .addOnSuccessListener {
                progressDialog.dismiss()
                Toast.makeText(this,"Instructions Sent to \n$email", Toast.LENGTH_SHORT)
                    .show()
            }
            .addOnFailureListener { e->
                progressDialog.dismiss()
                Toast.makeText(this,"Failed to Send due to ${e.message}", Toast.LENGTH_SHORT)
                    .show()
            }
    }
}