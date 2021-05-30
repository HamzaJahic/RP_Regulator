package com.example.rpregulator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rpregulator.adapters.SkillsAdapter
import com.example.rpregulator.databinding.FragmentRvLayoutBinding
import com.example.rpregulator.viewmodel.DataViewModel
import com.example.rpregulator.viewmodel.DataViewModelFactory

class SkillsAdminFragment : Fragment() {
    private var _binding: FragmentRvLayoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRvLayoutBinding.inflate(inflater, container, false)
        val view = binding.root
        val id = SkillsAdminFragmentArgs.fromBundle(requireArguments()).id

        val viewModelFactory = DataViewModelFactory(this, id)
        val dataDetailViewModel = ViewModelProvider(this, viewModelFactory).get(DataViewModel::class.java)

        val adapter = SkillsAdapter(dataDetailViewModel.optionsSkills, requireContext(), id, SkillsAdapter.OnClickListener {

        })
        binding.viewModel = dataDetailViewModel

        dataDetailViewModel.navigateToAdd.observe(viewLifecycleOwner, {
            it?.let {
                val action = SkillsAdminFragmentDirections.actionSkillsAdminFragmentToAddSkillFragment(id)
                findNavController().navigate(action)
            }
        })

        adapter.progressBar.observe(viewLifecycleOwner, {
            it?.let {
                binding.progressBar.visibility = View.GONE
            }
        })

        binding.recyclerView.adapter = adapter

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}