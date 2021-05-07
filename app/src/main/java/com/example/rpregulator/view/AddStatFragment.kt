package com.example.rpregulator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rpregulator.databinding.FragmentAddStatBinding
import com.example.rpregulator.viewmodel.AddStatViewModel
import com.example.rpregulator.viewmodel.AddStatViewModelFactory

class AddStatFragment: Fragment() {
    private var _binding: FragmentAddStatBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddStatBinding.inflate(inflater, container, false)
        val view = binding.root
        val viewModelFactory = AddStatViewModelFactory()
        val addStatViewModel = ViewModelProvider(this, viewModelFactory).get(AddStatViewModel::class.java)

        binding.viewModel = addStatViewModel


        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}