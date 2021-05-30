@file:Suppress("DEPRECATION")

package com.example.rpregulator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rpregulator.R
import com.example.rpregulator.adapters.TabFragmentAdapter
import com.example.rpregulator.databinding.FragmentTabLayoutBinding

class AdminTabFragment : Fragment() {
    private var _binding: FragmentTabLayoutBinding? = null
    private val binding get() = _binding!!

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpTabs()

    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabLayoutBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setUpTabs() {
        val adapter = TabFragmentAdapter(childFragmentManager)
        adapter.addFragment(CharDejoFragment())
        adapter.addFragment(CharHamzaFragment())
        adapter.addFragment(CharStefanFragment())
        adapter.addFragment(CharDumyFragment())
        adapter.addFragment(CharHarisFragment())

        binding.viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        binding.tabLayout.getTabAt(0)!!.setIcon(R.drawable.ic_people)
        binding.tabLayout.getTabAt(1)!!.setIcon(R.drawable.ic_creatures)
        binding.tabLayout.getTabAt(2)!!.setIcon(R.drawable.ic_people)
        binding.tabLayout.getTabAt(3)!!.setIcon(R.drawable.ic_creatures)
        binding.tabLayout.getTabAt(4)!!.setIcon(R.drawable.ic_people)


    }
}