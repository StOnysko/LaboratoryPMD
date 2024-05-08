package com.example.laboratory.application.fragments

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.laboratory.application.data.Post
import com.example.laboratory.application.localdatabase.mvvm.PostViewModel
import com.example.laboratory.databinding.FragmentCreatePostBinding
import kotlinx.coroutines.launch

class CreatePostFragment : Fragment() {
    private var _binding: FragmentCreatePostBinding? = null
    private val binding get() = _binding!!
    private lateinit var postViewModel: PostViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreatePostBinding.inflate(inflater, container, false)
        postViewModel = ViewModelProvider(this)[PostViewModel::class.java]

        createPost()

        return binding.root
    }

    private fun createPost() {
        binding.createPostBtn.setOnClickListener {
            val postHeader = binding.setPostHeader.text.toString()
            val postDescription = binding.setPostDescription.text.toString()
            if (checkInputs(header = postHeader, description = postDescription)) {
                lifecycleScope.launch {
                    postViewModel.createPost(
                        Post(
                            postHeader = postHeader,
                            postDescription = postDescription,
                            postImage = getBitmap()
                        )
                    )
                }
                Toast.makeText(requireContext(), "post created", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private suspend fun getBitmap(): Bitmap {
        val loading = ImageLoader(requireContext())
        val request = ImageRequest.Builder(requireContext())
            .data("https://www.pngall.com/wp-content/uploads/15/Breaking-News-Template-PNG-Cutout.png")
            .build()

        val result = (loading.execute(request) as SuccessResult).drawable
        return (result as BitmapDrawable).bitmap
    }

    private fun checkInputs(header: String, description: String): Boolean {
        when {
            TextUtils.isEmpty(header) && TextUtils.isEmpty(description) -> {
                Toast.makeText(requireContext(), "Inputs are empty", Toast.LENGTH_SHORT).show()
                return false
            }

            TextUtils.isEmpty(header) -> {
                Toast.makeText(requireContext(), "Input header", Toast.LENGTH_SHORT).show()
                return false
            }

            TextUtils.isEmpty(description) -> {
                Toast.makeText(requireContext(), "Input description", Toast.LENGTH_SHORT).show()
                return false
            }
        }
        return true
    }
}