package com.example.rpregulator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.rpregulator.databinding.FragmentCharDetailsBinding
import com.example.rpregulator.utils.AlertDialogBuilders
import com.example.rpregulator.viewmodel.CharDetailsViewModel
import com.example.rpregulator.viewmodel.CharDetailsViewModelFactory

class CharDetailsFragment : Fragment() {
    private var _binding: FragmentCharDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCharDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        val char = CharDetailsFragmentArgs.fromBundle(requireArguments()).char
        val viewModelFactory = CharDetailsViewModelFactory(char)
        val charDetailViewModel =
            ViewModelProvider(this, viewModelFactory).get(CharDetailsViewModel::class.java)

        binding.viewModel = charDetailViewModel

        Glide.with(this).load(char.img).into(binding.imgOfChar)

        charDetailViewModel.navigateToCharEdit.observe(viewLifecycleOwner, {
            it?.let {
                val action =
                    CharDetailsFragmentDirections.actionCharDetailsFragmentToCharEditDetailsFragment(
                        it
                    )
                findNavController().navigate(action)
            }
        })

        charDetailViewModel.navigateToChar.observe(viewLifecycleOwner, {
            it?.let {
                val action =
                    CharDetailsFragmentDirections.actionCharDetailsFragmentToBestiaryFragment()
                findNavController().navigate(action)
            }
        })


        charDetailViewModel.showAlertDialog.observe(viewLifecycleOwner, {
            it?.let {
                AlertDialogBuilders.createDeleteAlert(requireContext()) { charDetailViewModel.deleteEntry() }
            }
        })

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}