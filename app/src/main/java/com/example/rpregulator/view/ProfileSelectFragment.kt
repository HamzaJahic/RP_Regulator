package com.example.rpregulator.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rpregulator.adapters.CharSelectAdapter
import com.example.rpregulator.databinding.FragmentProfilesBinding
import com.example.rpregulator.firebase.UsersFirebase
import com.example.rpregulator.models.User
import com.example.rpregulator.viewmodel.CharSelectViewModel
import com.example.rpregulator.viewmodel.CharSelectViewModelFactory
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.storage.FirebaseStorage

class ProfileSelectFragment: Fragment() {
    private var _binding: FragmentProfilesBinding? = null
    private val binding get() = _binding!!
    val storageRefrence = FirebaseStorage.getInstance().reference


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfilesBinding.inflate(inflater, container, false)
        val view = binding.root
        
        val options =  FirebaseRecyclerOptions.Builder<User>()
                .setQuery(UsersFirebase.databaseReference, User::class.java)
                .setLifecycleOwner(this)
                .build()

        val viewModelFactory = CharSelectViewModelFactory()
        val charSelectViewModel = ViewModelProvider(this,  viewModelFactory).get(CharSelectViewModel::class.java)
        
        val adapter = CharSelectAdapter(options,requireActivity(), CharSelectAdapter.OnClickListener{
        })

        val layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        binding.charLista.layoutManager = layoutManager
        binding.charLista.adapter = adapter

        charSelectViewModel.navigateToPin.observe(viewLifecycleOwner, Observer {
            it?.let{
                val action = CharSelectFragmentDirections.actionCharSelectFragmentToPinFragment(it)
                findNavController().navigate(action)
            }
        })

        adapter.progressBar.observe(viewLifecycleOwner, Observer {
            it?.let{
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