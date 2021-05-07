package com.example.rpregulator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.rpregulator.databinding.FragmentChangePinBinding
import com.example.rpregulator.viewmodel.ChangePinViewModel
import com.example.rpregulator.viewmodel.ChangePinViewModelFactory
import com.google.firebase.storage.FirebaseStorage

class ChangePinFragment : Fragment() {
    private var _binding: FragmentChangePinBinding? = null
    private val binding get() = _binding!!
    val GALLERY_REQUEST_CODE = 123

    val storageRefrence = FirebaseStorage.getInstance().reference

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentChangePinBinding.inflate(inflater, container, false)
        val view = binding.root


        val viewModelFactory = ChangePinViewModelFactory()
        val changePinViewModel = ViewModelProvider(this, viewModelFactory).get(ChangePinViewModel::class.java)


        binding.viewModel = changePinViewModel


        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}