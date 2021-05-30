package com.example.rpregulator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rpregulator.adapters.CharsAdapter
import com.example.rpregulator.databinding.FragmentPeopleBinding
import com.example.rpregulator.viewmodel.CharsViewModel
import com.example.rpregulator.viewmodel.CharsViewModelFactory

class PeopleFragment : Fragment() {
    private var _binding: FragmentPeopleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPeopleBinding.inflate(inflater, container, false)
        val view = binding.root

        val viewModelFactory = CharsViewModelFactory(this)
        val charsViewModel = ViewModelProvider(this, viewModelFactory).get(CharsViewModel::class.java)

        binding.viewModel = charsViewModel

        val adapter = CharsAdapter(charsViewModel.options, requireActivity(), CharsAdapter.OnClickListener {
            charsViewModel.navigateToCharsDetails(it)
        })

        binding.listPeople.adapter = adapter

        charsViewModel.navigateToAddChars.observe(viewLifecycleOwner, {
            it?.let {
                val action = BestiaryFragmentDirections.actionBestiaryFragmentToAddCharFragment()
                findNavController().navigate(action)
            }
        })

        charsViewModel.navigateToCharsDetails.observe(viewLifecycleOwner, {
            it?.let {
                val action = BestiaryFragmentDirections.actionBestiaryFragmentToCharDetailsFragment(it)
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