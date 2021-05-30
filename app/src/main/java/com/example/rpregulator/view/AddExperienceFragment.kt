package com.example.rpregulator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.rpregulator.databinding.FragmentAddDataBinding
import com.example.rpregulator.viewmodel.AddExperienceViewModel
import com.example.rpregulator.viewmodel.AddExperienceViewModelFactory

class AddExperienceFragment : Fragment() {
    private var _binding: FragmentAddDataBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddDataBinding.inflate(inflater, container, false)
        val view = binding.root

        val id = AddExperienceFragmentArgs.fromBundle(requireArguments()).id


        val viewModelFactory = AddExperienceViewModelFactory(id)
        val addExperienceViewModel = ViewModelProvider(this, viewModelFactory).get(AddExperienceViewModel::class.java)

        binding.viewModel = addExperienceViewModel

        addExperienceViewModel.navigateToMain.observe(viewLifecycleOwner, {
            it?.let {

            }
        })




        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}