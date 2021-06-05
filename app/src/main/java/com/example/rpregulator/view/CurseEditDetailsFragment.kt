package com.example.rpregulator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.rpregulator.databinding.FragmentCurseEditDetailsBinding
import com.example.rpregulator.viewmodel.CurseDetailsViewModel
import com.example.rpregulator.viewmodel.CurseDetailsViewModelFactory

class CurseEditDetailsFragment : Fragment() {
    private var _binding: FragmentCurseEditDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCurseEditDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        val curse = CurseEditDetailsFragmentArgs.fromBundle(requireArguments()).curse
        val viewModelFactory = CurseDetailsViewModelFactory(curse)
        val curseDetailViewModel =
            ViewModelProvider(this, viewModelFactory).get(CurseDetailsViewModel::class.java)

        binding.viewModel = curseDetailViewModel

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}