@file:Suppress("DEPRECATION")

package com.example.journalsearch

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.example.journalsearch.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlin.Exception


@Suppress("DEPRECATION")
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var progressDialog: ProgressDialog
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private var email = ""
    private var password = ""

    private companion object {
        private const val RC_SIGN_IN = 1000
        private const val TAG = "GOOGLE_SIGN_IN_TAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Harap Tunggu")
        progressDialog.setMessage("Masuk...")
        progressDialog.setCanceledOnTouchOutside(false)

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        binding.noAccount.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {
            validateData()
        }
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("172488824815-h12su3qb277jfbe7tgp6mks4pjq2897b.apps.googleusercontent.com")
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)

        binding.googleBtn.setOnClickListener {
            Log.d(TAG, "onCreate: Mulai Google SignIn")
            val intent = googleSignInClient.signInIntent
            startActivityForResult(intent, RC_SIGN_IN)
        }

        binding.forPassword.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }
    }

    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null) {
            startActivity(Intent(this, BottomActivity::class.java))
            finish()
        }
    }

    private fun validateData() {
        email = binding.emailEt.text.toString().trim()
        password = binding.passwordEt.text.toString().trim()
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.emailEt.error = "Format Email Salah"
        } else if (TextUtils.isEmpty(password)) {
            binding.passwordEt.error = "Silakan Masukkan Kata Sandi"
        } else {
            firebaseLogin()
        }
    }

    private fun firebaseLogin() {
        progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                progressDialog.dismiss()
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this, "Masuk sebagai $email", Toast.LENGTH_SHORT)
                    .show()
                startActivity(Intent(this, BottomActivity::class.java))
                finish()
            }
            .addOnFailureListener { e ->
                progressDialog.dismiss()
                Toast.makeText(this, "Login Gagal Karena ${e.message}", Toast.LENGTH_SHORT)
                    .show()
            }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN)
            Log.d(TAG,"onActivityResult: Hasil Intent Google SignIn")
            val account = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            val forAccount = account.getResult(ApiException::class.java)
            firebaseAuthWithGoogle(forAccount)
        }
        catch (e: Exception){
            
        }
    }

    private fun firebaseAuthWithGoogle(forAccount: GoogleSignInAccount?) {
        Log.d(TAG,"firebaseAuthWithGoogle: Mulai Firebase Auth Dengan Google")
        val credential = GoogleAuthProvider.getCredential(forAccount!!.idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnSuccessListener { auth ->
                Log.d(TAG, "firebaseAuthWithGoogle: Masuk")
                val firebaseUser = firebaseAuth.currentUser
                val uId = firebaseUser!!.uid
                val email = firebaseUser.email
                Log.d(TAG,"firebaseAuthWithGoogle: uId: $uId")
                Log.d(TAG,"firebaseAuthWithGoogle: Email: $email")
                if (auth.additionalUserInfo!!.isNewUser){
                    Log.d(TAG,"firebaseAuthWithGoogle: Akun telah dibuat...\n$email")
                    Toast.makeText(this@LoginActivity,"Akun telah dibuat...\n$email", Toast.LENGTH_SHORT)
                        .show()
                }
                else{
                    Log.d(TAG,"firebaseAuthWithGoogle: Pengguna yang ada...\n$email")
                    Toast.makeText(this@LoginActivity,"Masuk...\n$email", Toast.LENGTH_SHORT)
                        .show()
                }
                startActivity(Intent(this@LoginActivity,BottomActivity::class.java))
                finish()
            }
            .addOnFailureListener {
                Log.d(TAG,"firebaseAuthWithGoogle: Login Gagal Karena ${it.message}")
                Toast.makeText(this@LoginActivity,"Login Gagal Karena ${it.message}", Toast.LENGTH_SHORT)
                    .show()
            }
    }
}