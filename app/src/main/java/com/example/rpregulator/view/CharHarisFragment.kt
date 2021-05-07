package com.example.rpregulator.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.rpregulator.databinding.FragmentCharAdminBinding
import com.example.rpregulator.firebase.UsersFirebase
import com.example.rpregulator.viewmodel.CharAdminViewModel
import com.example.rpregulator.viewmodel.CharAdminViewModelFactory
import com.example.rpregulator.viewmodel.MainActivityViewModel
import com.google.firebase.storage.FirebaseStorage

class CharHarisFragment: Fragment() {
    private var _binding: FragmentCharAdminBinding? = null
    private val binding get() = _binding!!
    val GALLERY_REQUEST_CODE = 123
    lateinit var charAdminViewModel: CharAdminViewModel
    val storageRefrence = FirebaseStorage.getInstance().reference


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharAdminBinding.inflate(inflater, container, false)
        val view = binding.root

        val viewModelFactory = CharAdminViewModelFactory("Haris")
         charAdminViewModel = ViewModelProvider(this, viewModelFactory).get(CharAdminViewModel::class.java)

        binding.viewModel = charAdminViewModel

        charAdminViewModel.img.observe(viewLifecycleOwner,{
            it?.let{
                Glide.with(this).load(it).into(binding.imgOfChar)
            }
        })

        charAdminViewModel.charName.observe(viewLifecycleOwner, {
            it?.let{
                binding.tvCharName.text = it
            }
        })

        charAdminViewModel.navigateToStats.observe(viewLifecycleOwner, {
            it?.let{
                val action = AdminTabFragmentDirections.actionAdminTabFragmentToStatsAdminFragment(it)
                findNavController().navigate(action)
            }
        })

        charAdminViewModel.navigateToSkills.observe(viewLifecycleOwner, {
            it?.let{
                val action = AdminTabFragmentDirections.actionAdminTabFragmentToSkillsAdminFragment(it)
                findNavController().navigate(action)
            }
        })

        charAdminViewModel.navigateToInventory.observe(viewLifecycleOwner, {
            it?.let{
                val action = AdminTabFragmentDirections.actionAdminTabFragmentToInventoryAdminFragment(it)
                findNavController().navigate(action)
            }
        })

        charAdminViewModel.navigateToCurses.observe(viewLifecycleOwner, {
            it?.let{
                val action = AdminTabFragmentDirections.actionAdminTabFragmentToCursesAdminFragment(it)
                findNavController().navigate(action)
            }
        })

        charAdminViewModel.navigateToBlessings.observe(viewLifecycleOwner, {
            it?.let{
                val action = AdminTabFragmentDirections.actionAdminTabFragmentToBlessingsAdminFragment(it)
                findNavController().navigate(action)
            }
        })

        charAdminViewModel.navigateToStatus.observe(viewLifecycleOwner, {
            it?.let{
                val action = AdminTabFragmentDirections.actionAdminTabFragmentToStatusAdminFragment(it)
                findNavController().navigate(action)
            }
        })


        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK && data!=null){
            val imageUri = data.data
            binding.imgOfChar.setImageURI(imageUri)
            uploadToFirebase(imageUri!!)
        }
    }

    fun uploadToFirebase(uri: Uri){
        val fileRef = storageRefrence.child("${System.currentTimeMillis()} ${charAdminViewModel.charName.value}")
        fileRef.putFile(uri).addOnSuccessListener {
            fileRef.downloadUrl.addOnSuccessListener {
                Log.d("ImageUp","Upload")
                UsersFirebase.databaseReference.child(MainActivityViewModel.id.value.toString()).child("img").setValue(it.toString())
                Toast.makeText(requireContext(), "Upload successful!", Toast.LENGTH_LONG).show()

            }
        }.addOnProgressListener {
            Log.d("ImageUp","Upload")
            Toast.makeText(requireContext(), "Uploading..", Toast.LENGTH_SHORT).show()

        }.addOnFailureListener{
            Log.d("ImageUp", it.toString())
        }
    }
}