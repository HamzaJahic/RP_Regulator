package com.example.rpregulator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.rpregulator.databinding.FragmentSkillEditDetailsBinding
import com.example.rpregulator.viewmodel.SkillDetailsViewModel
import com.example.rpregulator.viewmodel.SkillDetailsViewModelFactory

class SkillEditDetailsFragment : Fragment() {
    private var _binding: FragmentSkillEditDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSkillEditDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        val skill = SkillEditDetailsFragmentArgs.fromBundle(requireArguments()).skills
        val viewModelFactory = SkillDetailsViewModelFactory(skill)
        val skillDetailViewModel = ViewModelProvider(this, viewModelFactory).get(SkillDetailsViewModel::class.java)

        binding.viewModel = skillDetailViewModel


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}