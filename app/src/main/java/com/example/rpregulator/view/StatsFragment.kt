package com.example.rpregulator.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rpregulator.adapters.StatsAdapter
import com.example.rpregulator.databinding.FragmentStatsBinding
import com.example.rpregulator.firebase.StatsFirebase
import com.example.rpregulator.models.Stats
import com.example.rpregulator.utils.GlobalConstants.Companion.USER_ID
import com.example.rpregulator.viewmodel.StatsViewModel
import com.example.rpregulator.viewmodel.StatsViewModelFactory
import com.firebase.ui.database.FirebaseRecyclerOptions

class StatsFragment : Fragment() {
    private var _binding: FragmentStatsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStatsBinding.inflate(inflater, container, false)
        val view = binding.root
        val viewModelFactory = StatsViewModelFactory()
        val statsViewModel =
            ViewModelProvider(this, viewModelFactory).get(StatsViewModel::class.java)
        binding.viewModel = statsViewModel

        binding.listStats.invalidate()

        val options = FirebaseRecyclerOptions.Builder<Stats>()
            .setQuery(StatsFirebase.databaseReference.orderByChild("sorting"), Stats::class.java)
            .setLifecycleOwner(this)
            .build()

        val adapter =
            StatsAdapter(options, requireContext(), USER_ID.value!!, StatsAdapter.OnClickListener {
                Log.d("Click", "${it.name}")
            })


        binding.listStats.adapter = adapter


        statsViewModel.navigateToAddStats.observe(viewLifecycleOwner, {
            it?.let {
                val action =
                    MainFragmentDirections.actionMainFragmentToAddStatFragment(USER_ID.value!!)
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