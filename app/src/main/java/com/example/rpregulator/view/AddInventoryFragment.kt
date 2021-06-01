package com.example.rpregulator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rpregulator.databinding.FragmentAddInventoryBinding
import com.example.rpregulator.viewmodel.AddInventoryViewModel
import com.example.rpregulator.viewmodel.AddInventoryViewModelFactory

class AddInventoryFragment : Fragment() {
    private var _binding: FragmentAddInventoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceInventorye: Bundle?
    ): View {

        _binding = FragmentAddInventoryBinding.inflate(inflater, container, false)
        val view = binding.root
        val id = AddInventoryFragmentArgs.fromBundle(requireArguments()).id

        val viewModelFactory = AddInventoryViewModelFactory(id)
        val addInventoryViewModel =
            ViewModelProvider(this, viewModelFactory).get(AddInventoryViewModel::class.java)

        binding.viewModel = addInventoryViewModel

        addInventoryViewModel.navigateToInventory.observe(viewLifecycleOwner, {
            it?.let {
                val action =
                    AddInventoryFragmentDirections.actionAddInventoryFragmentToMainFragment()
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