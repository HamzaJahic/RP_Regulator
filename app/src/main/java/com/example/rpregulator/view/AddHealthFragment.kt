package com.example.rpregulator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rpregulator.databinding.FragmentAddHealthBinding
import com.example.rpregulator.viewmodel.AddHealthViewModel
import com.example.rpregulator.viewmodel.AddHealthViewModelFactory

class AddHealthFragment: Fragment() {

    private var _binding: FragmentAddHealthBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddHealthBinding.inflate(inflater, container, false)
        val view = binding.root
        val viewModelFactory = AddHealthViewModelFactory()
        val addHealthViewModel = ViewModelProvider(this, viewModelFactory).get(AddHealthViewModel::class.java)

        binding.viewModel = addHealthViewModel

        addHealthViewModel.navigateToHealths.observe(viewLifecycleOwner, Observer {
            it?.let {
                val action = AddHealthFragmentDirections.actionAddHealthFragmentToStatusFragment()
                findNavController().navigate(action)
            }
        })

        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}