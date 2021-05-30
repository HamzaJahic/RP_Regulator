package com.example.rpregulator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rpregulator.adapters.NotesAdapter
import com.example.rpregulator.databinding.FragmentNotesBinding
import com.example.rpregulator.viewmodel.NotesViewModel
import com.example.rpregulator.viewmodel.NotesViewModelFactory

class NotesFragment : Fragment() {
    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        val view: View = binding.root

        val viewModelFactory = NotesViewModelFactory(this)
        val notesViewModel = ViewModelProvider(this, viewModelFactory).get(NotesViewModel::class.java)

        binding.viewModel = notesViewModel

        val adapter = NotesAdapter(notesViewModel.options, NotesAdapter.OnClickListener {
            notesViewModel.navigateToNotesDetails(it)
        })

        binding.listNotes.adapter = adapter

        notesViewModel.navigateToAddNotes.observe(viewLifecycleOwner, {
            it?.let {
                val action = NotesFragmentDirections.actionNotesFragmentToAddNotesFragment()
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