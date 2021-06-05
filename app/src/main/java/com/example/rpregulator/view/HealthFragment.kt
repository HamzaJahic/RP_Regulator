package com.example.rpregulator.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rpregulator.adapters.StatusAdapter
import com.example.rpregulator.databinding.FragmentHealthBinding
import com.example.rpregulator.firebase.HealthFirebase
import com.example.rpregulator.models.CursesBlessingsHealth
import com.example.rpregulator.utils.GlobalConstants.USER_ID
import com.example.rpregulator.viewmodel.HealthViewModel
import com.example.rpregulator.viewmodel.HealthViewModelFactory
import com.firebase.ui.database.FirebaseRecyclerOptions

class HealthFragment : Fragment() {
    private var _binding: FragmentHealthBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHealthBinding.inflate(inflater, container, false)
        val view = binding.root

        val viewModelFactory = HealthViewModelFactory()
        val healthViewModel =
            ViewModelProvider(this, viewModelFactory).get(HealthViewModel::class.java)
        binding.viewModel = healthViewModel

        val options = FirebaseRecyclerOptions.Builder<CursesBlessingsHealth>()
            .setQuery(HealthFirebase.databaseReference, CursesBlessingsHealth::class.java)
            .setLifecycleOwner(this)
            .build()

        val adapter = StatusAdapter(
            options,
            requireContext(),
            USER_ID.value!!,
            StatusAdapter.OnClickListener {
                Log.d("Click", "${it.name}")
            })

        binding.listHealth.adapter = adapter

        healthViewModel.navigateToAddHealth.observe(viewLifecycleOwner, {
            it?.let {
                val action =
                    StatusFragmentDirections.actionStatusFragmentToAddHealthFragment(USER_ID.value!!)
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