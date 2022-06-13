package com.example.journalsearch.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.journalsearch.EditProfileActivity
import com.example.journalsearch.InformationActivity
import com.example.journalsearch.LoginActivity
import com.example.journalsearch.UploadActivity

import com.example.journalsearch.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this)[ProfileViewModel::class.java]
        firebaseAuth = FirebaseAuth.getInstance()
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.viewSettingAccount.setOnClickListener {
            val intent = Intent(activity,EditProfileActivity::class.java)
            startActivity(intent)
        }
        binding.viewInfo.setOnClickListener {
            val intent = Intent(activity,InformationActivity::class.java)
            startActivity(intent)
        }
        binding.viewUpload.setOnClickListener {
            val intent = Intent(activity,UploadActivity::class.java)
            startActivity(intent)
        }
        binding.viewExit.setOnClickListener {
            firebaseAuth.signOut()
            val intent = Intent(activity,LoginActivity::class.java)
            startActivity(intent)
        }
        val currentUser = firebaseAuth.currentUser
        binding.tvName.text = currentUser?.displayName
        binding.tvUsername.text = currentUser?.email

        Glide.with(this).load(currentUser?.photoUrl).into(binding.profileImage);
        return root

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}