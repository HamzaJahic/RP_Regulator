package com.example.rpregulator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rpregulator.adapters.CursesAdapter
import com.example.rpregulator.databinding.FragmentCursesBinding
import com.example.rpregulator.utils.GlobalConstants.Companion.USER_ID
import com.example.rpregulator.viewmodel.CursesViewModel
import com.example.rpregulator.viewmodel.CursesViewModelFactory

class CursesFragment : Fragment() {
    private var _binding: FragmentCursesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCursesBinding.inflate(inflater, container, false)
        val view = binding.root

        val viewModelFactory = CursesViewModelFactory(this)
        val cursesViewModel =
            ViewModelProvider(this, viewModelFactory).get(CursesViewModel::class.java)
        binding.viewModel = cursesViewModel

        val options = cursesViewModel.options

        val adapter = CursesAdapter(
            options,
            USER_ID.value!!,
            requireContext(),
            CursesAdapter.OnClickListener {
                cursesViewModel.navigateToCurseDetails(it)
            })

        binding.listCurses.adapter = adapter

        cursesViewModel.navigateToCurseDetails.observe(viewLifecycleOwner, {
            it?.let {
                val action = StatusFragmentDirections.actionStatusFragmentToCurseDetailsFragment(it)
                findNavController().navigate(action)
            }
        })

        cursesViewModel.navigateToAddCurses.observe(viewLifecycleOwner, {
            it?.let {
                val action =
                    StatusFragmentDirections.actionStatusFragmentToAddCurseFragment(USER_ID.value!!)
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