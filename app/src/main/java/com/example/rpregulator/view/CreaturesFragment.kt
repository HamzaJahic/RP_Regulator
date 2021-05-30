package com.example.rpregulator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rpregulator.adapters.CreaturesAdapter
import com.example.rpregulator.databinding.FragmentCreaturesBinding
import com.example.rpregulator.viewmodel.CreaturesViewModel
import com.example.rpregulator.viewmodel.CreaturesViewModelFactory

class CreaturesFragment : Fragment() {
    private var _binding: FragmentCreaturesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreaturesBinding.inflate(inflater, container, false)
        val view = binding.root


        val viewModelFactory = CreaturesViewModelFactory(this)
        val creaturesViewModel = ViewModelProvider(this, viewModelFactory).get(CreaturesViewModel::class.java)

        binding.viewModel = creaturesViewModel

        val adapter = CreaturesAdapter(creaturesViewModel.options, requireActivity(), CreaturesAdapter.OnClickListener {
            creaturesViewModel.navigateToCreaturesDetails(it)
        })

        binding.listCreatures.adapter = adapter

        creaturesViewModel.navigateToCreaturesDetails.observe(viewLifecycleOwner, {
            it?.let {
                val action = BestiaryFragmentDirections.actionBestiaryFragmentToCreatureDetailsFragment(it)
                findNavController().navigate(action)
            }
        })

        creaturesViewModel.navigateToAddCreatures.observe(viewLifecycleOwner, {
            it?.let {
                val action = BestiaryFragmentDirections.actionBestiaryFragmentToAddCreatureFragment()
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