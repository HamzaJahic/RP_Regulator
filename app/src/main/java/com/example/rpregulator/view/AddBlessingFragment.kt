package com.example.rpregulator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rpregulator.databinding.FragmentAddBlessingBinding
import com.example.rpregulator.viewmodel.AddBlessingViewModel
import com.example.rpregulator.viewmodel.AddBlessingViewModelFactory

class AddBlessingFragment : Fragment() {
    private var _binding: FragmentAddBlessingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddBlessingBinding.inflate(inflater, container, false)
        val view = binding.root
        val id = AddBlessingFragmentArgs.fromBundle(requireArguments()).id

        val viewModelFactory = AddBlessingViewModelFactory(id)
        val blessingViewModel = ViewModelProvider(this, viewModelFactory).get(AddBlessingViewModel::class.java)

        binding.viewModel = blessingViewModel

        blessingViewModel.navigateToBlessings.observe(viewLifecycleOwner, {
            it?.let {
                val action = AddBlessingFragmentDirections.actionAddBlessingFragmentToStatusFragment()
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