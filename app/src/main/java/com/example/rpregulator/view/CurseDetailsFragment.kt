package com.example.rpregulator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rpregulator.databinding.FragmentCursesDetailsBinding
import com.example.rpregulator.utils.AlertDialogBuilders
import com.example.rpregulator.viewmodel.CurseDetailsViewModel
import com.example.rpregulator.viewmodel.CurseDetailsViewModelFactory

class CurseDetailsFragment: Fragment() {
    private var _binding: FragmentCursesDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCursesDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        val curse = CurseDetailsFragmentArgs.fromBundle(requireArguments()).curse
        val viewModelFactory = CurseDetailsViewModelFactory(curse)
        val curseDetailViewModel = ViewModelProvider(this, viewModelFactory).get(CurseDetailsViewModel::class.java)

        binding.viewModel = curseDetailViewModel



        curseDetailViewModel.navigateToCurseEdit.observe(viewLifecycleOwner, {
            it?.let{
                val action = CurseDetailsFragmentDirections.actionCurseDetailsFragmentToCurseEditDetailsFragment(it)
               findNavController().navigate(action)
            }
        })

        curseDetailViewModel.navigateToCurse.observe(viewLifecycleOwner,{
            it?.let{
               val action = CurseDetailsFragmentDirections.actionCurseDetailsFragmentToStatusFragment()
               findNavController().navigate(action)
            }
        })


        curseDetailViewModel.showAlertDialog.observe(viewLifecycleOwner, {
            it?.let{
                AlertDialogBuilders.createDeleteAlert(requireContext()) { curseDetailViewModel.deleteEntry() }
            }
        })

        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}