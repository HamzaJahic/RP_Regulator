package com.example.rpregulator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rpregulator.databinding.FragmentAddPaperworkBinding
import com.example.rpregulator.viewmodel.AddPaperWorkViewModel
import com.example.rpregulator.viewmodel.AddPaperWorkViewModelFactory

class AddPaperWorkFragment : Fragment() {

    private var _binding: FragmentAddPaperworkBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddPaperworkBinding.inflate(inflater, container, false)
        val view = binding.root
        val viewModelFactory = AddPaperWorkViewModelFactory()
        val addPaperWorkViewModel =
            ViewModelProvider(this, viewModelFactory).get(AddPaperWorkViewModel::class.java)

        binding.viewModel = addPaperWorkViewModel

        addPaperWorkViewModel.navigateToPaperWorks.observe(viewLifecycleOwner, {
            it?.let {
                val action =
                    AddPaperWorkFragmentDirections.actionAddPaperWorkFragmentToPaperworkFragment()
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