package com.example.rpregulator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rpregulator.databinding.FragmentInventoryDetailsBinding
import com.example.rpregulator.utils.AlertDialogBuilders
import com.example.rpregulator.viewmodel.InventoryDetailsViewModel
import com.example.rpregulator.viewmodel.InventoryDetailsViewModelFactory

class InventoryDetailsFragment : Fragment() {
    private var _binding: FragmentInventoryDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentInventoryDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        val inventory = InventoryDetailsFragmentArgs.fromBundle(requireArguments()).inventory
        val viewModelFactory = InventoryDetailsViewModelFactory(inventory)
        val inventoryDetailViewModel =
            ViewModelProvider(this, viewModelFactory).get(InventoryDetailsViewModel::class.java)

        binding.viewModel = inventoryDetailViewModel

        inventoryDetailViewModel.navigateToInventoryEdit.observe(viewLifecycleOwner, {
            it?.let {
                val action =
                    InventoryDetailsFragmentDirections.actionInventoryDetailsFragmentToInventoryEditDetailsFragment(
                        it
                    )
                findNavController().navigate(action)
            }
        })

        inventoryDetailViewModel.navigateToInventory.observe(viewLifecycleOwner, {
            it?.let {
                val action =
                    InventoryDetailsFragmentDirections.actionInventoryDetailsFragmentToMainFragment()
                findNavController().navigate(action)
            }
        })


        inventoryDetailViewModel.showAlertDialog.observe(viewLifecycleOwner, {
            it?.let {
                AlertDialogBuilders.createDeleteAlert(requireContext()) { inventoryDetailViewModel.deleteEntry() }
            }
        })

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}