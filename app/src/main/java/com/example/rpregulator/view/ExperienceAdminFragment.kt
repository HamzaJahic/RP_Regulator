package com.example.rpregulator.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rpregulator.adapters.ExperienceAdapter
import com.example.rpregulator.databinding.FragmentRvLayoutBinding
import com.example.rpregulator.viewmodel.DataViewModel
import com.example.rpregulator.viewmodel.DataViewModelFactory

class ExperienceAdminFragment: Fragment() {
    private var _binding: FragmentRvLayoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRvLayoutBinding.inflate(inflater, container, false)
        val view = binding.root

        val id = ExperienceAdminFragmentArgs.fromBundle(requireArguments()).id

        val viewModelFactory = DataViewModelFactory(this, id)
        val dataDetailViewModel = ViewModelProvider(this, viewModelFactory).get(DataViewModel::class.java)

        val adapter = ExperienceAdapter(dataDetailViewModel.optionsExperience, ExperienceAdapter.OnClickListener{

        }, id)



        binding.viewModel = dataDetailViewModel
        binding.recyclerView.adapter = adapter

        dataDetailViewModel.navigateToAdd.observe(viewLifecycleOwner,{
            it?.let{
                Log.d("Experience", "${it}")
                val action = ExperienceAdminFragmentDirections.actionExperienceAdminFragmentToAddExperienceFragment(id)
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