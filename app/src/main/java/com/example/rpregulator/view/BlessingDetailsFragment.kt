package com.example.rpregulator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rpregulator.databinding.FragmentBlessingsDetailsBinding
import com.example.rpregulator.utils.AlertDialogBuilders
import com.example.rpregulator.viewmodel.BlessingDetailsViewModel
import com.example.rpregulator.viewmodel.BlessingDetailsViewModelFactory

class BlessingDetailsFragment: Fragment() {
    private var _binding: FragmentBlessingsDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentBlessingsDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        val blessing = BlessingDetailsFragmentArgs.fromBundle(requireArguments()).blessing
        val viewModelFactory = BlessingDetailsViewModelFactory(blessing)
        val blessingDetailViewModel = ViewModelProvider(this, viewModelFactory).get(BlessingDetailsViewModel::class.java)

        binding.viewModel = blessingDetailViewModel



        blessingDetailViewModel.navigateToBlessingEdit.observe(viewLifecycleOwner, {
            it?.let{
                val action = BlessingDetailsFragmentDirections.actionBlessingDetailsFragmentToBlessingEditDetailsFragment(it)
                findNavController().navigate(action)
            }
        })

        blessingDetailViewModel.navigateToBlessing.observe(viewLifecycleOwner,{
            it?.let{
               val action = BlessingDetailsFragmentDirections.actionBlessingDetailsFragmentToStatusFragment()
               findNavController().navigate(action)
            }
        })


        blessingDetailViewModel.showAlertDialog.observe(viewLifecycleOwner, {
            it?.let{
                AlertDialogBuilders.createDeleteAlert(requireContext()) { blessingDetailViewModel.deleteEntry() }
            }
        })

        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}