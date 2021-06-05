@file:Suppress("DEPRECATION")

package com.example.rpregulator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rpregulator.R
import com.example.rpregulator.adapters.TabFragmentAdapter
import com.example.rpregulator.databinding.FragmentMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    val storageRefrence = FirebaseStorage.getInstance().reference
    val databaseReference: DatabaseReference = Firebase.database.reference.child("Users")

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setUpTabs()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpTabs() {
        val adapter = TabFragmentAdapter(childFragmentManager)
        adapter.addFragment(StatsFragment())
        adapter.addFragment(SkillsFragment())
        adapter.addFragment(InventoryFragment())
        adapter.addFragment(CharScreenFragment())
        binding.viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        binding.tabLayout.getTabAt(0)!!.setIcon(R.drawable.stats)
        binding.tabLayout.getTabAt(1)!!.setIcon(R.drawable.skills)
        binding.tabLayout.getTabAt(2)!!.setIcon(R.drawable.inventory)
        binding.tabLayout.getTabAt(3)!!.setIcon(R.drawable.profile)
    }
}