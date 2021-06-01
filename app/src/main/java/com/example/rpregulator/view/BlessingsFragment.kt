package com.example.rpregulator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rpregulator.adapters.BlessingsAdapter
import com.example.rpregulator.databinding.FragmentBlessingsBinding
import com.example.rpregulator.firebase.BlessingsFirebase
import com.example.rpregulator.models.CursesBlessingsHealth
import com.example.rpregulator.utils.GlobalConstants.Companion.USER_ID
import com.example.rpregulator.viewmodel.BlessingsViewModel
import com.example.rpregulator.viewmodel.BlessingsViewModelFactory
import com.firebase.ui.database.FirebaseRecyclerOptions

class BlessingsFragment : Fragment() {
    private var _binding: FragmentBlessingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBlessingsBinding.inflate(inflater, container, false)
        val view = binding.root

        val viewModelFactory = BlessingsViewModelFactory()
        val blessingsViewModel =
            ViewModelProvider(this, viewModelFactory).get(BlessingsViewModel::class.java)
        binding.viewModel = blessingsViewModel

        val options = FirebaseRecyclerOptions.Builder<CursesBlessingsHealth>()
            .setQuery(BlessingsFirebase.databaseReference, CursesBlessingsHealth::class.java)
            .setLifecycleOwner(this)
            .build()

        val adapter = BlessingsAdapter(
            options,
            requireContext(),
            USER_ID.value!!,
            BlessingsAdapter.OnClickListener {
                blessingsViewModel.navigateToBlessingDetail(it)
            })

        binding.listBlessing.adapter = adapter

        blessingsViewModel.navigateToBlessingDetail.observe(viewLifecycleOwner, {
            it?.let {
                val action =
                    StatusFragmentDirections.actionStatusFragmentToBlessingDetailsFragment(it)
                findNavController().navigate(action)
            }
        })

        blessingsViewModel.navigateToAddBlessings.observe(viewLifecycleOwner, {
            it?.let {
                val action =
                    StatusFragmentDirections.actionStatusFragmentToAddBlessingFragment(USER_ID.value!!)
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