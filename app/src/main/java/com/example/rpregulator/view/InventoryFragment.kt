package com.example.rpregulator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rpregulator.adapters.InventoryAdapter
import com.example.rpregulator.databinding.FragmentInventoryBinding
import com.example.rpregulator.utils.GlobalConstants.USER_ID
import com.example.rpregulator.viewmodel.InventoryViewModel
import com.example.rpregulator.viewmodel.InventoryViewModelFactory

class InventoryFragment : Fragment() {
    private var _binding: FragmentInventoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInventoryBinding.inflate(inflater, container, false)
        val view = binding.root

        val viewModelFactory = InventoryViewModelFactory(this)
        val inventoryViewModel =
            ViewModelProvider(this, viewModelFactory).get(InventoryViewModel::class.java)

        binding.viewModel = inventoryViewModel

        val options = inventoryViewModel.options

        val adapter = InventoryAdapter(
            options,
            requireContext(),
            USER_ID.value!!,
            InventoryAdapter.OnClickListener {
                inventoryViewModel.navigateToInventoryDetails(it)
            })

        binding.listInventory.adapter = adapter

        inventoryViewModel.navigateToAddInventory.observe(viewLifecycleOwner, {
            it?.let {
                val action =
                    MainFragmentDirections.actionMainFragmentToAddInventoryFragment(USER_ID.value!!)
                findNavController().navigate(action)
            }
        })

        inventoryViewModel.navigateToInventoryDetails.observe(viewLifecycleOwner, {
            it?.let {
                val action = MainFragmentDirections.actionMainFragmentToInventoryDetailsFragment(it)
                findNavController().navigate(action)
            }
        })
        adapter.progressBar.observe(viewLifecycleOwner, {
            it?.let {
                binding.progressBar.visibility = View.GONE
            }
        })
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}