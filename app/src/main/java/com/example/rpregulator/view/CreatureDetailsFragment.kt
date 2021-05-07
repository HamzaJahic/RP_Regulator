package com.example.rpregulator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.rpregulator.databinding.FragmentCreatureDetailsBinding
import com.example.rpregulator.utils.AlertDialogBuilders
import com.example.rpregulator.viewmodel.CreatureDetailsViewModel
import com.example.rpregulator.viewmodel.CreatureDetailsViewModelFactory

class CreatureDetailsFragment: Fragment() {
    private var _binding: FragmentCreatureDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCreatureDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        val creature = CreatureDetailsFragmentArgs.fromBundle(requireArguments()).creature
        val viewModelFactory = CreatureDetailsViewModelFactory(creature)
        val creatureDetailViewModel = ViewModelProvider(this, viewModelFactory).get(CreatureDetailsViewModel::class.java)

        binding.viewModel = creatureDetailViewModel

        Glide.with(this).load(creature.img).into(binding.imgOfCreature)

        creatureDetailViewModel.navigateToCreatureEdit.observe(viewLifecycleOwner, {
            it?.let{
               val action = CreatureDetailsFragmentDirections.actionCreatureDetailsFragmentToCreatureEditDetailsFragment(it)
               findNavController().navigate(action)
            }
        })

        creatureDetailViewModel.navigateToCreature.observe(viewLifecycleOwner,{
            it?.let{
               val action = CreatureDetailsFragmentDirections.actionCreatureDetailsFragmentToBestiaryFragment()
                findNavController().navigate(action)
            }
        })


        creatureDetailViewModel.showAlertDialog.observe(viewLifecycleOwner, {
            it?.let{
                AlertDialogBuilders.createDeleteAlert(requireContext()) { creatureDetailViewModel.deleteEntry() }
            }
        })

        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}