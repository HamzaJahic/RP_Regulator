package com.example.rpregulator.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rpregulator.databinding.FragmentAddCreatureBinding
import com.example.rpregulator.viewmodel.AddCreatureViewModel
import com.example.rpregulator.viewmodel.AddCreatureViewModelFactory
import com.google.firebase.storage.FirebaseStorage

class AddCreatureFragment: Fragment() {

    private var _binding: FragmentAddCreatureBinding? = null
    private val binding get() = _binding!!
    val GALLERY_REQUEST_CODE = 123
    val storageRefrence = FirebaseStorage.getInstance().reference
    lateinit var addCreaturesViewModel: AddCreatureViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddCreatureBinding.inflate(inflater, container, false)
        val view = binding.root
        val viewModelFactory = AddCreatureViewModelFactory()
        addCreaturesViewModel = ViewModelProvider(this, viewModelFactory).get(AddCreatureViewModel::class.java)

        binding.viewModel = addCreaturesViewModel

        addCreaturesViewModel.navigateToCreatures.observe(viewLifecycleOwner, Observer {
            it?.let {
                val action = AddCreatureFragmentDirections.actionAddCreatureFragmentToBestiaryFragment()
                findNavController().navigate(action)
            }
        })

        addCreaturesViewModel.uploadPhoto.observe(viewLifecycleOwner, Observer {
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
        val fileRef = storageRefrence.child("${System.currentTimeMillis()} ${addCreaturesViewModel.creatureName.value}")
        fileRef.putFile(uri).addOnSuccessListener {
            fileRef.downloadUrl.addOnSuccessListener {
                Log.d("ImageUp","Upload")
                addCreaturesViewModel.img = it.toString()

            }
        }.addOnProgressListener {
            Log.d("ImageUp","Upload")
        }.addOnFailureListener{
            Log.d("ImageUp", it.toString())
        }
    }
}