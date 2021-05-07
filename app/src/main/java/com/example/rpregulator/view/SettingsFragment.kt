package com.example.rpregulator.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rpregulator.databinding.FragmentSettingsBinding
import com.example.rpregulator.viewmodel.SettingsViewModel
import com.example.rpregulator.viewmodel.SettingsViewModelFactory

class SettingsFragment: Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val view = binding.root

        val viewModelFactory = SettingsViewModelFactory(requireActivity())
        val settingsViewModel = ViewModelProvider(this, viewModelFactory).get(SettingsViewModel::class.java)
        binding.viewModel = settingsViewModel
        settingsViewModel.logout.observe(viewLifecycleOwner, Observer {
            it?.let{
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }

        })
        
        settingsViewModel.navigateToChangePin.observe(viewLifecycleOwner, {
            it?.let{
                val action = SettingsFragmentDirections.actionSettingsFragmentToChangePinFragment()
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