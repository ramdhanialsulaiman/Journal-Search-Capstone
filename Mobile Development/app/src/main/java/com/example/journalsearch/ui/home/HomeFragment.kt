package com.example.journalsearch.ui.home


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide

import com.example.journalsearch.databinding.FragmentHomeBinding
import com.example.journalsearch.EkonomiActivity
import com.example.journalsearch.HukumActivity
import com.example.journalsearch.KesehatanActivity
import com.example.journalsearch.TeknikActivity
import com.google.firebase.auth.FirebaseAuth


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]
        firebaseAuth = FirebaseAuth.getInstance()
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.viewTech.setOnClickListener {
            val intent = Intent(activity, TeknikActivity::class.java)
            startActivity(intent)
        }
        binding.viewLaw.setOnClickListener {
            val intent = Intent(activity, HukumActivity::class.java)
            startActivity(intent)
        }
        binding.viewEconomy.setOnClickListener {
            val intent = Intent(activity, EkonomiActivity::class.java)
            startActivity(intent)
        }
        binding.viewHeal.setOnClickListener {
            val intent = Intent(activity, KesehatanActivity::class.java)
            startActivity(intent)
        }
        val currentUser = firebaseAuth.currentUser
        binding.nameText.text = currentUser?.displayName
        Glide.with(this).load(currentUser?.photoUrl).into(binding.photoProfileHome);
        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}