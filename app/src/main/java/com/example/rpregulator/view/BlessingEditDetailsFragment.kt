package com.example.rpregulator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.rpregulator.databinding.FragmentBlessingEditDetailsBinding
import com.example.rpregulator.viewmodel.BlessingDetailsViewModel
import com.example.rpregulator.viewmodel.BlessingDetailsViewModelFactory

class BlessingEditDetailsFragment : Fragment() {
    private var _binding: FragmentBlessingEditDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentBlessingEditDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        val blessing = BlessingEditDetailsFragmentArgs.fromBundle(requireArguments()).blessing
        val viewModelFactory = BlessingDetailsViewModelFactory(blessing)
        val blessingDetailViewModel = ViewModelProvider(this, viewModelFactory).get(BlessingDetailsViewModel::class.java)

        binding.viewModel = blessingDetailViewModel


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}