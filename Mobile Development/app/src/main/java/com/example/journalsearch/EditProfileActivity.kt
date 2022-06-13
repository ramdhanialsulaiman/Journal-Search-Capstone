package com.example.journalsearch

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.journalsearch.databinding.ActivityEditProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream

@Suppress("DEPRECATION")
class EditProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditProfileBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var firebaseStorage: FirebaseStorage
    private lateinit var selectedImg: Uri
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        firebaseAuth = FirebaseAuth.getInstance()
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Mohon Tunggu")
        progressDialog.setMessage("Memperbarui Profil")
        progressDialog.setCanceledOnTouchOutside(false)
        firebaseDatabase = FirebaseDatabase.getInstance()
        firebaseStorage = FirebaseStorage.getInstance()

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        binding.btnUpdate.setOnClickListener {
            if (binding.nameEt.text!!.isEmpty()){
                Toast.makeText(this, "Silahkan masukan nama anda", Toast.LENGTH_SHORT)
                    .show()
            }
            else if (selectedImg == null){
                Toast.makeText(this, "Silakan pilih gambar Anda", Toast.LENGTH_SHORT)
                    .show()
            }
            else uploadData()
        }

        binding.imgItemPhoto.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"
            startActivityForResult(intent,1)
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data != null) {
            if (data.data != null) {
                selectedImg = data.data!!
                binding.imgItemPhoto.setImageURI(selectedImg)
            }
        }
    }
    private fun uploadData() {
        val baos = ByteArrayOutputStream()
        val reference = FirebaseStorage.getInstance().reference.child("img_user/${FirebaseAuth.getInstance().currentUser?.email}")
//        val reference = storage.reference.child("profile").child(Date().time.toString())
        reference.putFile((selectedImg)).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(this, "Uploading data, Please wait... ", Toast.LENGTH_SHORT).show()
                reference.downloadUrl.addOnSuccessListener { task ->
                    uploadInfo(task.toString())
                }
            }
        }
    }

    private fun uploadInfo(imgUrl: String) {
        val user =  binding.nameEt.text.toString()
            imgUrl
        progressDialog.show()
        firebaseDatabase.reference.child("users")
            .child(firebaseAuth.currentUser?.uid.toString())
            .setValue(user)
            .addOnSuccessListener {
                progressDialog.dismiss()
                startActivity(Intent(this, BottomActivity::class.java))
                Toast.makeText(this, "Data inserted", Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, "insert data failed", Toast.LENGTH_SHORT).show()
            }
    }
}