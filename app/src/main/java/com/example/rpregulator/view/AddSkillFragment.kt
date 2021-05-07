package com.example.rpregulator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rpregulator.databinding.FragmentAddSkillBinding
import com.example.rpregulator.viewmodel.AddSkillViewModel
import com.example.rpregulator.viewmodel.AddSkillViewModelFactory

class AddSkillFragment: Fragment() {
    private var _binding: FragmentAddSkillBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddSkillBinding.inflate(inflater, container, false)
        val view = binding.root
        val viewModelFactory = AddSkillViewModelFactory()
        val addSkillViewModel = ViewModelProvider(this, viewModelFactory).get(AddSkillViewModel::class.java)

        binding.viewModel = addSkillViewModel

        addSkillViewModel.navigateToSkills.observe(viewLifecycleOwner, Observer {
            it?.let {
                val action = AddSkillFragmentDirections.actionAddSkillFragmentToMainFragment()
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