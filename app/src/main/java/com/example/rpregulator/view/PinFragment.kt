package com.example.rpregulator.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.rpregulator.databinding.FragmentPinBinding
import com.example.rpregulator.viewmodel.PinViewModel
import com.example.rpregulator.viewmodel.PinViewModelFactory

class PinFragment : Fragment() {
    private var _binding: FragmentPinBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPinBinding.inflate(inflater, container, false)
        val view = binding.root

        val user = PinFragmentArgs.fromBundle(requireArguments()).user
        val viewModelFactory = PinViewModelFactory(user, requireActivity())
        val pinViewModel = ViewModelProvider(this, viewModelFactory).get(PinViewModel::class.java)

        binding.viewModel = pinViewModel

        Glide.with(this).load(user.img).into(binding.imgKorisnika)

        pinViewModel.navigateToMain.observe(viewLifecycleOwner, {
            it?.let {
                val intent =
                    Intent(requireActivity(), MainActivity::class.java).putExtra("id", user.id)
                intent.flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }
        })


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}