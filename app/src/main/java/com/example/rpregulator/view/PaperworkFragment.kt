package com.example.rpregulator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rpregulator.adapters.PaperworkAdapter
import com.example.rpregulator.databinding.FragmentPaperworkBinding
import com.example.rpregulator.viewmodel.PaperworkViewModel
import com.example.rpregulator.viewmodel.PaperworkViewModelFactory

class PaperworkFragment : Fragment() {
    private var _binding: FragmentPaperworkBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaperworkBinding.inflate(inflater, container, false)
        val view = binding.root

        val viewModelFactory = PaperworkViewModelFactory(this)
        val paperworkViewModel = ViewModelProvider(this, viewModelFactory).get(PaperworkViewModel::class.java)

        binding.viewModel = paperworkViewModel

        val adapter = PaperworkAdapter(paperworkViewModel.options, PaperworkAdapter.OnClickListener {
            paperworkViewModel.navigateToPaperWorkDetails(it)
        })

        binding.listPaperwork.adapter = adapter

        paperworkViewModel.navigateToAddPaperWork.observe(viewLifecycleOwner, {
            it?.let {
                val action = PaperworkFragmentDirections.actionPaperworkFragmentToAddPaperWorkFragment()
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