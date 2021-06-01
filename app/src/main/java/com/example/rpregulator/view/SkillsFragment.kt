package com.example.rpregulator.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rpregulator.adapters.SkillsAdapter
import com.example.rpregulator.databinding.FragmentSkillsBinding
import com.example.rpregulator.utils.GlobalConstants.Companion.USER_ID
import com.example.rpregulator.viewmodel.SkillsViewModel
import com.example.rpregulator.viewmodel.SkillsViewModelFactory

class SkillsFragment : Fragment() {
    private var _binding: FragmentSkillsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSkillsBinding.inflate(inflater, container, false)
        val view = binding.root

        val viewModelFactory = SkillsViewModelFactory(this)
        val skillsViewModel =
            ViewModelProvider(this, viewModelFactory).get(SkillsViewModel::class.java)

        binding.viewModel = skillsViewModel


        val adapter = SkillsAdapter(
            skillsViewModel.options,
            requireContext(),
            USER_ID.value!!,
            SkillsAdapter.OnClickListener {
                skillsViewModel.navigateToSkillDetails(it)
                Log.d("Navigate", "Klik se desio na ${it.id}")
            })

        binding.listSkills.adapter = adapter

        skillsViewModel.navigateToAddSkills.observe(viewLifecycleOwner, {
            it?.let {
                val action =
                    MainFragmentDirections.actionMainFragmentToAddSkillFragment(USER_ID.value!!)
                findNavController().navigate(action)
            }
        })

        skillsViewModel.navigateToSkillDetails.observe(viewLifecycleOwner, {
            it?.let {
                val action = MainFragmentDirections.actionMainFragmentToSkillDetailsFragment(it)
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