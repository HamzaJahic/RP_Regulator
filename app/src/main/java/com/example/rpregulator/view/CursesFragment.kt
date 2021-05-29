package com.example.rpregulator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rpregulator.adapters.CursesAdapter
import com.example.rpregulator.databinding.FragmentCursesBinding
import com.example.rpregulator.firebase.CursesFirebase
import com.example.rpregulator.models.CursesBlessingsHealth
import com.example.rpregulator.utils.GlobalConstants.Companion.USER_ID
import com.example.rpregulator.viewmodel.CursesViewModel
import com.example.rpregulator.viewmodel.CursesViewModelFactory
import com.firebase.ui.database.FirebaseRecyclerOptions

class CursesFragment: Fragment() {
    private var _binding: FragmentCursesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCursesBinding.inflate(inflater, container, false)
        val view = binding.root

        val viewModelFactory = CursesViewModelFactory()
        val cursesViewModel = ViewModelProvider(this, viewModelFactory).get(CursesViewModel::class.java)
        binding.viewModel = cursesViewModel

        val options = FirebaseRecyclerOptions.Builder<CursesBlessingsHealth>()
                .setQuery(CursesFirebase.databaseReference, CursesBlessingsHealth::class.java)
                .setLifecycleOwner(this)
                .build()

        val adapter = CursesAdapter(options, USER_ID.value!!, CursesAdapter.OnClickListener{
            cursesViewModel.navigateToCurseDetails(it)
        })

        binding.listCurses.adapter = adapter

        cursesViewModel.navigateToCurseDetails.observe(viewLifecycleOwner, {
            it?.let{
                val action = StatusFragmentDirections.actionStatusFragmentToCurseDetailsFragment(it)
                findNavController().navigate(action)
            }
        })

        cursesViewModel.navigateToAddCurses.observe(viewLifecycleOwner, Observer {
            it?.let{
                val action = StatusFragmentDirections.actionStatusFragmentToAddCurseFragment()
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