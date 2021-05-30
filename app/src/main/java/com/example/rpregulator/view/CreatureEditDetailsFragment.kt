@file:Suppress("DEPRECATION")

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
import com.bumptech.glide.Glide
import com.example.rpregulator.databinding.FragmentCreatureEditDetailsBinding
import com.example.rpregulator.utils.GlobalConstants.Companion.GALLERY_REQUEST_CODE
import com.example.rpregulator.viewmodel.CreatureDetailsViewModel
import com.example.rpregulator.viewmodel.CreatureDetailsViewModelFactory
import com.google.firebase.storage.FirebaseStorage

class CreatureEditDetailsFragment: Fragment() {
    private var _binding: FragmentCreatureEditDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var creatureDetailViewModel : CreatureDetailsViewModel
    val storageRefrence = FirebaseStorage.getInstance().reference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentCreatureEditDetailsBinding.inflate(inflater, container, false)
        val view = this.binding.root
        val creature = CreatureEditDetailsFragmentArgs.fromBundle(requireArguments()).creature
        val viewModelFactory = CreatureDetailsViewModelFactory(creature)
        creatureDetailViewModel = ViewModelProvider(this, viewModelFactory).get(CreatureDetailsViewModel::class.java)

        Glide.with(this).load(creature.img).into(binding.imgOfAdd)

        creatureDetailViewModel.uploadPhoto.observe(viewLifecycleOwner,  {
            it?.let {
                val intent = Intent()
                intent.type = "image/*"
                intent.action = Intent.ACTION_GET_CONTENT
                startActivityForResult(Intent.createChooser(intent,"Choose photo"), GALLERY_REQUEST_CODE)
            }
        })

        binding.viewModel = creatureDetailViewModel


        return view
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK && data!=null){
            val imageUri = data.data
            binding.imgOfAdd.setImageURI(imageUri)
            uploadToFirebase(imageUri!!)
        }
    }

    fun uploadToFirebase(uri: Uri){
        val fileRef = storageRefrence.child("${System.currentTimeMillis()} ${creatureDetailViewModel.creatureName.value}")
        fileRef.putFile(uri).addOnSuccessListener {
            fileRef.downloadUrl.addOnSuccessListener {
                Log.d("ImageUp","Upload")
                creatureDetailViewModel.img = it.toString()
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