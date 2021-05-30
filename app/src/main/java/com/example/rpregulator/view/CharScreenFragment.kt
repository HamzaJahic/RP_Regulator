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
import com.example.rpregulator.databinding.FragmentCharScreenBinding
import com.example.rpregulator.firebase.UsersFirebase
import com.example.rpregulator.utils.GlobalConstants.Companion.GALLERY_REQUEST_CODE
import com.example.rpregulator.utils.GlobalConstants.Companion.USER_ID
import com.example.rpregulator.viewmodel.CharScreenViewModel
import com.example.rpregulator.viewmodel.CharScreenViewModelFactory
import com.google.firebase.storage.FirebaseStorage

class CharScreenFragment : Fragment() {
    private var _binding: FragmentCharScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var charScreenViewModel: CharScreenViewModel
    val storageRefrence = FirebaseStorage.getInstance().reference


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharScreenBinding.inflate(inflater, container, false)
        val view = binding.root

        val viewModelFactory = CharScreenViewModelFactory()
        charScreenViewModel = ViewModelProvider(this, viewModelFactory).get(CharScreenViewModel::class.java)

        binding.viewModel = charScreenViewModel

        charScreenViewModel.charName.observe(viewLifecycleOwner, {
            it?.let {
                binding.tvCharName.text = it
            }
        })

        charScreenViewModel.gold.observe(viewLifecycleOwner, {
            it?.let {
                binding.tvCharGold.text = it
            }
        })

        charScreenViewModel.img.observe(viewLifecycleOwner, {
            it?.let {
                Glide.with(this).load(it).into(binding.imgOfChar)
            }
        })

        charScreenViewModel.changePhoto.observe(viewLifecycleOwner, {
            it?.let {
                val intent = Intent()
                intent.type = "image/*"
                intent.action = Intent.ACTION_GET_CONTENT
                startActivityForResult(Intent.createChooser(intent, "Choose photo"), GALLERY_REQUEST_CODE)
            }
        })


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            val imageUri = data.data
            binding.imgOfChar.setImageURI(imageUri)
            uploadToFirebase(imageUri!!)
        }
    }

    fun uploadToFirebase(uri: Uri) {
        val fileRef = storageRefrence.child("${System.currentTimeMillis()} ${charScreenViewModel.charName.value}")
        fileRef.putFile(uri).addOnSuccessListener {
            fileRef.downloadUrl.addOnSuccessListener {
                Log.d("ImageUp", "Upload")
                UsersFirebase.databaseReference.child(USER_ID.value!!).child("img").setValue(it.toString())
                Toast.makeText(requireContext(), "Upload successful!", Toast.LENGTH_LONG).show()

            }
        }.addOnProgressListener {
            Log.d("ImageUp", "Upload")
            Toast.makeText(requireContext(), "Uploading..", Toast.LENGTH_SHORT).show()

        }.addOnFailureListener {
            Log.d("ImageUp", it.toString())
        }
    }
}