package com.example.rpregulator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.rpregulator.adapters.StatsAdapter
import com.example.rpregulator.databinding.FragmentRvLayoutBinding
import com.example.rpregulator.viewmodel.DataViewModel
import com.example.rpregulator.viewmodel.DataViewModelFactory

class StatsAdminFragment: Fragment() {
    private var _binding: FragmentRvLayoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRvLayoutBinding.inflate(inflater, container, false)
        val view = binding.root
        val id = StatsAdminFragmentArgs.fromBundle(requireArguments()).id

        val viewModelFactory = DataViewModelFactory(this, id)
        val dataDetailViewModel = ViewModelProvider(this, viewModelFactory).get(DataViewModel::class.java)

       val adapter = StatsAdapter(dataDetailViewModel.optionsStats,id, StatsAdapter.OnClickListener{

       })

        binding.recyclerView.adapter = adapter

        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}