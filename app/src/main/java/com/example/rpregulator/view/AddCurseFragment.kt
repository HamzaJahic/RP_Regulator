package com.example.rpregulator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rpregulator.databinding.FragmentAddCurseBinding
import com.example.rpregulator.viewmodel.AddCurseViewModel
import com.example.rpregulator.viewmodel.AddCurseViewModelFactory

class AddCurseFragment : Fragment() {
    private var _binding: FragmentAddCurseBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddCurseBinding.inflate(inflater, container, false)
        val view = binding.root
        val id = AddCurseFragmentArgs.fromBundle(requireArguments()).id
        val viewModelFactory = AddCurseViewModelFactory(id)
        val curseViewModel =
            ViewModelProvider(this, viewModelFactory).get(AddCurseViewModel::class.java)

        binding.viewModel = curseViewModel

        curseViewModel.navigateToCurses.observe(viewLifecycleOwner, {
            it?.let {
                val action = AddCurseFragmentDirections.actionAddCurseFragmentToStatusFragment()
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