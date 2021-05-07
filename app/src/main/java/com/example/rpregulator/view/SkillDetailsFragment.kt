package com.example.rpregulator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rpregulator.R
import com.example.rpregulator.databinding.FragmentSkillDetailsBinding
import com.example.rpregulator.utils.AlertDialogBuilders
import com.example.rpregulator.viewmodel.SkillDetailsViewModel
import com.example.rpregulator.viewmodel.SkillDetailsViewModelFactory

class SkillDetailsFragment: Fragment() {
    private var _binding: FragmentSkillDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSkillDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        val skill = SkillDetailsFragmentArgs.fromBundle(requireArguments()).skills
        val viewModelFactory = SkillDetailsViewModelFactory(skill)
        val skillDetailViewModel = ViewModelProvider(this, viewModelFactory).get(SkillDetailsViewModel::class.java)

        binding.viewModel = skillDetailViewModel

        if (skill.cost == " "){
            binding.tvSkillCost.text="-"
        }

        if(skill.type == "ACTIVE"){
            binding.imgSkillType.setImageResource(R.drawable.ic_active)
        } else binding.imgSkillType.setImageResource(R.drawable.ic_passive)

        skillDetailViewModel.navigateToSkillEdit.observe(viewLifecycleOwner, {
            it?.let{
                val action = SkillDetailsFragmentDirections.actionSkillDetailsFragmentToSkillEditDetailsFragment(it)
                findNavController().navigate(action)
            }
        })

        skillDetailViewModel.navigateToSkills.observe(viewLifecycleOwner, {
            it?.let{
                val action = SkillDetailsFragmentDirections.actionSkillDetailsFragmentToMainFragment()
                findNavController().navigate(action)
            }
        })

        skillDetailViewModel.showAlertDialog.observe(viewLifecycleOwner, {
            it?.let{
                AlertDialogBuilders.createDeleteAlert(requireContext()) { skillDetailViewModel.deleteEntry() }
            }
        })

        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}