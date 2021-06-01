package com.example.rpregulator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.rpregulator.databinding.FragmentInventoryEditDetailsBinding
import com.example.rpregulator.viewmodel.InventoryDetailsViewModel
import com.example.rpregulator.viewmodel.InventoryDetailsViewModelFactory

class InventoryEditDetailsFragment : Fragment() {
    private var _binding: FragmentInventoryEditDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentInventoryEditDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        val inventory = InventoryDetailsFragmentArgs.fromBundle(requireArguments()).inventory
        val viewModelFactory = InventoryDetailsViewModelFactory(inventory)
        val inventoryDetailViewModel =
            ViewModelProvider(this, viewModelFactory).get(InventoryDetailsViewModel::class.java)

        binding.viewModel = inventoryDetailViewModel


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}