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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rpregulator.databinding.FragmentAddCharBinding
import com.example.rpregulator.viewmodel.AddCharViewModel
import com.example.rpregulator.viewmodel.AddCharViewModelFactory
import com.google.firebase.storage.FirebaseStorage

class AddCharFragment: Fragment() {

    private var _binding: FragmentAddCharBinding? = null
    private val binding get() = _binding!!
    val GALLERY_REQUEST_CODE = 123
    val storageRefrence = FirebaseStorage.getInstance().reference
    lateinit var addCharsViewModel: AddCharViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddCharBinding.inflate(inflater, container, false)
        val view = binding.root
        val viewModelFactory = AddCharViewModelFactory()
        addCharsViewModel = ViewModelProvider(this, viewModelFactory).get(AddCharViewModel::class.java)

        binding.viewModel = addCharsViewModel

        addCharsViewModel.navigateToChars.observe(viewLifecycleOwner, Observer {
            it?.let {
                val action = AddCharFragmentDirections.actionAddCharFragmentToBestiaryFragment()
                findNavController().navigate(action)

            }
        })

        addCharsViewModel.uploadPhoto.observe(viewLifecycleOwner, Observer {
            it?.let {
                val intent = Intent()
                intent.setType("image/*")
                intent.setAction(Intent.ACTION_GET_CONTENT)
                startActivityForResult(Intent.createChooser(intent,"Choose photo"), GALLERY_REQUEST_CODE)
            }
        })

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK && data!=null){
            val imageUri = data.data
            binding.imgOfAdd.setImageURI(imageUri)
            uploadToFirebase(imageUri!!)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    fun uploadToFirebase(uri: Uri){
        val fileRef = storageRefrence.child("${System.currentTimeMillis()} ${addCharsViewModel.charName.value}")
        fileRef.putFile(uri).addOnSuccessListener {
            fileRef.downloadUrl.addOnSuccessListener {
                Log.d("ImageUp","Upload")
                addCharsViewModel.img = it.toString()
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