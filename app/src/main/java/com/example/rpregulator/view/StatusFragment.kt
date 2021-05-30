@file:Suppress("DEPRECATION")

package com.example.rpregulator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rpregulator.R
import com.example.rpregulator.adapters.TabFragmentAdapter
import com.example.rpregulator.databinding.FragmentStatusBinding

class StatusFragment : Fragment() {
    private var _binding: FragmentStatusBinding? = null
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
        _binding = FragmentStatusBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpTabs() {
        val adapter = TabFragmentAdapter(childFragmentManager)
        adapter.addFragment(BlessingsFragment())
        adapter.addFragment(CursesFragment())
        adapter.addFragment(HealthFragment())
        binding.viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)

        binding.tabLayout.getTabAt(0)!!.setIcon(R.drawable.blessings)
        binding.tabLayout.getTabAt(1)!!.setIcon(R.drawable.curses)
        binding.tabLayout.getTabAt(2)!!.setIcon(R.drawable.status)
    }
}