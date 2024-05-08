package com.example.laboratory.application.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.laboratory.application.data.Comment
import com.example.laboratory.application.fragments.adapters.CommentListAdapter
import com.example.laboratory.application.localdatabase.mvvm.CommentViewModel
import com.example.laboratory.application.model.PostModel
import com.example.laboratory.databinding.FragmentPostBinding
import kotlinx.coroutines.launch

class PostFragment : Fragment() {
    private var _binding: FragmentPostBinding? = null
    private val binding get() = _binding!!
    private lateinit var commentVM: CommentViewModel
    private lateinit var postModel: PostModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostBinding.inflate(inflater, container, false)
        commentVM = ViewModelProvider(this)[CommentViewModel::class.java]
        postModel = ViewModelProvider(requireActivity()).get(PostModel::class.java)

        postModel.getSelectedPost().observe(viewLifecycleOwner, { post ->
            binding.postHeader.text = post.postHeader
            binding.postDescription.text = post.postDescription
            binding.postImage.load(post.postImage)
        })

        writeComment()
        setCommentListAdapter()

        return binding.root
    }

    private fun setCommentListAdapter() {
        val adapter = CommentListAdapter()
        binding.apply {
            commentAdapter.adapter = adapter
            commentAdapter.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            commentAdapter.setHasFixedSize(false)
        }
        commentVM.getCommentsByPostId(postModel.getPostId())
            .observe(viewLifecycleOwner, Observer { comment ->
                adapter.submitList(comment)
            })
    }

    private fun writeComment() {
        binding.submitBtn.setOnClickListener {
            val comment = binding.createComment.text.toString()
            if (checkInput(comment)) {
                lifecycleScope.launch {
                    commentVM.createComment(
                        comment = Comment(
                            postId = postModel.getPostId(),
                            postText = comment
                        )
                    )
                    Toast.makeText(requireContext(), "comment added", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun checkInput(text: String): Boolean {
        if (TextUtils.isEmpty(text)) {
            Toast.makeText(requireActivity(), "Empty comment", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun deleteComment(comment: Comment) {
        lifecycleScope.launch {
            commentVM.deleteComment(comment)
        }
    }

    private fun editComment(comment: Comment) {
        lifecycleScope.launch {
            commentVM.editComment(comment)
        }
    }
}